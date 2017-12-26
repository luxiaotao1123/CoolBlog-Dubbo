package cn.dubbo.service.Impl;

import cn.dubbo.common.bean.Token;
import cn.dubbo.common.bean.TokenExample;
import cn.dubbo.common.bean.User;
import cn.dubbo.dao.TokenMapper;
import cn.dubbo.dao.UserMapper;
import cn.dubbo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    private static final int Expire = 3600*25;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenMapper tokenMapper;
    
    @Override
    public String queryByToken(String token) {
        return tokenMapper.findByStringToken(token);
    }

    @Override
    public Map<String, Object> createToken(User user) {
        Map<String,Object> map = new HashMap<String,Object>();
        User user1 = userMapper.selectByNameAndPassword(user);
        //创建TokenEntity参数
        String newtoken = UUID.randomUUID().toString();
        Date updateTime = new Date();
        Date expireTime = new Date(updateTime.getTime()+Expire*1000);

        Token token = new Token(newtoken,user1.getId(),updateTime,expireTime);
        //判断token是否已经存在,不存在就存入，存在就更新
        if (tokenMapper.findByUserId(user1.getId())==null){
            tokenMapper.insert(token);
            System.out.println("存入成功");
        }else {
            tokenMapper.updateByToken(token);
            System.out.println("更新成功");
        }
        map.put("token",token);
        return map;
    }

    @Override
    public void checkExpire() {
        Date now = new Date();
        List<Token> list = tokenMapper.selectByExample(new TokenExample());
        for (Token token:list){
            if (token.getExpiretime().getTime()<now.getTime()){
                tokenMapper.deleteByExpireTime(token);
                System.out.println(token.getTokenid()+"已删除");
            }
        }
    }

    @Override
    public int getUserId(String token) {
        return tokenMapper.finduserIdByToken(token);
    }
}
