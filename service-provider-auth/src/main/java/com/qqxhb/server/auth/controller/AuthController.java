package com.qqxhb.server.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.qqxhb.springcloud.domain.auth.JwtResult;
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
		CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false);

	}

	@Override
	@PostMapping("/userlogout")
	public ResponseResult logout() {
		// 取出身份令牌
		String uid = getTokenFormCookie();
		// 删除redis中token
		authService.logout(uid);
		// 清除cookie
		clearCookie(uid);
		return new ResponseResult(CommonCode.SUCCESS);
	}

	// 从cookie中读取访问令牌
	private String getTokenFormCookie() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Map<String, String> cookieMap = CookieUtil.readCookie(request, "uid");
		String access_token = cookieMap.get("uid");
		return access_token;
	}

	// 清除cookie
	private void clearCookie(String token) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, 0, false);
	}

	@Override
	public JwtResult userjwt() {
		// 取出cookie中的用户身份令牌
		String uid = getTokenFormCookie();
		if (uid == null) {
			return new JwtResult(CommonCode.FAIL, null);
		}

		// 拿身份令牌从redis中查询jwt令牌
		AuthToken userToken = authService.getUserToken(uid);
		if (userToken != null) {
			// 将jwt令牌返回给用户
			String jwt_token = userToken.getJwt_token();
			return new JwtResult(CommonCode.SUCCESS, jwt_token);
		}
		return new JwtResult(CommonCode.FAIL, null);
	}
}
