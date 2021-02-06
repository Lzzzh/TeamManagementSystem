package com.team.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.team.api.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User>{}
