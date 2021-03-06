package cn.lxt.controller;

import cn.lxt.bean.User;
import cn.lxt.service.TokenService;
import cn.lxt.service.UsersService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TokenService tokenService;

    //登录成功后返回一个map
    @GetMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request){
        String username = request.getParameter("name");
        User user = usersService.findByName(username);
        //给这个User创建一个token
        Map<String,Object> map = tokenService.createToken(user.getId());
        map.put("user",user);
        return map;
    }

}
