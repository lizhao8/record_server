package com.bilibili.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.base.exception.RunException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiClient {

	public static CloseableHttpClient httpClient;

	public static RequestConfig requestConfig;

	@Autowired
	public void setHttpClient(CloseableHttpClient httpClient) {
		ApiClient.httpClient = httpClient;
	}

	@Autowired
	public void setRequestConfig(RequestConfig requestConfig) {
		ApiClient.requestConfig = requestConfig;
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @param encode
	 * @return
	 */
	public static String get(String url, Map<String, Object> params, String encode) {
		CloseableHttpResponse response = null;
		try {
			log.info("执行GET请求，URL = {}", url);

			if (null != params) {
				URIBuilder builder = new URIBuilder(url);
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					builder.setParameter(entry.getKey(),
							entry.getValue() == null ? "" : entry.getValue().toString());
				}
				url = builder.build().toString();
			}
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				if (encode == null) {
					encode = "UTF-8";
				}
				return EntityUtils.toString(response.getEntity(), encode);
			}
		} catch (Exception e) {
			throw new RunException(e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw new RunException(e);
				}
			}
		}
		return null;
	}

	public static String get(String url, String encode) {
		return get(url, null, encode);
	}

	public static String get(String url) {
		return get(url, null, null);
	}

	public static String get(String url, Map<String, Object> params) {
		return get(url, params, null);
	}
}