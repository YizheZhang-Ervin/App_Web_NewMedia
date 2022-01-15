package com.ervin.newmediaspring.service;

import com.ervin.newmediaspring.dao.ArtifactDAO;
import com.ervin.newmediaspring.po.ArtifactPO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArtifactService {
    @Resource
    ArtifactDAO artifactDAO;

    public PageInfo<ArtifactPO> getAllArtifact(Integer current, Integer size){
        PageHelper.startPage(current,size);// 分页
        List<ArtifactPO> artifactPOList = artifactDAO.getAllArtifact(); // 查询
        PageInfo<ArtifactPO> pageInfo = new PageInfo<>(artifactPOList);
        return pageInfo;
    }

    public PageInfo<ArtifactPO> getArtifactByCondition(Integer current, Integer size,String condition){
        PageHelper.startPage(current,size);// 分页
        List<ArtifactPO> artifactPOList = artifactDAO.getArtifactByCondition(condition);
        PageInfo<ArtifactPO> pageInfo = new PageInfo<>(artifactPOList);
        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createArtifact(ArtifactPO artifact){
        artifactDAO.createArtifact(artifact);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateArtifact(ArtifactPO artifact){
        artifactDAO.updateArtifact(artifact);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteArtifact(Integer artifactId){
        artifactDAO.deleteArtifact(artifactId);
    }
}
