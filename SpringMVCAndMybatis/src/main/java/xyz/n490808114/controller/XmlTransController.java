package xyz.n490808114.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.n490808114.domain.User;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/xml")
public class XmlTransController {
    @RequestMapping("/readxml")
    public @ResponseBody User getUser(@RequestBody User user,
                                      HttpServletResponse response){
        user.setLoginName("testForXMl");
        return user;
    }
}
