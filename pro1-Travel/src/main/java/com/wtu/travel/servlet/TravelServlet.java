package com.wtu.travel.servlet;


import com.wtu.travel.Dao.ImgDao;
import com.wtu.travel.Dao.TravelDao;
import com.wtu.travel.Dao.TravelDetailDao;
import com.wtu.travel.Dao.UserDao;
import com.wtu.travel.Utils.UserMapperSqlSession;
import com.wtu.travel.bean.Img;
import com.wtu.travel.bean.TTravel;
import com.wtu.travel.bean.TravelDetail;
import com.wtu.travel.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class TravelServlet {
    @Autowired
    private UserDao userDao;
 @Autowired
 private TravelDao travelDao;
 @Autowired
 private TravelDetailDao travelDetailDao;
 @Autowired
 private ImgDao imgDao;

    public void addOneTravelInformation(String title,String region,Integer authourId,String context,String imgName)  {
        SqlSession userSqlSession = null;
        try {

            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            Date date = new Date();
            int i = travelDao.addOneTravel(userSqlSession, title, region, authourId, date);
            Integer integer = travelDetailDao.AddOneTravelDetail(userSqlSession, context, i);
            imgDao.addOneImg(userSqlSession,imgName,integer);
            userSqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
            userSqlSession.rollback();
        }finally {
            userSqlSession.close();
        }

    }


    public  List<TTravel> getAllTravel(Integer page) {
        SqlSession userSqlSession = null;

        List<TTravel> allTTravel = null;
        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            allTTravel = travelDao.getAllTTravel(userSqlSession,page);
            for (TTravel travel : allTTravel) {
               travel = getTTravel(userSqlSession,travel);
            }
            userSqlSession.commit();
        } catch (IOException e) {
            userSqlSession.rollback();
            e.printStackTrace();
        } finally {
            userSqlSession.close();
        }
        return allTTravel;
    }
    public  List<TTravel> getAllTravelByUserId(Integer page,Integer id) {
        SqlSession userSqlSession = null;

        List<TTravel> allTTravel = null;
        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            allTTravel = travelDao.getAllTTravelByUserId(userSqlSession,page,id);
            for (TTravel travel : allTTravel) {
                travel = getTTravel(userSqlSession,travel);
            }
            userSqlSession.commit();
        } catch (IOException e) {
            userSqlSession.rollback();
            e.printStackTrace();
        } finally {
            userSqlSession.close();
        }
        return allTTravel;
    }

    public TTravel getTTravelById(Integer id) {
        SqlSession userSqlSession = null;
        TTravel tTravelById = null;
        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            tTravelById = travelDao.getTTravelById(userSqlSession, id);
            tTravelById = getTTravel(userSqlSession, tTravelById);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            userSqlSession.close();
        }
        return tTravelById;
    }

    public void deleteTTravel(Integer travelId){
        SqlSession userSqlSession = null;
        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            TTravel tTravelById = getTTravelById(travelId);
            imgDao.deleteImgById(userSqlSession,tTravelById.getImg().getId());
            travelDetailDao.deleteTravelDetail(userSqlSession,tTravelById.getTravelDetail().getId());
            travelDao.deleteTTravelById(userSqlSession,travelId);
            userSqlSession.commit();
        } catch (IOException e) {
            userSqlSession.rollback();
            e.printStackTrace();
        }finally {
            userSqlSession.close();
        }

    }

    public void update(TTravel travel,String title,String region,String context,String photoName){
        SqlSession userSqlSession = null;

        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            travelDao.updateTravelById(userSqlSession,travel.getId(),title,region);
            travelDetailDao.updateTravelDetail(userSqlSession,travel.getTravelDetail().getId(),context);
            if (photoName!=null) {
                imgDao.updateImg(userSqlSession, travel.getImg().getId(), photoName);
            }
            userSqlSession.commit();
        } catch (IOException e) {
            userSqlSession.rollback();
            e.printStackTrace();
        }finally {
            userSqlSession.close();
        }
    }

    public Integer getCount() {
        SqlSession userSqlSession = null;

        Integer count = null;
        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            count = travelDao.getCount(userSqlSession);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            userSqlSession.close();
        }
        return count;
    }
    //模糊查询
    public List<TTravel> getTTravelListByLike(String s,Integer page) {
        SqlSession userSqlSession = null;

        List<TTravel> listTTravelByLike = null;
        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
           listTTravelByLike = travelDao.getListTTravelByLike(userSqlSession, s, page);
            for (TTravel travel : listTTravelByLike) {
                travel = getTTravel(userSqlSession, travel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listTTravelByLike;
    }
    //封装TTravel类用
    private TTravel getTTravel(SqlSession userSqlSession,TTravel travel){
        User user = userDao.selUserById(userSqlSession,travel.getAuthour());
        travel.setRealAuthour(user);
        TravelDetail travelDetailByParentId = travelDetailDao.getTravelDetailByParentId(userSqlSession, travel.getId());
        travel.setTravelDetail(travelDetailByParentId);
        Img imgByParentId = imgDao.getImgByParentId(userSqlSession, travelDetailByParentId.getId());
        travel.setImg(imgByParentId);
        return travel;
    }

}
