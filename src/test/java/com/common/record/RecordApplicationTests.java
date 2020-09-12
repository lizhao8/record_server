package com.common.record;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.record.controller.AnchorController;

@SpringBootTest
class RecordApplicationTests {
	@Autowired
	AnchorController anchorController;

	@Test
	public void contextLoads() {
		//int uid = 407044808;
		int uid = 745493;
		anchorController.save(uid);
	}
}
