package com.example.ylxjb.technology.service.impl;

import com.example.ylxjb.technology.entity.User;
import com.example.ylxjb.technology.mapper.UserMapper;
import com.example.ylxjb.technology.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lizhen
 * @since 2021-12-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
