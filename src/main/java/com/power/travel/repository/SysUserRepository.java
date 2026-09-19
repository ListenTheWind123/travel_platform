package com.power.travel.repository;

import com.power.travel.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
//取数据，将查询到的sql数据转换为对象，就相当于dao层
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {
    SysUser findSysUserByUsername(String username);
}