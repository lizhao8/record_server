package com.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

import javax.servlet.ServletContext;

import org.apache.http.util.CharArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class FileUtil {

	private static String projectPath = "";
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static boolean saveFile(InputStream inputStream, String tarFilePath) throws Exception {

		if (StringUtils.isNull(tarFilePath)) {
			logger.info("目标文件为空");
			return false;
		}
		if (inputStream == null || inputStream.available() <= 0) {
			logger.info("原文件流为空");
			return false;
		}
		File file = new File(tarFilePath);

		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (file.exists()) {
			file.delete();
		}

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(tarFilePath);
			byte[] buffer = new byte[1024 * 8];
			int len;
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			return Exsit(tarFilePath);
		} catch (Exception e) {
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static void checkFile(String filePath) {
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (file.exists()) {
			file.delete();
		}
	}

	public static boolean saveFile(String content, String tarFilePath) {
		if (content != null) {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
			try {
				return saveFile(byteArrayInputStream, tarFilePath);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(tarFilePath + "保存文件出错");
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean Exsit(String filePath) {
		return new File(filePath).exists();
	}

	public static String inputStreamToString(InputStream in) throws Exception {
		Reader reader = new InputStreamReader(in);
		CharArrayBuffer buffer = new CharArrayBuffer(in.available());
		char[] tmp = new char[1024];
		int l;
		while ((l = reader.read(tmp)) != -1) {
			buffer.append(tmp, 0, l);
		}
		return buffer.toString();
	}

	public static String getProjectPath() {
		if (StringUtils.isNotNull(projectPath)) {
			return projectPath;
		}
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		projectPath = servletContext.getRealPath("");
		if (StringUtils.isNotNull(projectPath)) {
			logger.info("获取项目路径=" + projectPath);
			return projectPath;
		}
		return null;
	}
}
