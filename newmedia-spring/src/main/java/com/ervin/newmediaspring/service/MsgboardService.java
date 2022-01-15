package com.ervin.newmediaspring.service;

import com.ervin.newmediaspring.dao.MsgboardDAO;
import com.ervin.newmediaspring.po.MsgboardPO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MsgboardService {
    @Resource
    MsgboardDAO msgboardDAO;

    public PageInfo<MsgboardPO> getAllMsgboard(Integer current, Integer size){
        PageHelper.startPage(current,size);// 分页
        List<MsgboardPO> msgboardPOList = msgboardDAO.getAllMsgboard(); // 查询
        PageInfo<MsgboardPO> pageInfo = new PageInfo<>(msgboardPOList);
        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createMsgboard(MsgboardPO msgboard){
        msgboardDAO.createMsgboard(msgboard);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMsgboard(MsgboardPO msgboard){
        msgboardDAO.updateMsgboard(msgboard);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMsgboard(Integer msgboardId){
        msgboardDAO.deleteMsgboard(msgboardId);
    }
}
