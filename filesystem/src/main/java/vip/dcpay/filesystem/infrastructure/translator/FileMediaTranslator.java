package vip.dcpay.filesystem.infrastructure.translator;

import org.springframework.stereotype.Component;
import vip.dcpay.enums.filesystem.FileGroupEnum;
import vip.dcpay.filesystem.application.dto.FileMediaDto;
import vip.dcpay.filesystem.infrastructure.repository.model.FileMedia;

/**
 * author lw
 * date 2019/8/5 19:56
 * 
 */
@Component
public class FileMediaTranslator {


    public static FileMediaDto conversionToDto(FileMedia fileMedia) {

        return FileMediaDto.builder().
                groupName(FileGroupEnum.getEnum(fileMedia.getGroupName()))
                .fileName(fileMedia.getFileName())
                .remoteFileName(fileMedia.getRemoteFileName())
                .uid(fileMedia.getUid())
                .fileExtName(fileMedia.getFileExtName())
                .build();
    }
}
