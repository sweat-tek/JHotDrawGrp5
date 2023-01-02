/*
 * @(#)OpenRecentFileAction.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.app.action.file;

import org.jhotdraw.api.app.Application;
import org.jhotdraw.net.URIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URI;

/**
 * Loads the specified URI into an empty view. If no empty view is available, a
 * new view is created.
 * <p>
 * This action is called when the user selects an item in the Recent Files
 * submenu of the File menu. The action and the menu item is automatically
 * created by the application, when the {@code ApplicationModel} provides a
 * {@code OpenFileAction}.
 * <hr>
 * <b>Features</b>
 *
 * <p>
 * <em>Allow multiple views per URI</em><br>
 * When the feature is disabled, {@code OpenRecentFileAction} prevents opening
 * an URI which is opened in another view.<br>
 * See {@link org.jhotdraw.app} for a description of the feature.
 * </p>
 *
 * <p>
 * <em>Open last URI on launch</em><br>
 * {@code OpenRecentFileAction} supplies data for this feature by calling
 * {@link Application#addRecentURI} when it successfully opened a file.
 * See {@link org.jhotdraw.app} for a description of the feature.
 * </p>
 *
 * @author Werner Randelshofer.
 * @version $Id$
 */
public class OpenRecentFileAction extends AbstractOpenFileAction {

    private static final long serialVersionUID = 1L;
    public static final String ID = "file.openRecent";
    private final URI uri;

    public OpenRecentFileAction(Application app, URI uri) {
        super(app);
        this.uri = uri;
        putValue(Action.NAME, URIUtil.getName(uri));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        final Application app = getApplication();
        if (!app.isEnabled()) {
            return;
        }

        app.setEnabled(false);
        openFileAction(uri);
        app.setEnabled(true);
    }
}
