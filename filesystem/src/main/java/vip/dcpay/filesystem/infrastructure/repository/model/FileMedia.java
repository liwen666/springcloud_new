package vip.dcpay.filesystem.infrastructure.repository.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * author lw
 * date 2019/8/5 19:56
 * 
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("file_media")
public class FileMedia implements Serializable {

	private static final long serialVersionUID = 1L;
	@TableField("uid")
	private long uid;//文件唯一标识
	@TableField("file_name")
	private String fileName; //文件名称
	@TableField("file_ext_name")
	private String fileExtName; //文件扩展名
	@TableField("group_name")
	private String groupName;//文件所属的组名
	@TableField("remote_filename")
	private String remoteFileName;//远程服务器文件名称
	@TableField("create_time")
	private Date createTime;//创建时间
	@TableField("modify_time")
	private Timestamp modifyTime;//文件修改时间

}
