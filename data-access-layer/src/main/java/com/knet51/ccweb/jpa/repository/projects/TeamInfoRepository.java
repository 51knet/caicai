package com.knet51.ccweb.jpa.repository.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.entities.projects.TeamInfo;

public interface TeamInfoRepository extends JpaRepository<TeamInfo, Long>, JpaSpecificationExecutor<TeamInfo> {
	TeamInfo findTeamInfoByProjectsId(Long id);
}
