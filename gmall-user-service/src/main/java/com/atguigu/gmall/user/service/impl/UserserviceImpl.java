package com.atguigu.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.user.UserService;

import com.atguigu.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserserviceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UmsMember> getAllUserMembers() {
        return userMapper.getAllUserMembers();
    }


    @Override
    public void addUserMember(UmsMember umsMember) {
        userMapper.addUserMember(umsMember);
    }

    @Override
    public void deleteUserMemberById(String id) {
        userMapper.deleteUserMemberById(id);
    }

    @Override
    public void updateUserMemberById(String id, String username) {
        userMapper.updateUserMemberById(id,username);
    }


}
