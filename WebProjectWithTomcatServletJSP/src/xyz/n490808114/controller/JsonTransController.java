package xyz.n490808114.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.n490808114.pojo.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/json")
class JsonTransController implements Constant {
    public static void main(String[] args){
        User user = new User();
        user.setLoginName("admin");
        user.setPassword("12345");

    }
    @RequestMapping(value = "/testRequestBody",method = RequestMethod.POST)
    public void setJson(@RequestBody User user,
                        HttpServletResponse response)
                        throws IOException {
        user.setUserName("≥¨º∂≥Ã–Ú‘±");
        String str = JSON.toJSONString(user);
        logger.info(str);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(str);

    }
    @RequestMapping("/testResponseBody")
    @ResponseBody
    public Object TestResponseBody(){
        List<User> users = new ArrayList<>();
        for(int i=0;i<5;i++){
            User user = new User();
            user.setId(i);
            user.setUserName(""+i);
            user.setLoginName("≤‚ ‘"+i);
            users.add(user);
        }
        return users;
    }
}
