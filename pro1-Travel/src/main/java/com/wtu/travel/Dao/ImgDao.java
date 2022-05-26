package com.wtu.travel.Dao;

import com.wtu.travel.Utils.UserMapperSqlSession;
import com.wtu.travel.bean.Img;
import com.wtu.travel.bean.ImgExample;
import com.wtu.travel.mapper.ImgMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImgDao {

    public int addOneImg(SqlSession session, String imgName,Integer imgId){
        ImgMapper mapper = session.getMapper(ImgMapper.class);
        Img img = new Img();
        img.setAdress(imgName);
        img.setParent(imgId);
        int insert = mapper.insert(img);
        return insert;
    }

    public Img getImgByParentId(SqlSession session,Integer parentId){
        ImgMapper mapper = session.getMapper(ImgMapper.class);
        ImgExample imgExample = new ImgExample();
        imgExample.createCriteria().andParentEqualTo(parentId);
        List<Img> imgs = mapper.selectByExample(imgExample);
        if (imgs.size()!=0) {
            return imgs.get(0);
        }else {
            return null;
        }
    }

    public void deleteImgById(SqlSession session,Integer id){
        ImgMapper mapper = session.getMapper(ImgMapper.class);
        ImgExample imgExample = new ImgExample();
        imgExample.createCriteria().andIdEqualTo(id);
        mapper.deleteByExample(imgExample);
    }

    public void updateImg(SqlSession session,Integer id,String address){
        ImgMapper mapper = session.getMapper(ImgMapper.class);
        Img img = new Img();
        img.setId(id);
        img.setAdress(address);
        mapper.updateByPrimaryKeySelective(img);
    }
}
