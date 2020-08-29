package com.common.config.porvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.common.service.UserService;
/**
 * 自定义登录认证
 * @author LIZHAO
 * @date 2020年7月21日
 * @desc 描述
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    private UserService userService;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Override
    public Authentication authenticate(Authentication authentication) {
        //表单输入的用户名
        String username = (String) authentication.getPrincipal();
        //表单输入的密码
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userService.loadUserByUsername(username);
        //对加密密码进行验证
        /*if(passwordEncoder.matches(password,userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(username,password,null);
        }else {
            throw new BadCredentialsException("密码错误");
        }*/
        if(password.equals(userDetails.getPassword())){
        	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
        	usernamePasswordAuthenticationToken.setDetails(userDetails);
            return usernamePasswordAuthenticationToken;
        }else {
            throw new BadCredentialsException("密码错误");
        }
    }
 
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}