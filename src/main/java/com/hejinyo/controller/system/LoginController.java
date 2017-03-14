package com.hejinyo.controller.system;

import com.hejinyo.utils.Const;
import com.hejinyo.utils.JsonRetrun;
import com.hejinyo.utils.Tools;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {

    /**
     * 用户登录页面login
     *
     * @return
     */
    @RequestMapping(value = "/to_login", method = RequestMethod.GET)
    public String to_login() {
        return "system/login";
    }

    /**
     * 执行登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String login(HttpSession session, @RequestBody String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        String verifi = jsonObject.getString("verifi");
        String username = jsonObject.getString("username");
        String password = Tools.loginDecoder(username, jsonObject.getString("loginpw"));

        if ("admin".equals(username)) {
            session.removeAttribute(Const.SESSION_VERIFI_KEY);//验证码失效
            session.setAttribute(Const.SESSION_USER_INFO, username);
            return JsonRetrun.result(0, "登录成功！");
        } else {
            return JsonRetrun.result(1, "登录失败！");
        }
    }

    /**
     * 注销登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(Const.SESSION_USER_INFO);
        return "to_login";
    }

    /**
     * 验证成功，返回主界面
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView loginPost(HttpServletRequest request, HttpSession session) {
        /*String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
                if (Tools.isNull(sessionCode).equals(code)) {
                    session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
                }*/
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/home");
        return mv;
    }
}
