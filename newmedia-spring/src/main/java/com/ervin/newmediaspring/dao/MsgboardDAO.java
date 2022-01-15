package com.ervin.newmediaspring.dao;

import com.ervin.newmediaspring.po.MsgboardPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgboardDAO {
    List<MsgboardPO> getAllMsgboard();

    void createMsgboard(MsgboardPO msgboardPO);

    void updateMsgboard(MsgboardPO msgboardPO);

    void deleteMsgboard(Integer msgboardId);
}
