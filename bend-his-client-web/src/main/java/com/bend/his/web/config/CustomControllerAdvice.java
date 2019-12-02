package com.bend.his.web.config;

import com.bend.his.common.GenericResponse;
import com.bend.his.common.ResponseFormat;
import com.bend.his.exception.HisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


/**
 * controller 增强器 原理是使用AOP对Controller控制器进行增强（前置增强、后置增强、环绕增强）
 * 启动应用后，被 @ExceptionHandler、@InitBinder、@ModelAttribute 注解的方法，都会作用在 被 @RequestMapping 注解的方法上。
 * <p>
 * ModelAttribute：在Model上设置的值，对于所有被 @RequestMapping 注解的方法中，都可以通过 ModelMap获取，或者通过@ModelAttribute("author")也可以获取
 * ExceptionHandler 拦截了异常，我们可以通过该注解实现自定义异常处理。其中，@ExceptionHandler 配置的 value 指定需要拦截的异常类型，下面拦截了 Exception.class 这种异常。
 */
@ControllerAdvice
public class CustomControllerAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        //model.addAttribute("author", "sxd");
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public GenericResponse errorHandler(Exception ex) {
        logger.error(ex.getMessage());
        return ResponseFormat.retInfo(ResponseFormat.CODE_1000, ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HisException.class)
    public GenericResponse hisExceptionHandler(HisException ex) {
        logger.error(ex.getMsg());
        return ResponseFormat.retInfo(ex.getCode(), ex.getMsg(), null);
    }
}