package com.team.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.api.entity.Result;
import com.team.api.entity.Todo;
import com.team.api.service.TodoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @ApiOperation("更新待办清单")
    @RequestMapping("/updateTodoList")
    public Result updateTodoList(@RequestBody List<Todo> todoList) {
        if (!todoList.isEmpty()) {
            String userId = todoList.get(0).getUserId();
            boolean removeResult = todoService.remove(new QueryWrapper<Todo>().eq("USER_ID", userId));
            boolean insertResult = todoService.saveBatch(todoList);
            if (removeResult && insertResult) {
                return Result.success("更新成功！", todoList);
            }else {
                return Result.fail("更新失败！", "");
            }
        }else {
            return Result.fail("列表为空！", "");
        }

    }

    @ApiOperation("获取待办清单")
    @RequestMapping("/getTodoList")
    public Result getTodoList(@RequestParam String userId) {
        List<Todo> todoList = todoService.list(new QueryWrapper<Todo>().eq("USER_ID", userId));
        return Result.success("查询成功！", todoList);
    }
}
