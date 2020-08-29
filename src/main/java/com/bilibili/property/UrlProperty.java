package com.bilibili.property;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.common.util.ReflectionUtils;
import com.common.util.SpringUtils;
import com.record.entity.Url;
import com.record.mapper.UrlMapper;
import com.record.service.UrlService;

@Component
@DependsOn("springUtils")
public class UrlProperty {
	public static String getUserInfo;

	public static String getRoomInfoOld;

	public static String getInfoByRoom;

	public static String getRoomPlayInfo;
	
	@PostConstruct
	public void init() {
		UrlService urlService = SpringUtils.getBean(UrlService.class);
		List<Url> list = urlService.queryListEnable();
		Map<String, Object> map = list.stream().collect(Collectors.toMap(Url::getName, Url::getUrl));
		ReflectionUtils.setStaticFiled(UrlProperty.class, map);
	}
}
