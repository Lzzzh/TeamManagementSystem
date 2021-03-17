package com.team.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.api.entity.Photo;
import com.team.api.mapper.PhotoMapper;
import com.team.api.service.PhotoService;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {
}
