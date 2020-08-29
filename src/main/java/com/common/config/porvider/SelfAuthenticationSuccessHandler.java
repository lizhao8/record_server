package com.common.config.porvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.common.base.entity.Result;
/**
 * 自定义登陆成功处理器
 * @author LIZHAO
 * @date 2020年7月21日
 * @desc 描述
 */
@Component
public class SelfAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication e) throws IOException {        
    	
    	List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = e.getAuthorities();
        for (GrantedAuthority authority : authorities){
            roles.add(authority.getAuthority());
        }
    	String token = TokenUtils.createToken(e.getPrincipal().toString(),roles,true);
    	Map<String, String> map=new HashMap<String, String>();    	
    	map.put("token", token);
    	Result.SUCCESS("登陆成功",map).writerToResponse(response);
    }
}