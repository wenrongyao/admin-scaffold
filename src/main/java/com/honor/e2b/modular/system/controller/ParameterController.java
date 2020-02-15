package com.honor.e2b.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honor.e2b.core.common.annotion.BussinessLog;
import com.honor.e2b.core.common.annotion.Permission;
import com.honor.e2b.core.common.constant.dictmap.DeleteDict;
import com.honor.e2b.core.common.constant.dictmap.NoticeMap;
import com.honor.e2b.core.common.constant.dictmap.ParameterMap;
import com.honor.e2b.core.common.constant.factory.ConstantFactory;
import com.honor.e2b.core.common.exception.BizExceptionEnum;
import com.honor.e2b.core.common.page.LayuiPageFactory;
import com.honor.e2b.core.log.LogObjectHolder;
import com.honor.e2b.core.shiro.ShiroKit;
import com.honor.e2b.modular.system.entity.Notice;
import com.honor.e2b.modular.system.entity.Parameter;
import com.honor.e2b.modular.system.service.ParameterService;
import com.honor.e2b.modular.system.warpper.NoticeWrapper;
import com.honor.e2b.modular.system.warpper.ParameterWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by wen rongyao
 * on 2020/2/15.
 * 系统参数
 */
@Controller
@RequestMapping("/parameter")
public class ParameterController extends BaseController {
    private String PREFIX = "/modular/system/parameter/";

    @Autowired
    private ParameterService parameterService;

    /**
     * 跳转到系统参数首页
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "parameter.html";
    }

    /**
     * 系统参数添加页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/parameter_add")
    public String parameterAdd() {
        return PREFIX + "parameter_add.html";
    }

    /**
     * 系统参数修改页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/parameter_update/{parameterId}")
    public String parameterUpdate(@PathVariable Long parameterId, Model model) {
        Parameter parameter = this.parameterService.getById(parameterId);
        model.addAllAttributes(BeanUtil.beanToMap(parameter));
        LogObjectHolder.me().set(parameter);
        return PREFIX + "parameter_edit.html";
    }

    /**
     * 获取通知列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    @Permission
    public Object list(String condition) {
        Page<Map<String, Object>> list = this.parameterService.list(condition);
        Page<Map<String, Object>> wrap = new ParameterWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增系统参数
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增系统参数", key = "code", dict = ParameterMap.class)
    public Object add(Parameter parameter) {
        if (ToolUtil.isOneEmpty(parameter, parameter.getCode(), parameter.getValue())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        parameter.setCreateUser(ShiroKit.getUserNotNull().getId());
        parameter.setCreateTime(new Date());
        this.parameterService.save(parameter);
        return SUCCESS_TIP;
    }

    /**
     * 修改系统参数
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改系统参数", key = "code", dict = ParameterMap.class)
    public Object update(Parameter parameter) {
        if (ToolUtil.isOneEmpty(parameter, parameter.getCode(), parameter.getValue())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Parameter old = this.parameterService.getById(parameter.getParameterId());
        old.setCode(parameter.getCode());
        old.setValue(parameter.getValue());
        this.parameterService.updateById(old);
        return SUCCESS_TIP;
    }

    /**
     * 删除系统参数
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除系统参数", key = "parameterId", dict = ParameterMap.class)
    public Object delete(@RequestParam Long parameterId) {
        this.parameterService.removeById(parameterId);
        return SUCCESS_TIP;
    }
}
