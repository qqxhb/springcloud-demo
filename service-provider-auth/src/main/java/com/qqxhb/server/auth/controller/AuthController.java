package com.qqxhb.server.auth.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qqxhb.server.auth.service.AuthService;
import com.qqxhb.springcloud.api.auth.AuthControllerApi;
import com.qqxhb.springcloud.domain.auth.AuthCode;
import com.qqxhb.springcloud.domain.auth.AuthToken;
import com.qqxhb.springcloud.domain.auth.LoginRequest;
import com.qqxhb.springcloud.domain.auth.LoginResult;
import com.qqxhb.springcloud.exception.ExceptionCast;
import com.qqxhb.springcloud.model.response.CommonCode;
import com.qqxhb.springcloud.model.response.ResponseResult;
import com.qqxhb.springcloud.utils.CookieUtil;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerApi {

	@Value("${auth.clientId}")
	String clientId;
	@Value("${auth.clientSecret}")
	String clientSecret;
	@Value("${auth.cookieDomain}")
	String cookieDomain;
	@Value("${auth.cookieMaxAge}")
	int cookieMaxAge;

	@Autowired
	AuthService authService;

	@Override
	@PostMapping("/userlogin")
	public LoginResult login(LoginRequest loginRequest) {
		if (loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())) {
			ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
		}
		if (loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())) {
			ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
		}
		// 账号
		String username = loginRequest.getUsername();
		// 密码
		String password = loginRequest.getPassword();

		// 申请令牌
		AuthToken authToken = authService.login(username, password, clientId, clientSecret);

		// 用户身份令牌
		String access_token = authToken.getAccess_token();
		// 将令牌存储到cookie
		this.saveCookie(access_token);

		return new LoginResult(CommonCode.SUCCESS, access_token);
	}

	// 将令牌存储到cookie
	private void saveCookie(String token) {

		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		CookieUtil.addCookie(response, cookieDomain, "/", "jti", token, cookieMaxAge, false);

	}

	@Override
	@PostMapping("/userlogout")
	public ResponseResult logout(String accessToken) {
		if (authService.logout(accessToken)) {
			return ResponseResult.SUCCESS();
		}
		return ResponseResult.FAIL();
	}
}
