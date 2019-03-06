package xyz.n490808114.test;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {
    private static final Log  logger = LogFactory.getLog(HelloController.class);
    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {
        logger.info("handleRequest ±»µ÷ÓÃ");
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","Hello Wordld!");
        mv.setViewName("/WEB-INF/content/welcome.jsp");
        return mv;
    }
}
