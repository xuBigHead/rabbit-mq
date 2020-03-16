package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
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
    Date createTime;

    /**
     * 修改时间
     */
    Date updateTime;
}
