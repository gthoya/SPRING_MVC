package com.gthoya.application.board.dao;

import com.gthoya.application.board.model.Board;
import com.gthoya.configuration.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO extends SqlSessionDaoSupport {
    public int insertBoard(Board board) {
        return getSqlSession().insert("board.insertBoard", board);
    }

    public int updateBoard(Board board) {
        return getSqlSession().update("board.updateBoard", board);
    }

    public List<Board> selectBoardList(Board board) {
        return getSqlSession().selectList("board.selectBoard", board);
    }

    public Board selectBoard(Board board) {
        return getSqlSession().selectOne("board.selectBoard", board);
    }
}
