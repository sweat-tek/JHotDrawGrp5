package org.jhotdraw.app.action.file;

import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.jhotdraw.app.DefaultApplicationModel;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.nio.file.Paths;

import static org.mockito.Mockito.when;

public class OpenRecentFileActionUnitTest {

    private final String IMAGES_PATH = "src/main/resources/org/jhotdraw/app/action/images/";

    @Test
    public void testOpenRecentFile() {
        Application appMock = Mockito.mock(Application.class);
        View viewMock = Mockito.mock(View.class);
        DefaultApplicationModel modelMock = Mockito.mock(DefaultApplicationModel.class);

        when(appMock.isEnabled()).thenReturn(true);
        when(appMock.createView()).thenReturn(viewMock);
        when(appMock.getModel()).thenReturn(modelMock);
        when(appMock.getModel().isAllowMultipleViewsPerURI()).thenReturn(true);

        when(viewMock.getComponent()).thenReturn(Mockito.mock(JComponent.class));

        ActionEvent actionEventMock = Mockito.mock(ActionEvent.class);

        URI uri = URI.create("file:" + Paths.get(IMAGES_PATH + "editCopy.png").toAbsolutePath());
        OpenRecentFileAction openRecentFileAction = new OpenRecentFileAction(appMock, uri);

        openRecentFileAction.actionPerformed(actionEventMock);

        while (!openRecentFileAction.isDone()) {
        }
    }
}