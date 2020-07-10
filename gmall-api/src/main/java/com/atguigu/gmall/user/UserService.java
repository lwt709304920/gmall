package com.atguigu.gmall.user;

import com.atguigu.gmall.bean.UmsMember;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUserMembers();

    void addUserMember(UmsMember umsMember);

    void deleteUserMemberById(String id);

    void updateUserMemberById(String id, String username);
}
