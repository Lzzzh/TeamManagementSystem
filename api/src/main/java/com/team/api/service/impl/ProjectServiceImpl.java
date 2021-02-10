package com.team.api.service.impl;

import com.team.api.mapper.ProjectMapper;
import com.team.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;


}
