package vip.dcpay.filesystem.application.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import vip.dcpay.enums.filesystem.FileGroupEnum;
import vip.dcpay.filesystem.application.dto.FileMediaDto;
import vip.dcpay.filesystem.application.service.FileSystemService;
import vip.dcpay.filesystem.common.MyException;
import vip.dcpay.filesystem.common.NameValuePair;
import vip.dcpay.filesystem.config.FileDfsConfig;
import vip.dcpay.filesystem.domain.fastdfs.StorageClient;
import vip.dcpay.filesystem.domain.fastdfs.StorageServer;
import vip.dcpay.filesystem.infrastructure.repository.dao.FileMediaDao;
import vip.dcpay.filesystem.infrastructure.repository.model.FileMedia;
import vip.dcpay.filesystem.infrastructure.translator.FileMediaTranslator;
import vip.dcpay.filesystem.util.DcpayDfsUtils;
import vip.dcpay.filesystem.util.FileIdGenerator;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.vo.basic.Result;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author lw
 * date 2019/8/5 20:17
 */
@Service
public class FileSystemServiceImpl implements FileSystemService {
    @Autowired
    private FileMediaDao fileMediaDao;

    @Override
    public Result<FileMediaDto> downLoadFileMedia(long uid) {
        FileMedia fileMedia = fileMediaDao.selectOne(new QueryWrapper<FileMedia>().lambda().eq(FileMedia::getUid, uid));
        if (null != fileMedia) {
            StorageServer[] storeStorages = DcpayDfsUtils.getArrayStorage(fileMedia.getGroupName());
            StorageClient client;
            if (storeStorages != null && storeStorages.length == 1) {
                client = new StorageClient(FileDfsConfig.trackerServer, storeStorages[0]);
                try {
                    byte[] bytes = client.download_file(fileMedia.getGroupName(), fileMedia.getRemoteFileName());
                    FileMediaDto fileMediaDto = FileMediaTranslator.conversionToDto(fileMedia);
                    fileMediaDto.setFileBuff(bytes);
                    return Result.success(fileMediaDto);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (MyException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.error("下载失败！");
    }

    @Override
    public Result<Long> uploadFileMedia(FileMediaDto fileMediaDto) {
        return uploadFileMediaFromLocal(fileMediaDto, null);
    }

    @Override
    public Result<Long> uploadFileMediaFromLocal(FileMediaDto fileMediaDto, String localName) {
        try {
            if (localName != null) {
                File file = new File(localName);
                @Cleanup FileInputStream inputStream = new FileInputStream(file);
                byte[] cacheBuff = new byte[inputStream.available()];
                inputStream.read(cacheBuff);
                fileMediaDto.setFileBuff(cacheBuff);
            }
            checkFile(fileMediaDto);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        FileGroupEnum groupName = fileMediaDto.getGroupName();
        if (groupName == null) groupName = FileGroupEnum.DEFAULT;
        StorageServer[] storeStorages = DcpayDfsUtils.getArrayStorage(groupName.getName());
        StorageClient client;
        String[] results = null;
        if (storeStorages != null && storeStorages.length == 1) {
            try {

                client = new StorageClient(FileDfsConfig.trackerServer, storeStorages[0]);
                List<NameValuePair> nameValuePairs;
                if (null != fileMediaDto.getMeta_list()) {
                    Map<String, String> meta_list = fileMediaDto.getMeta_list();
                    nameValuePairs = JSON.parseArray(JSON.toJSONString(meta_list), NameValuePair.class);
                    results = client.upload_file(groupName.getName(), fileMediaDto.getFileBuff(), fileMediaDto.getFileExtName(), (NameValuePair[]) nameValuePairs.toArray());

                } else {
                    results = client.upload_file(groupName.getName(), fileMediaDto.getFileBuff(), fileMediaDto.getFileExtName(), null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MyException e) {
                e.printStackTrace();
            }
            MyLogManager.develop("=====上传文件" + JSON.toJSONString(results));
            //将文件id存入数据库
            if (results != null) {
                long uid = FileIdGenerator.getNextLong();
                fileMediaDao.insert(FileMedia.builder()
                        .uid(uid)
                        .createTime(new Date())
                        .remoteFileName(results[1])
                        .groupName(groupName.getName())
                        .fileExtName(fileMediaDto.getFileExtName())
                        .fileName(fileMediaDto.getFileName())
                        .build());
                return Result.success(uid);
            }
            return Result.error("上传文件失败！");
        } else {
            return Result.error("=====找不到文件服务器！请检查系统配置。");
        }
    }

    private void checkFile(FileMediaDto fileMediaDto) {
        Assert.state(fileMediaDto.getFileName() != null && !fileMediaDto.getFileName().equals(""), "文件名不能为空");
        Assert.state(0 < fileMediaDto.getFileBuff().length && fileMediaDto.getFileBuff().length < Integer.parseInt(FileDfsConfig.properties.getProperty("fastdfs.upload_size")), "文件大小需大于0   小于 " + FileDfsConfig.properties.getProperty("fastdfs.upload_size") + "文件大小是：" + fileMediaDto.getFileBuff().length);
        Assert.state(fileMediaDto.getFileExtName() != null && !fileMediaDto.getFileExtName().equals(""), "文件扩展名不能为空");
    }

    public static void main(String[] args) {
//        int i = Integer.parseInt("1*10");
//        System.out.println(i);
        System.out.println(10 * 1024 * 1024);
    }
}
