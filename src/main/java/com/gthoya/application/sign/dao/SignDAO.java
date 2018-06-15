package com.gthoya.application.sign.dao;

import com.gthoya.application.sign.model.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int insertUser(User param) {
        return sqlSessionTemplate.insert("sign.insertUser", param);
    }

    public User selectUser(User param) {
        return sqlSessionTemplate.selectOne("sign.selectUser", param);
    }
}
