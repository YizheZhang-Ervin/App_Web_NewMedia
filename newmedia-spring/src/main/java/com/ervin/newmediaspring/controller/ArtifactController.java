package com.ervin.newmediaspring.controller;

import com.ervin.newmediaspring.po.ArtifactPO;
import com.ervin.newmediaspring.service.ArtifactService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ArtifactController {
    @Resource
    private ArtifactService artifactService;

    // 分页查的接口
    @GetMapping("artifact")
    public PageInfo<ArtifactPO> getAllArtifact(@RequestParam(value="current", required=false,defaultValue = "1") Integer current,
                                               @RequestParam(value="size", required=false,defaultValue = "10") Integer size,
                                               @RequestParam(value="condition",required = false) String condition){
        PageInfo<ArtifactPO> artifactPageInfo;
        if(null!=condition){
            artifactPageInfo = artifactService.getArtifactByCondition(current,size,condition);
        }else{
            artifactPageInfo = artifactService.getAllArtifact(current,size);
        }
        return artifactPageInfo;
    }

    @PostMapping("artifact")
    public Map<String,Object> createArtifact(@RequestBody ArtifactPO artifactPO){
        artifactService.createArtifact(artifactPO);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }

    @PutMapping("artifact")
    public Map<String,Object> updateArtifact(@RequestBody ArtifactPO artifactPO){
        artifactService.updateArtifact(artifactPO);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }

    @DeleteMapping("artifact")
    public Map<String,Object> deleteArtifact(@RequestParam(value="id", required=true) Integer artifactId){
        artifactService.deleteArtifact(artifactId);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }
}
