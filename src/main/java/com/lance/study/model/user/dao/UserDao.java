package com.lance.study.model.user.dao;

import com.lance.study.model.user.entity.User;

public interface UserDao {

    int insert(User user);

    User selectOne(int id);
}
