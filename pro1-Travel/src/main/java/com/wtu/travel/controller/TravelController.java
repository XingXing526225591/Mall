package com.wtu.travel.controller;


import com.wtu.travel.bean.TTravel;
import com.wtu.travel.bean.User;
import com.wtu.travel.servlet.TravelServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@Controller
public class TravelController {
           @Autowired
           private TravelServlet travelServlet;
         String key = "This is a Key";

           @RequestMapping("/add")
           public String addOneTravelInformation(String title, String region, String context, MultipartFile photo, HttpSession session, HttpServletResponse resp) throws IOException {
               String photoName = getPhotoPath(photo);
               User user = (User)session.getAttribute("user");
               travelServlet.addOneTravelInformation(title,region,user.getId(),context,photoName);
               resp.setCharacterEncoding("UTF-8");
               PrintWriter out = resp.getWriter();
               out.println("<script   language='javascript'>alert('新增成功！'); </script>");
               return "main";
    }
          @RequestMapping("/selectAll")
          @ResponseBody
          public List<TTravel> getTTravel(Integer page){
              List<TTravel> allTravel = travelServlet.getAllTravel(page);
              return allTravel;
          }

          //跳转到详情页面函数
         @RequestMapping("/details/{travelId}")
         public String jumpDetails( @PathVariable("travelId") Integer travelId,HttpSession session){
             TTravel tTravelById = travelServlet.getTTravelById(travelId);
             session.setAttribute("travel",tTravelById);
             return "details";
         }
         //删除一条信息
    @RequestMapping("/delete/{travelId}")
    @ResponseBody
    public void deleteTTravel(@PathVariable("travelId") Integer travelId){
               synchronized (key) {
                   travelServlet.deleteTTravel(travelId);
               }
    }
       //修改一条信息
    @RequestMapping("/update")
    public String reviseTTravel(Integer travelId,String title, String region, String context, MultipartFile photo) throws IOException {
        TTravel tTravelById = travelServlet.getTTravelById(travelId);
        String photoName = null;
        String originalFilename = photo.getOriginalFilename();
        if(originalFilename != "") {
            if (photo.getSize() != 0) {
                photoName = getPhotoPath(photo);
            }
        }
        travelServlet.update(tTravelById,title,region,context,photoName);
        return "main";
    }
    //跳转到updateJump
    @RequestMapping("/updateJump/{tId}")
    public String updateJump(@PathVariable("tId") Integer tId,HttpSession session){
        TTravel tTravelById = travelServlet.getTTravelById(tId);
        session.setAttribute("travel",tTravelById);
        return "updata";
    }
    @RequestMapping("/selectByUserId")
    @ResponseBody
    public List<TTravel> getTTravelByUserId(Integer page,HttpSession session) throws InterruptedException {
               synchronized (key) {
                   User user = (User) session.getAttribute("user");
                   List<TTravel> allTravel = travelServlet.getAllTravelByUserId(page, user.getId());
                   return allTravel;
               }
           }
    //获取总页数
    @RequestMapping("/getCount")
    @ResponseBody
    public Integer getCount(){
        Integer i = null;
        Integer count = travelServlet.getCount();
        if(count%3==0){
            i = (count / 3);
        }else {
            i = (count / 3) + 1;
        }
        return i;

    }
    //处理模糊查询
    @RequestMapping("/selectByLike")
    @ResponseBody
    public List<TTravel> getTTravelByLike(Integer page,HttpSession session){
        String s = (String) session.getAttribute("s");
        List<TTravel> tTravelListByLike = travelServlet.getTTravelListByLike(s,page);
        return tTravelListByLike;
    }
    //处理上传照片
    private String getPhotoPath(MultipartFile photo) throws IOException {
        //获取照片名
        String originalFilename = photo.getOriginalFilename();
        //获取上传文件的后缀名
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成图片文件名
        String replace = UUID.randomUUID().toString().replace("-","");
        //合成整个图片名
        originalFilename = replace + substring;
        //获取ServletContext
        //获取路径
        String path = "D:\\Code\\IDEA\\SSMConformity\\pro1-Travel\\src\\main\\webapp\\static\\IMG";

        File file = new File(path);
        //如果该文件路径不存在则创建
        if(!file.exists()){
            file.mkdir();
        }
        //生成文件新路径
        String s = path + File.separator + originalFilename;
        //将图片保存
        photo.transferTo(new File(s));
        return "http://127.0.0.1:8080/IMG/" + originalFilename;
    }
}
