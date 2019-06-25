package com.qqxhb.server.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import com.qqxhb.server.auth.vo.UserJwt;

import lombok.Data;
import lombok.ToString;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ClientDetailsService clientDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 取出身份，如果身份为空说明没有认证
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
		if (authentication == null) {
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
			if (clientDetails != null) {
				// 密码
				String clientSecret = clientDetails.getClientSecret();
				return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
			}
		}
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		UserInfo userInfo = loadUserByName(username);
		if (userInfo == null) {
			return null;
		}
		// 取出正确密码（hash值）
		String password = userInfo.getPassword();
		// 从数据库获取权限
		List<String> permissions = userInfo.getPermissions();
		String user_permission_string = StringUtils.join(permissions.toArray(), ",");
		UserJwt userDetails = new UserJwt(username, password,
				AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
		userDetails.setId(userInfo.getId());
		userDetails.setUtype(userInfo.getUtype());// 用户类型
		userDetails.setName(userInfo.getNickname());// 用户名称
		userDetails.setAvatar(userInfo.getAvatar());
		return userDetails;
	}

	/**
	 * 模拟根据用户名查询用信息 密码admin
	 * 
	 * 
	 * @param userName
	 * @return
	 */
	private UserInfo loadUserByName(String userName) {
		UserInfo userInfo = new UserInfo();
		userInfo.setAvatar("http://localhost/static/1.png");
		userInfo.setId("66666666");
		userInfo.setNickname("昵称");
		userInfo.setPassword(new BCryptPasswordEncoder().encode("admin"));
		userInfo.setUsername(userName);
		userInfo.setUtype("系统用户");
		userInfo.setPermissions(new ArrayList<String>());
		return userInfo;

	}

	@Data
	@ToString
	private static class UserInfo {
		// 权限信息
		private List<String> permissions;
		private String id;
		private String username;
		private String password;
		private String utype;
		private String nickname;
		private String avatar;

	}
}
