package com.zmx.usermanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmx.usermanager.domain.User;
import com.zmx.usermanager.mapper.UserMapper;
import com.zmx.usermanager.vo.EasyUIResult;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public EasyUIResult queryUserList(Integer page, Integer rows) {

        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 查询User数据
        Example example = new Example(User.class);
        example.setOrderByClause("updated DESC"); // 设置排序条件
        List<User> users = this.userMapper.selectByExample(example);

        // 获取分页后的信息
        PageInfo<User> pageInfo = new PageInfo<User>(users);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    public User queryUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    public Boolean saveUser(User user) {
        return this.userMapper.insert(user) == 1;
    }

    public Boolean updateUser(User user) {
        return this.userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    public Boolean deleteUser(Long id) {
        return this.userMapper.deleteByPrimaryKey(id) == 1;
    }

}
