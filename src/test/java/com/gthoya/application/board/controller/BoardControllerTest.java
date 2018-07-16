package com.gthoya.application.board.controller;

import com.gthoya.application.board.model.Contents;
import com.gthoya.application.board.service.BoardService;
import com.gthoya.application.constant.CommonConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardControllerTest {
    @InjectMocks
    private BoardController boardController;

    @Mock
    private BoardService boardService;

    @Test
    public void testGetBoardPage() {
        assertEquals(boardController.getBoardPage(), "board/board");
    }

    @Test
    public void testCreateContents() {
        when(boardService.createContents(anyObject())).thenReturn(CommonConstant.SUCCESS);

        assertEquals(boardController.createContents(anyObject()), CommonConstant.SUCCESS);

        verify(boardService, times(1)).createContents(anyObject());
    }

    @Test
    public void testCreateContentsWhenThrowsException() {
        when(boardService.createContents(anyObject())).thenThrow(Exception.class);

        assertEquals(boardController.createContents(anyObject()), CommonConstant.FAIL);

        verify(boardService, times(1)).createContents(anyObject());
    }

    @Test
    public void testModifyContents() {
        when(boardService.modifyContents(anyObject())).thenReturn(CommonConstant.SUCCESS);

        boardController.modifyContents(anyObject());

        verify(boardService, times(1)).modifyContents(anyObject());
    }

    @Test
    public void testModifyContentsWhenThrowsException() {
        when(boardService.modifyContents(anyObject())).thenThrow(Exception.class);

        boardController.modifyContents(anyObject());

        verify(boardService, times(1)).modifyContents(anyObject());
    }

    @Test
    public void testUnusedContents() {
        when(boardService.unusedContents(anyObject())).thenReturn(CommonConstant.SUCCESS);

        boardController.unusedContents(1L);

        verify(boardService, times(1)).unusedContents(anyObject());
    }

    @Test
    public void testUnusedContentsWhenThrowsException() {
        when(boardService.unusedContents(anyObject())).thenThrow(Exception.class);

        boardController.unusedContents(1L);

        verify(boardService, times(1)).unusedContents(anyObject());
    }

    @Test
    public void testGetContentsList() {
        when(boardService.getContentsList(anyObject())).thenReturn(new ArrayList<Contents>());

        assertEquals(boardController.getContentsList(anyObject()).getViewName(), "board/contentsList");

        verify(boardService, times(1)).getContentsList(anyObject());
    }

    @Test
    public void testGetContentsWhenReturnIsNull() {
        when(boardService.getContents(anyObject())).thenReturn(null);

        assertEquals(boardController.getContents(1L).getViewName(), "board/contents");

        verify(boardService, times(1)).getContents(anyObject());
    }

    @Test
    public void testGetContentsWhenReturnIsNotNull() {
        when(boardService.getContents(anyObject())).thenReturn(new Contents());

        assertEquals(boardController.getContents(1L).getViewName(), "board/contents");

        verify(boardService, times(1)).getContents(anyObject());
    }
}
