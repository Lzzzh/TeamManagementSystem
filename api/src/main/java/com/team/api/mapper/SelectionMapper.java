package com.team.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.api.entity.Selection;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SelectionMapper extends BaseMapper<Selection> {
    int insertSelections(List<Selection> selectionList);

    List<Map<String, String>> getStudentListByProjectId(String projectId);
}
