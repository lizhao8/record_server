package com.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.common.base.service.BaseService;
import com.common.entity.User;
import com.common.mapper.MenuMapper;
import com.common.mapper.RoleMapper;

@Service
public class UserService extends BaseService<User> implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuMapper menuMapper;

	public User getByUserName(String username) {
		User user = new User();
		user.setUsername(username);
		return query(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		user.setRoleList(roleMapper.getByUserId(user.getId()));
		user.setMenuList(menuMapper.getByUserId(user.getId()));
		return user;
	}
}
