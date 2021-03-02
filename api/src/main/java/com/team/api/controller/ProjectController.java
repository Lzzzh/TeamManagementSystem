package com.team.api.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.api.dto.PageDto;
import com.team.api.dto.ProgressDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("学生端主页查询项目进度")
    @RequestMapping(value = "studentProgress", method = RequestMethod.GET)
    public Result studentProgress(@RequestParam String userId){
        List<ProgressDto> progressDtoList = projectService.getStudentProgress(userId);
        return Result.success("查询成功！", progressDtoList);
    }

    @ApiOperation("教师端主页查询项目进度")
    @RequestMapping(value = "/teacherProgress", method = RequestMethod.GET)
    public Result teacherProgress(@RequestParam String userId){
        List<ProgressDto> progressList = projectService.getTeacherProgress(userId);
        return Result.success("查询成功！", progressList);
    }





}
