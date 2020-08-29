package com.common.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class User extends BaseEntity implements UserDetails {
	@Transient
	private static final long serialVersionUID = 4235434656767845646L;
	@NotBlank
	private String username; // 用户名
	@NotBlank
	private String password; // 密码

	private String realName; // 真实姓名
	private String phoneNo; // 手机号
	private String email; // 邮箱
	private String lastLoginIp; // 最后一次登录IP
	private Date lastLoginTime; // 最后一次登录时间
	@Transient
	private String sessionId;
	@Transient
	@JsonIgnore
	private List<Role> roleList = new ArrayList<Role>();
	@Transient
	@JsonIgnore
	private List<Menu> menuList = new ArrayList<Menu>();

	public String toJsonString() {
		return this.toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roleList) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.getStatus() == BaseEntity.ENABLE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getStatus() == BaseEntity.DISABLE;
	}

	public List<String> getRoles() {
		return roleList.stream().map(x -> new String(x.getName())).collect(Collectors.toList());
	}

	public List<Menu> getMenus() {
		return menuList;
	}
}
