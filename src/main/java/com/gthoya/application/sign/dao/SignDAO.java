package com.gthoya.application.sign.dao;

import com.gthoya.application.sign.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignDAO {
    @Autowired
    private SqlSession sqlSession;

    public int insertUser(User param) {
        return sqlSession.insert("sign.insertUser", param);
    }

    public User selectUser(User param) {
        return sqlSession.selectOne("sign.selectUser", param);
    }
}
