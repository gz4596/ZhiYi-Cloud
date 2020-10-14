package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.mall.v1.admin.mapper.PostMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.PostService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminPost;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, AdminPost> implements PostService {

}
