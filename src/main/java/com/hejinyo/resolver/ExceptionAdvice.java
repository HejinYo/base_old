package com.hejinyo.resolver;

import com.hejinyo.utils.JsonRetrun;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionAdvice {// ResponseEntityExceptionHandler

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return "参数解析失败";
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return exceptionReturn(ex, request, "不支持当前请求方法");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Object handleHttpMediaTypeNotSupportedException(Exception ex, HttpServletRequest request) {
        return exceptionReturn(ex, request, "不支持当前媒体类型");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public Object e(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        return exceptionReturn(e, request, "Server Error");
    }

    private Object exceptionReturn(Exception ex, HttpServletRequest request,String errormsg) {
        String accept = request.getHeader("accept");//指定客户端能够接收的内容类型
        String content = request.getHeader("content-type");//请求的与实体对应的MIME信息
        String getHeaderX = request.getHeader("X-Requested-With");//Jquery AJAX特有的一个参数
        if (accept != null && !(accept.indexOf("application/json") > -1 ||
                (getHeaderX != null && getHeaderX.indexOf("XMLHttpRequest") > -1) ||
                (content != null && content.indexOf("application/json") > -1))) {
            String page = "common/error";
            ModelAndView mv = new ModelAndView(page);
            mv.addObject("message", ex.toString());
            return mv;
        } else {
            //ex.printStackTrace();//打印异常
            return JsonRetrun.result(1, errormsg);
        }
    }
}
