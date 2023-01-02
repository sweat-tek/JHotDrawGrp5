package org.jhotdraw.app.action.file;

import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.jhotdraw.app.DefaultApplicationModel;
import org.jhotdraw.app.SDIApplication;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URI;

import static org.mockito.Mockito.when;

public class OpenRecentFileActionTest {
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

        URI uri = URI.create("file:/home/anders/Documents/SE20/software-maintenance/JHotDrawGrp5/jhotdraw-app/src/main/resources/org/jhotdraw/app/action/images/editCopy.png");
        OpenRecentFileAction openRecentFileAction = new OpenRecentFileAction(appMock, uri);

        openRecentFileAction.actionPerformed(actionEventMock);

        while(!openRecentFileAction.isDone()) {
        }
    }
}