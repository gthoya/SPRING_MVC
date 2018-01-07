package com.gthoya.springmvc.test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
    @Autowired
    private SqlSession sqlSession;

    public String test() {
        return sqlSession.selectOne("test.test");
    }
}
