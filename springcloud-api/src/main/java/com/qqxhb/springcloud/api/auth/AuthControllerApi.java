package com.qqxhb.springcloud.api.auth;

import com.qqxhb.springcloud.domain.auth.LoginRequest;
import com.qqxhb.springcloud.domain.auth.LoginResult;
import com.qqxhb.springcloud.model.response.ResponseResult;

import io.swagger.annotations.ApiOperation;

public interface AuthControllerApi {
	@ApiOperation("退出接口")
	ResponseResult logout(String accessToken);

	@ApiOperation("登录接口")
	LoginResult login(LoginRequest loginRequest);


}
