package com.novel.DAO;

import com.novel.entity.Page;
import com.novel.entity.User;

import java.util.Map;

public interface UserServiceDAO {

    // 查询
    User queryUser(String _account);

    // 修改
    boolean updateUser(User user);

    // 增加
    boolean addUser(User user);

    // 删除
    boolean deleteUser(String _account);

    // 按页查询
    Page<User> queryByPage(int currentPageNumber, int pageSize, Map<String, String[]> paramMap);

}
