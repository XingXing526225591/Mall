package com.wtu.travel.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"/*"},initParams = {
        @WebInitParam(name = "name",value = "/travel/user/init,/travel/user/login,/travel/regist.do,/user/registered,/travel/user/exit,css,jpg,webp,png,js,user/secletUserName,user/checkVarifyCode")
})
public class SessionFilters implements Filter {

    List<String> stringList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String name = filterConfig.getInitParameter("name");
        String[] split = name.split(",");
        stringList = Arrays.asList(split);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        boolean isFlage = false;
        String uri = req.getRequestURI();
        for (String s : stringList) {
            if(uri.contains(s)) {
                isFlage = true;
                break;
            }
        }
        if(isFlage){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            HttpSession session = req.getSession();
            Object user = session.getAttribute("user");
            if (user == null){
                resp.sendRedirect("/");

            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }


    }

    @Override
    public void destroy() {

    }
}
