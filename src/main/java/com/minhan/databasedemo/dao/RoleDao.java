package com.minhan.databasedemo.dao;

import com.minhan.databasedemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
