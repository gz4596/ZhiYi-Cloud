package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.mall.v1.common.mapper.SocialDetailsMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.SocialDetailsService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminSocialDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocialDetailsServiceImpl extends ServiceImpl<SocialDetailsMapper, AdminSocialDetails> implements SocialDetailsService {

}
