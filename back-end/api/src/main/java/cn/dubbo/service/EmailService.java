package cn.dubbo.service;


import cn.dubbo.common.bean.User;

public interface EmailService {


    boolean senEmail(User user, String email);

    public String getEmailToken(User user);

    public boolean balanceToken(String emailToken);
}
