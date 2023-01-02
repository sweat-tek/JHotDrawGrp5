package org.jhotdraw.app.action.file;

import org.jhotdraw.action.AbstractApplicationAction;
import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.jhotdraw.api.gui.URIChooser;
import org.jhotdraw.gui.JSheet;
import org.jhotdraw.net.URIUtil;
import org.jhotdraw.util.ResourceBundleUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractOpenFileAction extends AbstractApplicationAction {

    protected final ResourceBundleUtil labels;
    private boolean isNewView;

    private boolean isDone;

    public AbstractOpenFileAction(Application app) {
        super(app);
        labels = ResourceBundleUtil.getBundle("org.jhotdraw.app.Labels");
    }

    private void openView(final View view, final URI uri, final URIChooser chooser) {
        final Application app = getApplication();
        app.show(view);
        view.setEnabled(false);

        new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                setDone(false);
                boolean exists = new File(uri).exists();

                if (exists) {
                    view.read(uri, chooser);
                    return null;
                }

                throw new IOException(labels.getFormatted("file.open.fileDoesNotExist.message", URIUtil.getName(uri)));
            }

            @Override
            protected void done() {
                try {
                    get();
                    view.setURI(uri);
                    Frame w = (Frame) SwingUtilities.getWindowAncestor(view.getComponent());
                    if (w != null) {
                        w.setExtendedState(w.getExtendedState() & ~Frame.ICONIFIED);
                        w.toFront();
                    }
                    view.getComponent().requestFocus();
                    app.addRecentURI(uri);
                } catch (InterruptedException | ExecutionException e) {
                    Logger.getLogger(OpenFileAction.class.getName()).log(Level.SEVERE, null, e);
                    e.printStackTrace();
                    handleIOError(e, uri, view);
                } finally {
                    view.setEnabled(true);
                    setDone(true);
                }
            }
        }.execute();
    }

    private View getEmptyView() {
        final Application app = getApplication();
        final View emptyView = app.getActiveView();
        View view = emptyView;

        isNewView = emptyView == null || !emptyView.isEmpty() || !emptyView.isEnabled();

        if (isNewView) {
            view = app.createView();
            app.add(view);
        }

        int multipleOpenId = generateViewId(view);
        view.setMultipleOpenId(multipleOpenId);

        return view;
    }

    private boolean isPreventOpenSameURI(URI uri, View view) {
        final Application app = getApplication();
        if (app.getModel().isAllowMultipleViewsPerURI()) {
            return false;
        }

        for (View v : app.getViews()) {
            if (v.getURI() != null && v.getURI().equals(uri)) {
                v.getComponent().requestFocus();
                disposeNewView(view);
                return true;
            }
        }

        return false;
    }

    protected void openFileAction(URI uri, final URIChooser chooser) {
        final View view = getEmptyView();

        if (!isPreventOpenSameURI(uri, view)) {
            openView(view, uri, chooser);
        }
    }

    protected void openFileAction(URI uri) {
        openFileAction(uri, null);
    }

    private int generateViewId(View view) {
        int multipleOpenId = 1;
        final Application app = getApplication();

        for (View aView : app.views()) {
            if (aView != view && aView.isEmpty()) {
                multipleOpenId = Math.max(multipleOpenId, aView.getMultipleOpenId() + 1);
            }
        }

        return multipleOpenId;
    }

    private void showErrorMessage(Throwable e, URI uri, View view) {
        String message = e.getMessage() != null ? e.getMessage() : e.toString();
        message = message == null ? "" : message;

        JSheet.showMessageSheet(view.getComponent(),
                "<html>" + UIManager.getString("OptionPane.css")
                        + "<b>" + labels.getFormatted("file.open.couldntOpen.message",
                        URIUtil.getName(uri)) + "</b><p>" + message,
                JOptionPane.ERROR_MESSAGE);

    }

    private void handleIOError(Throwable e, URI uri, View view) {
        disposeNewView(view);
        showErrorMessage(e, uri, view);
    }

    private void disposeNewView(View view) {
        if (isNewView) {
            getApplication().dispose(view);
        }
    }

    synchronized public boolean isDone() {
        return isDone;
    }

    synchronized public void setDone(boolean done) {
        isDone = done;
    }
}
