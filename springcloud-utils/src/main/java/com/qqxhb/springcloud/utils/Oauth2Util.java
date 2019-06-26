package com.qqxhb.springcloud.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.ToString;

/**
 * Created by mrt on 2018/5/25.
 */
public class Oauth2Util {

	public static Map<String, String> getJwtClaimsFromHeader(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		// 取出头信息
		String authorization = request.getHeader("Authorization");
		if (StringUtils.isEmpty(authorization) || authorization.indexOf("Bearer") < 0) {
			return null;
		}
		// 从Bearer 后边开始取出token
		String token = authorization.substring(7);
		Map<String, String> map = null;
		try {
			// 解析jwt
			Jwt decode = JwtHelper.decode(token);
			// 得到 jwt中的用户信息
			String claims = decode.getClaims();
			// 将jwt转为Map
			map = JSON.parseObject(claims, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public UserJwt getUserJwtFromHeader(HttpServletRequest request) {
		Map<String, String> jwtClaims = getJwtClaimsFromHeader(request);
		if (jwtClaims == null || StringUtils.isEmpty(jwtClaims.get("id"))) {
			return null;
		}
		UserJwt userJwt = new UserJwt();
		userJwt.setId(jwtClaims.get("id"));
		userJwt.setName(jwtClaims.get("name"));
		userJwt.setUtype(jwtClaims.get("utype"));
		userJwt.setAvatar(jwtClaims.get("avatar"));
		return userJwt;
	}

	@Data
	@ToString
	public class UserJwt {

		private String id;
		private String name;
		private String avatar;
		private String utype;

	}
}
