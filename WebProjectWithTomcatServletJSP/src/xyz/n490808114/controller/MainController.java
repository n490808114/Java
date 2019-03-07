package xyz.n490808114.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class MainController {
    @RequestMapping("/index")
    public String index(){
        return "index.jsp";
    }
    @RequestMapping("/js/{jsFileName}")
    public String jsFile(@PathVariable String jsFileName){
        return jsFileName;
    }
}
