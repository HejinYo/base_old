package com.hejinyo.resolver;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ExceptionController {

    @RequestMapping(value = "/404")
    public ModelAndView error_404(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = null;
        String accept = request.getHeader("accept");//指定客户端能够接收的内容类型
        String content = request.getHeader("content-type");//请求的与实体对应的MIME信息
        String getHeaderX = request.getHeader("X-Requested-With");//Jquery AJAX特有的一个参数
        int httpStatus = response.getStatus();//获取状态码
        if (accept != null && !(accept.indexOf("application/json") > -1 ||
                (getHeaderX != null && getHeaderX.indexOf("XMLHttpRequest") > -1) ||
                (content != null && content.indexOf("application/json") > -1))) {
            String page = "common/error";
            if (HttpStatus.NOT_FOUND.value() == httpStatus) {
                page = "common/page_404";
            } else if (HttpStatus.INTERNAL_SERVER_ERROR.value() == httpStatus) {
                page = "common/page_500";
            }
            mv = new ModelAndView(page);
            mv.addObject("info", "404");
        } else {
            response.setCharacterEncoding("UTF-8"); //设置编码格式
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);//设置ContentType，返回内容的MIME类型
            response.setHeader("Cache-Control", "no-cache");//告诉所有的缓存机制是否可以缓存及哪种类型
            String json = "{\"success\":false,\"msg\":\"Not Found\",\"info\":\"Unfortunately the page you were looking for could not be found. " +
                    "It may be temporarily unavailable, moved or no longer exist. \"}";
            if (HttpStatus.OK.value() == httpStatus) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();//设置状态码500
                json = "{\"success\":false,\"msg\":\"Server Error\",\"info\":\"Something's wrong! " +
                        "It looks as though we've broken something on our system. " +
                        "Don't panic, we are fixing it! Please come back in a while.！\"}";
            }
            response.setStatus(httpStatus);//设置状态码
            try {
                response.getWriter().write(json);
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("xxxxxxxxxxxxxxxxxxx");
        return mv;

    }

    @RequestMapping(value = "/500")
    public void error_500(HttpServletRequest request) {
        throw new CustomException("500");
    }

}
