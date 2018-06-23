package com.gthoya.application.board.service;

import com.gthoya.application.board.dao.BoardDAO;
import com.gthoya.application.board.model.Board;
import com.gthoya.application.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardDAO boardDAO;

    public String createBoard(Board board) {
        if (boardDAO.insertBoard(board) == 1) {
            return CommonConstant.SUCCESS;
        }

        return CommonConstant.FAIL;
    }

    public String modifyBoard(Board board) {
        if (boardDAO.updateBoard(board) == 1) {
            return CommonConstant.SUCCESS;
        }

        return CommonConstant.FAIL;
    }

    public List<Board> getBoardList(Board board) {
        return boardDAO.selectBoardList(board);
    }

    public Board getBoard(Board board) {
        return boardDAO.selectBoard(board);
    }
}
