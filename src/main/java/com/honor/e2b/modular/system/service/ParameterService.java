package com.honor.e2b.modular.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honor.e2b.core.common.page.LayuiPageFactory;
import com.honor.e2b.modular.system.entity.Notice;
import com.honor.e2b.modular.system.entity.Parameter;
import com.honor.e2b.modular.system.mapper.NoticeMapper;
import com.honor.e2b.modular.system.mapper.ParameterMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 通知系统参数
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class ParameterService extends ServiceImpl<ParameterMapper, Parameter> {

    /**
     * 获取系统参数
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:05 PM
     */
    public Page<Map<String, Object>> list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, condition);
    }
}
