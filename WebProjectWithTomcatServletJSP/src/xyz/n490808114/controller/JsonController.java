package xyz.n490808114.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.n490808114.po.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/json")
public class JsonController implements Constant {
    @RequestMapping("/testRequestBody")
    public void setJson(@RequestBody User user,
                        HttpServletResponse response)
                        throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        user.setUserName("超级管理员");
        response.getWriter().println(mapper.writeValueAsString(user));
    }
}
