package com.record.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import com.common.base.exception.RunException;
import com.common.util.FileUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Data
public class RecordUtil {

	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();

	private RequestConfig requestConfig;

	private final int buffSize = 1024 * 1024;

	private int size = 0;

	private boolean stop = false;

	/**
	 * 
	 * @param url
	 * @param params
	 * @param encode
	 * @return
	 */
	public String record(String url, String filePath) {
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			// httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				InputStream inputStream = entity.getContent();
				FileUtil.checkFile(filePath);

				FileOutputStream fileOutputStream = new FileOutputStream(filePath);

				byte[] buffer = new byte[buffSize];
				int length = 0;
				while ((length = inputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, length);
					size += length;
					if (stop) {
						break;
					}
				}
				//inputStream.close();
				fileOutputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public void stop() {
		this.stop = true;
	}

}