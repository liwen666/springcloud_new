package vip.dcpay.filesystem.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vip.dcpay.enums.filesystem.FileGroupEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 文件传输对象
 * author lw
 * date 2019/8/5 17:11
 * 
 */
@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class FileMediaDto implements Serializable {

    private long uid;//文件唯一标识
    private String fileName; //文件名称
    private FileGroupEnum groupName;//文件所属的组名
    private String remoteFileName;//远程服务器文件名称
    private Date createTime;//创建时间
    private byte[] fileBuff;//文件内容
    private String fileExtName; //文件的扩展名称
    private Map<String,String> meta_list;  //文件的描述信息



}
