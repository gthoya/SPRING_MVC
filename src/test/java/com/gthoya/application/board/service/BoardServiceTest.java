package com.gthoya.application.board.service;

import com.gthoya.application.board.dao.BoardDAO;
import com.gthoya.application.board.model.Contents;
import com.gthoya.constant.CommonConstant;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {
    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardDAO boardDAO;

    @Test
    public void testCreateContentsWhenInsertIsSuccess() {
        when(boardDAO.insertContents(anyObject())).thenReturn(1);

        assertEquals(boardService.createContents(anyObject()), CommonConstant.SUCCESS);

        verify(boardDAO, times(1)).insertContents(anyObject());
    }

    @Test
    public void testCreateContentsWhenInsertIsFail() {
        when(boardDAO.insertContents(anyObject())).thenReturn(0);

        assertEquals(boardService.createContents(anyObject()), CommonConstant.FAIL);

        verify(boardDAO, times(1)).insertContents(anyObject());
    }

    @Test
    public void testModifyContentsWhenInsertIsSuccess() {
        when(boardDAO.updateContents(anyObject())).thenReturn(1);

        assertEquals(boardService.modifyContents(anyObject()), CommonConstant.SUCCESS);

        verify(boardDAO, times(1)).updateContents(anyObject());
    }

    @Test
    public void testModifyContentsWhenInsertIsFail() {
        when(boardDAO.updateContents(anyObject())).thenReturn(0);

        assertEquals(boardService.modifyContents(anyObject()), CommonConstant.FAIL);

        verify(boardDAO, times(1)).updateContents(anyObject());
    }

    @Test
    public void testUnusedContentsWhenInsertIsSuccess() {
        when(boardDAO.updateUnusedContents(anyObject())).thenReturn(1);

        assertEquals(boardService.unusedContents(anyObject()), CommonConstant.SUCCESS);

        verify(boardDAO, times(1)).updateUnusedContents(anyObject());
    }

    @Test
    public void testUnusedContentsWhenInsertIsFail() {
        when(boardDAO.updateUnusedContents(anyObject())).thenReturn(0);

        assertEquals(boardService.unusedContents(anyObject()), CommonConstant.FAIL);

        verify(boardDAO, times(1)).updateUnusedContents(anyObject());
    }

    @Test
    public void testGetContentsList() {
        when(boardDAO.selectContentsList(anyObject())).thenReturn(new ArrayList<Contents>());

        boardService.getContentsList(anyObject());

        verify(boardDAO, times(1)).selectContentsList(anyObject());
    }

    @Test
    public void testGetContents() {
        when(boardDAO.selectContents(anyObject())).thenReturn(null);

        boardService.getContents(anyObject());

        verify(boardDAO, times(1)).selectContents(anyObject());
    }
}
