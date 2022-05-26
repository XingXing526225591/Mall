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
               out.println("<script   language='javascript'>alert('�����ɹ���'); </script>");
               return "main";
    }
          @RequestMapping("/selectAll")
          @ResponseBody
          public List<TTravel> getTTravel(Integer page){
              List<TTravel> allTravel = travelServlet.getAllTravel(page);
              return allTravel;
          }

          //��ת������ҳ�溯��
         @RequestMapping("/details/{travelId}")
         public String jumpDetails( @PathVariable("travelId") Integer travelId,HttpSession session){
             TTravel tTravelById = travelServlet.getTTravelById(travelId);
             session.setAttribute("travel",tTravelById);
             return "details";
         }
         //ɾ��һ����Ϣ
    @RequestMapping("/delete/{travelId}")
    @ResponseBody
    public void deleteTTravel(@PathVariable("travelId") Integer travelId){
               synchronized (key) {
                   travelServlet.deleteTTravel(travelId);
               }
    }
       //�޸�һ����Ϣ
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
    //��ת��updateJump
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
    //��ȡ��ҳ��
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
    //����ģ����ѯ
    @RequestMapping("/selectByLike")
    @ResponseBody
    public List<TTravel> getTTravelByLike(Integer page,HttpSession session){
        String s = (String) session.getAttribute("s");
        List<TTravel> tTravelListByLike = travelServlet.getTTravelListByLike(s,page);
        return tTravelListByLike;
    }
    //�����ϴ���Ƭ
    private String getPhotoPath(MultipartFile photo) throws IOException {
        //��ȡ��Ƭ��
        String originalFilename = photo.getOriginalFilename();
        //��ȡ�ϴ��ļ��ĺ�׺��
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //����ͼƬ�ļ���
        String replace = UUID.randomUUID().toString().replace("-","");
        //�ϳ�����ͼƬ��
        originalFilename = replace + substring;
        //��ȡServletContext
        //��ȡ·��
        String path = "D:\\Code\\IDEA\\SSMConformity\\pro1-Travel\\src\\main\\webapp\\static\\IMG";

        File file = new File(path);
        //������ļ�·���������򴴽�
        if(!file.exists()){
            file.mkdir();
        }
        //�����ļ���·��
        String s = path + File.separator + originalFilename;
        //��ͼƬ����
        photo.transferTo(new File(s));
        return "http://127.0.0.1:8080/IMG/" + originalFilename;
    }
}
