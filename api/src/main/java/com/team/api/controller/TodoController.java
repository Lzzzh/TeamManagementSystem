package com.team.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.api.entity.Result;
import com.team.api.entity.Todo;
import com.team.api.service.TodoService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @ApiOperation("更新待办清单")
    @RequestMapping(value = "/updateTodoList", method = RequestMethod.POST)
    public Result updateTodoList(@RequestBody List<Todo> todoList) {
        if (!todoList.isEmpty()) {
            String userId = todoList.get(0).getUserId();
            boolean removeResult = todoService.remove(new QueryWrapper<Todo>().eq("USER_ID", userId));
            List<Todo> list = new ArrayList<>();
            for (Todo todo: todoList) {
                if (!StringUtils.isEmpty(todo.getContent())){
                    if (StringUtils.isEmpty(todo.getTodoId())) {
                        UUID uuid = UUID.randomUUID();
                        todo.setTodoId(uuid.toString());
                    }
                    list.add(todo);
                }
            }
            boolean insertResult = todoService.saveBatch(list);
            if (removeResult && insertResult) {
                return Result.success("更新成功！", list);
            }else {
                return Result.fail("更新失败！", "");
            }
        }else {
            return Result.fail("列表为空！", "");
        }

    }

    @ApiOperation("获取待办清单")
    @RequestMapping(value = "/getTodoList", method = RequestMethod.GET)
    public Result getTodoList(@RequestParam String userId) {
        List<Todo> todoList = todoService.list(new QueryWrapper<Todo>().eq("USER_ID", userId));
        return Result.success("查询成功！", todoList);
    }

    @ApiOperation("设置单条待办状态")
    @RequestMapping(value = "/setStatus", method = RequestMethod.GET)
    public Result setStatus(@RequestParam String todoId, @RequestParam Boolean status) {
        Todo todo = new Todo();
        todo.setStatus(status);
        boolean res = todoService.update(todo, new QueryWrapper<Todo>().eq("TODO_ID", todoId));
        if (res) {
            return Result.success("更新成功", todoId);
        }else {
            return Result.fail("更新失败！", "");
        }
    }

    @ApiOperation("删除单条待办")
    @RequestMapping(value = "/deleteTodo", method = RequestMethod.GET)
    public Result deleteTodo(@RequestParam String todoId) {
        boolean res = todoService.remove(new QueryWrapper<Todo>().eq("TODO_ID", todoId));
        if (res) {
            return Result.success("删除成功！", todoId);
        }else {
            return Result.fail("删除失败！", "");
        }
    }
}
