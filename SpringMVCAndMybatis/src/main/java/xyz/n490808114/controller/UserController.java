package xyz.n490808114.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.n490808114.pojo.User;

import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
@RequestMapping("/user")
public class UserController implements Constant {

    private static List<User> users = new ArrayList<>();;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        logger.info("register GET page been GET");
        return "registerForm.jsp";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam("loginname") String  loginName,
                           @RequestParam("password") String password,
                           @RequestParam("username") String userName,
                           @RequestParam("email") String email){
        logger.info("register POST been POST");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setLoginName(loginName);
        user.setEmail(email);
        users.add(user);
        return  "loginForm.html";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "loginForm.jsp";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "loginname",required=false) String loginName,
                        @RequestParam(value = "email",required=false) String email,
                        @RequestParam("password") String password,
                        Model model){
        String name = loginName==null?email:loginName;
        for (User user:
            users ) {
            if ((user.getLoginName().equals(name)||user.getEmail().equals(name))&&user.getPassword().equals(password) ){
                model.addAttribute("user",user);
                return "myMainPage.jsp";
            }
        }
        return "loginForm.jsp";
    }
}
