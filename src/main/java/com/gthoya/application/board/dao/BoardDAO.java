package com.gthoya.application.board.dao;

import com.gthoya.application.board.model.Contents;
import com.gthoya.application.util.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO extends SqlSessionDaoSupport {
    public int insertContents(Contents contents) {
        return getSqlSession().insert("board.insertContents", contents);
    }

    public int updateContents(Contents contents) {
        return getSqlSession().update("board.updateContents", contents);
    }

    public int updateUnusedContents(Contents contents) {
        return getSqlSession().update("board.updateUnusedContents", contents);
    }

    public List<Contents> selectContentsList(Contents contents) {
        return getSqlSession().selectList("board.selectContents", contents);
    }

    public Contents selectContents(Contents contents) {
        return getSqlSession().selectOne("board.selectContents", contents);
    }
}
