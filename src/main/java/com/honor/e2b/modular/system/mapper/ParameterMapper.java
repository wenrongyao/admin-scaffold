package com.honor.e2b.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honor.e2b.modular.system.entity.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by wen rongyao
 * on 2020/2/15.
 */
public interface ParameterMapper extends BaseMapper<Parameter> {
    /**
     * 获取系统参数
     *
     * @param page
     * @param condition
     * @return
     */
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);
}

