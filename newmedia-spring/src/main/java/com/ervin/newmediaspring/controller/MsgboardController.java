package com.ervin.newmediaspring.controller;

import com.ervin.newmediaspring.po.MsgboardPO;
import com.ervin.newmediaspring.service.MsgboardService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MsgboardController {
    @Resource
    private MsgboardService msgboardService;

    @GetMapping("msgboard")
    public PageInfo<MsgboardPO> getAllMsgboard(@RequestParam(value="current", required=false, defaultValue = "1") Integer current,
                                               @RequestParam(value="size", required=false, defaultValue = "10") Integer size){
        PageInfo<MsgboardPO> msgboardPageInfo;
        msgboardPageInfo = msgboardService.getAllMsgboard(current,size);
        return msgboardPageInfo;
    }

    @PostMapping("msgboard")
    public Map<String,Object> createMsgboard(@RequestBody MsgboardPO msgboardPO){
        msgboardService.createMsgboard(msgboardPO);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }

    @PutMapping("msgboard")
    public Map<String,Object> updateMsgboard(@RequestBody MsgboardPO msgboardPO){
        msgboardService.updateMsgboard(msgboardPO);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }

    @DeleteMapping("msgboard")
    public Map<String,Object> deleteMsgboard(@RequestParam(value="id", required=true) Integer msgboardId){
        msgboardService.deleteMsgboard(msgboardId);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }
}
