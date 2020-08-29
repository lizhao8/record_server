package com.record.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RecordProperty {

	public static String fileBasePath = null;

	@Value("${record.file.base.path}")
	public void setFileBasePath(String fileBasePath) {
		RecordProperty.fileBasePath = fileBasePath;
	}
}
