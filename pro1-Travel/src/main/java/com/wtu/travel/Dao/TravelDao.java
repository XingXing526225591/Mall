package com.wtu.travel.Dao;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wtu.travel.Utils.UserMapperSqlSession;
import com.wtu.travel.bean.TTravel;
import com.wtu.travel.bean.TTravelExample;
import com.wtu.travel.mapper.TTravelMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Repository
public class TravelDao {

    public int addOneTravel(SqlSession userSqlSession,String title, String region, Integer authourId, Date date) throws IOException {
        TTravelMapper mapper = userSqlSession.getMapper(TTravelMapper.class);
        TTravel record = new TTravel();
        record.setTitle(title);
        record.setRegion(region);
        record.setAuthour(authourId);
        record.setDate(date);
        mapper.insert(record);
        return record.getId();
    }

    public List<TTravel> getAllTTravel(SqlSession userSqlSession,Integer page){
        TTravelMapper mapper = userSqlSession.getMapper(TTravelMapper.class);
        TTravelExample tTravelExample = new TTravelExample();
        tTravelExample.createCriteria();
        PageHelper.startPage(page,3);
        List<TTravel> tTravels = mapper.selectByExample(tTravelExample);
        return tTravels;
    }
    public List<TTravel> getAllTTravelByUserId(SqlSession userSqlSession,Integer page,Integer id){
        TTravelMapper mapper = userSqlSession.getMapper(TTravelMapper.class);
        TTravelExample tTravelExample = new TTravelExample();
        tTravelExample.createCriteria().andAuthourEqualTo(id);
        PageHelper.startPage(page,3);
        List<TTravel> tTravels = mapper.selectByExample(tTravelExample);
        return tTravels;
    }

    public TTravel getTTravelById(SqlSession userSqlSession,Integer id){
        TTravelMapper mapper = userSqlSession.getMapper(TTravelMapper.class);
        TTravelExample tTravelExample = new TTravelExample();
        tTravelExample.createCriteria().andIdEqualTo(id);
        List<TTravel> tTravels = mapper.selectByExample(tTravelExample);
        if (tTravels.size()!=0) {
            return tTravels.get(0);
        }else {
            return null;
        }
    }

    public void deleteTTravelById(SqlSession userSqlSession,Integer id){
        TTravelMapper mapper = userSqlSession.getMapper(TTravelMapper.class);
        TTravelExample tTravelExample = new TTravelExample();
        tTravelExample.createCriteria().andIdEqualTo(id);
        mapper.deleteByExample(tTravelExample);
    }

    public void updateTravelById(SqlSession userSqlSession,Integer id,String title,String region){
        TTravelMapper mapper = userSqlSession.getMapper(TTravelMapper.class);
        TTravel travel = new TTravel();
        travel.setId(id);
        travel.setRegion(region);
        travel.setTitle(title);
        travel.setDate(new Date());
        mapper.updateByPrimaryKeySelective(travel);
    }

    public Integer getCount(SqlSession userSqlSession){
        TTravelMapper mapper = userSqlSession.getMapper(TTravelMapper.class);
        TTravelExample tTravelExample = new TTravelExample();
        tTravelExample.createCriteria();
        List<TTravel> tTravels = mapper.selectByExample(tTravelExample);
        return tTravels.size();
    }

    public List<TTravel> getListTTravelByLike(SqlSession userSqlSession,String s,Integer page){
        TTravelMapper mapper = userSqlSession.getMapper(TTravelMapper.class);
        TTravelExample tTravelExample = new TTravelExample();
        tTravelExample.or().andTitleLike("%" + s + "%");
        tTravelExample.or().andRegionLike( "%" + s + "%");
        PageHelper.startPage(page,3);
        List<TTravel> tTravels = mapper.selectByExample(tTravelExample);
        return tTravels;
    }
}
