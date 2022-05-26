package com.wtu.travel.Dao;

import com.wtu.travel.Utils.UserMapperSqlSession;
import com.wtu.travel.bean.TravelDetail;
import com.wtu.travel.bean.TravelDetailExample;
import com.wtu.travel.mapper.TravelDetailMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class TravelDetailDao {

    public Integer AddOneTravelDetail(SqlSession userSqlSession,String context,Integer parentId) throws IOException {
        int insert = 0;
        TravelDetailMapper mapper = userSqlSession.getMapper(TravelDetailMapper.class);
        TravelDetailExample travelDetailExample = new TravelDetailExample();
        TravelDetail record = new TravelDetail();
        record.setContext(context);
        record.setParent(parentId);
        insert = mapper.insert(record);
        return record.getId();
    }

    public TravelDetail getTravelDetailByParentId(SqlSession userSqlSession,Integer parentId){
        TravelDetailMapper mapper = userSqlSession.getMapper(TravelDetailMapper.class);
        TravelDetailExample travelDetailExample = new TravelDetailExample();
        travelDetailExample.createCriteria().andParentEqualTo(parentId);
        List<TravelDetail> travelDetails = mapper.selectByExample(travelDetailExample);
        if (travelDetails.size()!=0) {
            return travelDetails.get(0);
        }else {
            return null;
        }
    }


    public void deleteTravelDetail(SqlSession userSqlSession,Integer id){
        TravelDetailMapper mapper = userSqlSession.getMapper(TravelDetailMapper.class);
        TravelDetailExample travelDetailExample = new TravelDetailExample();
        travelDetailExample.createCriteria().andIdEqualTo(id);
        mapper.deleteByExample(travelDetailExample);
    }

    public void updateTravelDetail(SqlSession userSqlSession,Integer id,String context){
        TravelDetailMapper mapper = userSqlSession.getMapper(TravelDetailMapper.class);
        TravelDetail travelDetail = new TravelDetail();
        travelDetail.setId(id);
        travelDetail.setContext(context);
        mapper.updateByPrimaryKeySelective(travelDetail);
    }


}
