package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author xmm
 * @since 2020/2/24
 */
@Data
@TableName("rabbit_bean")
public class RabbitBean {
    @TableId(type = IdType.ASSIGN_ID)
    Long id;

    /**
     * 名称
     */
    String name;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Date updateTime;
}
