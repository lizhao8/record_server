use record;
INSERT INTO `menu` (`id`, `name`, `label`, `sequence`, `parent_id`, `url`, `icon`, `status`) VALUES (1, 'system', '系统管理', 100000, 0, '', NULL, 1);
INSERT INTO `menu` (`id`, `name`, `label`, `sequence`, `parent_id`, `url`, `icon`, `status`) VALUES (2, 'user', '用户管理', 100001, 1, '', NULL, 1);


INSERT INTO `role` (`id`, `name`, `label`, `description`, `status`) VALUES (1, 'admin', '系统管理员', '系统管理员', 1);
INSERT INTO `role` (`id`, `name`, `label`, `description`, `status`) VALUES (2, 'config', '配置管理', '修改系统配置', 1);


INSERT INTO `user` (`id`, `username`, `password`, `real_name`, `phone_no`, `email`, `status`, `last_login_ip`, `last_login_time`) VALUES (1, 'admin', '123456', 'admin', NULL, NULL, 1, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `real_name`, `phone_no`, `email`, `status`, `last_login_ip`, `last_login_time`) VALUES (2, 'lz', '123456', 'lz', NULL, NULL, 1, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `real_name`, `phone_no`, `email`, `status`, `last_login_ip`, `last_login_time`) VALUES (4, '4', '4', '4', NULL, NULL, 1, NULL, NULL);


INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES (1, 1, 1 );
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES (2, 1, 2 );


INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (1, 1, 1);

INSERT INTO `url`(`id`, `name`, `label`, `url`, `description`, `status`) VALUES (1, 'getUserInfo', '用户基本信息', 'https://api.bilibili.com/x/space/acc/info', NULL, 1);
INSERT INTO `url`(`id`, `name`, `label`, `url`, `description`, `status`) VALUES (2, 'getRoomInfoOld', '直播间基本信息', 'https://api.live.bilibili.com/room/v1/Room/getRoomInfoOld', NULL, 1);
INSERT INTO `url`(`id`, `name`, `label`, `url`, `description`, `status`) VALUES (3, 'getInfoByRoom', '直播间详细信息', 'https://api.live.bilibili.com/xlive/web-room/v1/index/getInfoByRoom', '获取直播间信息（标题，简介等）', 1);
INSERT INTO `url`(`id`, `name`, `label`, `url`, `description`, `status`) VALUES (4, 'getRoomPlayInfo', '获取直播流地址信息', 'https://api.live.bilibili.com/xlive/web-room/v1/index/getRoomPlayInfo', NULL, 1);



INSERT INTO `entity` (`id`, `name`, `label`, `status`) VALUES (1,'user','用户',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (1,1,1,'username','用户名',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (2,1,2,'password','密码',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (3,1,3,'realName','真实姓名',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (4,1,4,'phoneNo','手机号',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (5,1,5,'email','邮箱',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (6,1,6,'lastLoginIp','最后一次登录IP',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (7,1,7,'lastLoginTime','最后一次登录时间',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (8,1,8,'status','状态',1);
INSERT INTO `field`(`id`,`entity_id`,`no`,`name`,`label`,`status`) VALUES (9,1,9,'description','描述',1);

