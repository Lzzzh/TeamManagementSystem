package com.team.api.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.api.dto.ClassDto;
import com.team.api.dto.PageDto;
import com.team.api.dto.ProjectDto;
import com.team.api.entity.Project;
import com.team.api.entity.Result;
import com.team.api.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation("新增项目")
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public Result addProject(@RequestBody ProjectDto projectDto) {
        HanyuPinyinOutputFormat stringFormat = new HanyuPinyinOutputFormat();
        //拼音小写
        stringFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //不带声调
        stringFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            String pinYin = PinyinHelper.toHanYuPinyinString(projectDto.getProject().getProjectName(), stringFormat, "", true);
            String dateFormat = DateUtil.format(projectDto.getProject().getCreateTime(), "yyyyMM");
            projectDto.getProject().setProjectId(dateFormat + pinYin);
            projectDto.getProject().setCreateTime(projectDto.getProject().getCreateTime());
            boolean res = projectService.addProject(projectDto);
            if (res) {
                return Result.success("新增成功！", projectDto);
            }else {
                return Result.fail("新增失败!", "");
            }
        }catch (Exception e) {
            log.error("新增失败");
            return Result.fail("新增失败!", "");
        }
    }

    @ApiOperation("删除项目")
    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    public Result deleteProject(@RequestBody Project project) {
        try {
            boolean res = projectService.deleteProject(project);
            if (res) {
                return Result.success("删除成功！", project);
            }else {
                return Result.fail("删除失败！", "");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return Result.fail("删除失败！", "");
        }
    }

    @ApiOperation("修改项目")
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public Result updateProject(@RequestBody ProjectDto projectDto) {
        boolean res = projectService.updateProject(projectDto);
        try {
            if (res) {
                return Result.success("更新成功！", projectDto);
            }else {
                return Result.fail("更新失败！", "");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return Result.fail("更新失败！", "");
        }

    }

    @ApiOperation("学生端查询项目列表")
    @RequestMapping(value = "/studentProjectList", method = RequestMethod.POST)
    public Result studentProjectList(@RequestBody PageDto pageDto) {
        IPage<Project> projectList = projectService.getStudentProjectList(
                new Page<>(pageDto.getPageIndex(), pageDto.getPageSize()),
                pageDto.getUserId(), pageDto.getSearchText());
        return Result.success("查询成功！", projectList);
    }

    @ApiOperation("教师端查询项目列表")
    @RequestMapping(value = "/teacherProjectList", method = RequestMethod.POST)
    public Result teacherProjectList(@RequestBody PageDto pageDto) {
        IPage<Project> projectList = projectService.getTeacherProjectList(
                new Page<>(pageDto.getPageIndex(), pageDto.getPageSize()),
                pageDto.getUserId(), pageDto.getSearchText());
        return Result.success("查询成功！", projectList);
    }

    @ApiOperation("查询学生列表")
    @RequestMapping(value = "/studentList", method = RequestMethod.GET)
    public Result studentList(){
        List<ClassDto> classDtoList = projectService.getStudentList();
        return Result.success("查询成功！", classDtoList);
    }

    @ApiOperation("查询项目相关的学生列表")
    @RequestMapping(value = "/studentListByProject", method = RequestMethod.GET)
    public Result studentProjectList(@RequestParam(value = "projectId") String projectId){
        List<Map<String, String>> studentProjectList = projectService.getStudentListByProjectId(projectId);
        return Result.success("查询成功！", studentProjectList);
    }
}
