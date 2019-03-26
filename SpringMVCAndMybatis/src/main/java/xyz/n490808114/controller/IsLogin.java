package xyz.n490808114.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.n490808114.domain.User;


public interface IsLogin {
    @ModelAttribute
    default void checkIsLogin(@RequestParam("loginname") String loginName,
                              @RequestParam("password") String password,
                              ModelAndView mv){
        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);
        mv.addObject("user",user);
    }
}
