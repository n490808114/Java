package xyz.n490808114.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.n490808114.domain.User;
import xyz.n490808114.service.HrmService;
import xyz.n490808114.util.HrmConstants;

import javax.servlet.http.HttpSession;


@Controller
public class UserController implements Constant {
    @Autowired
    @Qualifier("hrmServiceImpl")
    private HrmService hrmService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "redirect:loginForm.html";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "email",required = false ) String email,
                              @RequestParam(value = "loginname",required = false) String loginName,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv){

        User user = hrmService.login(loginName,password);
        if(user != null){
            session.setAttribute(HrmConstants.USER_SESSION,user);
            mv.setViewName("redirect:text.html");
        }else{
            mv.addObject("message","��¼��������������������룡");
            mv.setViewName("redirect:loginForm.html");
        }
        return mv;
    }
}
