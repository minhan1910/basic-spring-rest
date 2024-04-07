package com.minhan.databasedemo.dao;

import com.minhan.databasedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
