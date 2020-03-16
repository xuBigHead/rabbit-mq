package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RabbitBean;
import com.example.mapper.RabbitBeanMapper;
import com.example.service.RabbitBeanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xmm
 * @since 2020/3/16
 */
@Service
@AllArgsConstructor
public class RabbitBeanServiceImpl extends ServiceImpl<RabbitBeanMapper, RabbitBean> implements RabbitBeanService {
}
