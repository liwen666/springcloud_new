CREATE TABLE `file_media` (
  `uid` bigint(32) NOT NULL,
  `file_name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `group_name` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `remote_filename` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
