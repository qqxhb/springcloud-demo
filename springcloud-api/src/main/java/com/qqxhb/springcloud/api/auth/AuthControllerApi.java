package com.qqxhb.springcloud.api.auth;

import com.qqxhb.springcloud.domain.auth.JwtResult;
import com.qqxhb.springcloud.domain.auth.LoginRequest;
import com.qqxhb.springcloud.domain.auth.LoginResult;
import com.qqxhb.springcloud.model.response.ResponseResult;

import io.swagger.annotations.ApiOperation;

public interface AuthControllerApi {
	@ApiOperation("退出接口")
	ResponseResult logout();

	@ApiOperation("登录接口")
	LoginResult login(LoginRequest loginRequest);

	@ApiOperation("查询userjwt令牌")
    public JwtResult userjwt();
}
