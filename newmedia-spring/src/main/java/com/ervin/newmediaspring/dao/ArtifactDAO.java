package com.ervin.newmediaspring.dao;

import com.ervin.newmediaspring.po.ArtifactPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtifactDAO {
    List<ArtifactPO> getAllArtifact();

    List<ArtifactPO> getArtifactByCondition(String condition);

    void createArtifact(ArtifactPO artifactPO);

    void updateArtifact(ArtifactPO artifactPO);

    void deleteArtifact(Integer artifactId);
}