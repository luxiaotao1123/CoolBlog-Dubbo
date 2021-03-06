package cn.dubbo.controller;


import cn.dubbo.utils.R1;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class AdviceController  {
    //捕获异常
    @ExceptionHandler(value = {
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            NullPointerException.class
    })
    @ResponseBody
    public R1 ExceptionHandle(){
        return R1.error(500,"服务器内部错误");
    }

}
