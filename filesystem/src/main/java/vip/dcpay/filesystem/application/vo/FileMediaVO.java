package vip.dcpay.filesystem.application.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * author lw
 * date 2019/8/5 19:56
 * 
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileMediaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private long uid;//文件唯一标识
	private String fileName; //文件名称
	private String groupName;//文件所属的组名
	private String remote_fileName;//远程服务器文件名称
	private Date create_time;//创建时间
	private Timestamp timestamp;//文件修改时间


}
