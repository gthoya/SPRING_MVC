package com.gthoya.application.sign.dao;

import com.gthoya.application.sign.model.User;
import com.gthoya.application.util.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SignDAO extends SqlSessionDaoSupport {
    public int insertUser(User param) {
        return getSqlSession().insert("sign.insertUser", param);
    }

    public User selectUserWithPassword(User param) {
        return getSqlSession().selectOne("sign.selectUserWithPassword", param);
    }

    public User selectUserWithoutPassword(User param) {
        return getSqlSession().selectOne("sign.selectUserWithoutPassword", param);
    }
}
