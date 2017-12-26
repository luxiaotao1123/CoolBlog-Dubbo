package cn.dubbo.service.Impl;

import cn.dubbo.common.bean.User;
import cn.dubbo.dao.UserMapper;
import cn.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuwen on 2017/11/13.
 */
@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int queryUser(User user) {
        User user1 = userMapper.selectByNameAndPassword(user);
        int statu=500;
        if (user1!=null){
            statu=200;
        }
        return statu;
    }
}
