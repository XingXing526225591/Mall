package com.wtu.travel.controller;

import com.wtu.travel.Dao.UserDao;
import com.wtu.travel.bean.User;
import com.wtu.travel.servlet.UserServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServlet userServlet;


    @RequestMapping(value = "/login")
    public String login(String userName, String passWord, String VerifyCode, Model model, HttpSession session, String remember, HttpServletRequest req,HttpServletResponse resp) {
        String key = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
        if(VerifyCode==null){
            VerifyCode = "****";
        }else {
            key  = key.toUpperCase(Locale.ROOT);
            VerifyCode = VerifyCode.toUpperCase(Locale.ROOT);
        }
        if(key == null|| !VerifyCode.equals(key)){
            model.addAttribute("userName",userName);
            model.addAttribute("passWord",passWord);
            model.addAttribute("msg","验证码不正确，请重新输入");
            return "login";
        }
        User userByUserName = userServlet.getUserByUserName(userName);
        if (userByUserName == null) {
            model.addAttribute("msg","您输入的用户名不存在");
            return "login";
        }else {
            if (!userByUserName.getPassword().equals(passWord)) {
                model.addAttribute("msg","密码输入错误，请重试");
                return "login";
            }
        }
        if (remember == null){
            remember = "";
        }
        if(remember.equals("on")){
            Cookie[] cookies = req.getCookies();
            if (!isEmpty(cookies)){
                createCookie(userByUserName,resp);
            }
        }
        session.setAttribute("user",userByUserName);
        model.addAttribute("msg","");
        return "main";
    }

    @RequestMapping(value = "/registered")
    public String registered(String userName,String passWord,String charN,HttpServletResponse resp) throws IOException {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(passWord);
        user.setCharname(charN);
        userServlet.addOneUser(user);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<script   language='javascript'>alert('注册成功！'); </script>");
        return "login";
    }
   @RequestMapping(value = "/init")
   public String init(HttpServletRequest req,HttpSession session){
       Cookie[] cookies = req.getCookies();
       String name = null;
       if(isEmpty(cookies)){
           for (Cookie cookie : cookies) {
               if("name".equals(cookie.getName())){
                   name = cookie.getValue();
               }

           }
           User userByName = userServlet.getUserByUserName(name);
           session.setAttribute("user",userByName);
           return "main";
       }
        return "login";
   }
    @RequestMapping(value = "/secletUserName")
    @ResponseBody
    public String getUserByUserName(String username) {
        User userByUserName = userServlet.getUserByUserName(username);
        System.out.println(userByUserName);
        if (userByUserName != null) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/checkVarifyCode")
    @ResponseBody
    public String checkVarifyCode(String VerifyCode,HttpSession session){
        String key = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
        if(VerifyCode==null){
            VerifyCode = "****";
        }else {
            key  = key.toUpperCase(Locale.ROOT);
            VerifyCode = VerifyCode.toUpperCase(Locale.ROOT);
        }
        if (VerifyCode.equals(key)){
            return "true";
        }else {
            return "false";
        }
    }

    //判断cookie中是否已存在本次登录的账号
    private boolean isEmpty(Cookie[] cookies){
        if(cookies == null){
            return false;
        }
        for (Cookie cookie : cookies) {
            if("name".equals(cookie.getName())){
                return true;
            }
        }
        return false;
    }
    //如果没有 创建一个cookie
    private void createCookie(User user, HttpServletResponse resp){
        Cookie name = new Cookie("name", user.getUsername());

        name.setPath("/");

        name.setMaxAge(7*24*3600);

        resp.addCookie(name);

    }

    //退出时清楚cookie
    @RequestMapping("/exit")
    public String exit(HttpSession session,HttpServletResponse resp){
        session.removeAttribute("user");
        Cookie cookie = new Cookie("name",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);
        session.invalidate();
        return "login";

    }


}
