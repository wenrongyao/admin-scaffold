package com.honor.e2b.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统参数表
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-01
 */
@TableName("sys_parameter")
@Data
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "parameter_id", type = IdType.AUTO)
    private Long parameterId;

    /**
     * 系统code
     */
    @TableField("code")
    private String code;

    /**
     * 内容
     */
    @TableField("value")
    private String value;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;

}
