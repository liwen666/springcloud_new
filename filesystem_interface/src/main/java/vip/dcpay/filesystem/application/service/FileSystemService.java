package vip.dcpay.filesystem.application.service;


import vip.dcpay.filesystem.application.dto.FileMediaDto;
import vip.dcpay.vo.basic.Result;

/**
 * author lw
 * date 2019/8/5 17:09
 * 
 */
public interface FileSystemService {
    /**
     * 下载文件
     * @param uid
     * @return
     */
     Result<FileMediaDto> downLoadFileMedia(long uid);

    /**
     * 上传文件
     * @param fileMediaDto
     * @see vip.dcpay.enums.filesystem.FileGroupEnum
     * @return
     */
     Result<Long> uploadFileMedia( FileMediaDto fileMediaDto);

    /**
     * 本地文件上传
     * @param fileMediaDto
     * @see vip.dcpay.enums.filesystem.FileGroupEnum
     * @return
     */
    Result<Long> uploadFileMediaFromLocal( FileMediaDto fileMediaDto,String localName);

}
