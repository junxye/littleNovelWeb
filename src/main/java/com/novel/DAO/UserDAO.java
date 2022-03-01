package com.novel.DAO;

import com.novel.entity.User;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {

    // 查询用户
    User queryUser(String account);

    // 修改用户信息
    boolean updateUser(User user);

    // 增加用户
    boolean addUser(User user);

    // 删除用户
    boolean deleteUser(String account);

    // 查询用户个数
    int queryUserNumber(@Param("parameterMap")Map<String,String[]> parameterMap);

    // 查询一页用户
    List<User> queryUserByPage(@Param("currentPage")int currentPage,
                               @Param("pageSize")int pageSize,
                               @Param("paramMap")Map<String, String[]> paramMap);

}
