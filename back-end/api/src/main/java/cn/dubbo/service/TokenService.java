package cn.dubbo.service;

import cn.dubbo.common.bean.User;

import java.util.Map;


public interface TokenService {

    public Map<String,Object> createToken(User user);

    public String queryByToken(String token);

    public void checkExpire();

    public int getUserId(String token);
}
