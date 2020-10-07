package com.github.taoroot.cloud.mall.v1.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.MallUser;

public interface UserService extends IService<MallUser> {

    R userInfo();
}
