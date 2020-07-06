package com.atguigu.gmall.user.service;

import com.atguigu.gmall.user.bean.UmsMember;
import java.util.List;

public interface UserService {
    List<UmsMember> getAllUserMembers();

    void addUserMember(UmsMember umsMember);

    void deleteUserMemberById(String id);

    void updateUserMemberById(String id, String username);
}
