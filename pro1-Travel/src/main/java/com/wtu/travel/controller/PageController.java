package com.wtu.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class PageController {


    @RequestMapping("/page/{page}")
    public String page(@PathVariable("page") String page){
            return page;

    }
    @RequestMapping("/page/{directory}/{page}")
    public String pageIframes(@PathVariable("page") String page,@PathVariable("directory") String directory){
        return directory + File.separator + page;

    }

    @RequestMapping("/page/selectByLike/{s}")
    public String pageSelect(@PathVariable("s") String s, HttpSession session){
        session.setAttribute("s",s);
        return "select";
    }

}
