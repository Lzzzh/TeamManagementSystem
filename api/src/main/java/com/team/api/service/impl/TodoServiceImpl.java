package com.team.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.api.entity.Todo;
import com.team.api.mapper.TodoMapper;
import com.team.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements TodoService {




}
