package it.pcan.test.feign.client;

import feign.Param;
import feign.RequestLine;
import it.pcan.test.feign.bean.UploadInfo;
import it.pcan.test.feign.bean.UploadMetadata;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @author Pierantonio Cangianiello
 */
//@FeignClient(value = "feignClientTest", url = "${portal.fuds.address:http://localhost:8080}")
public interface FeignClientTest {

    @RequestLine("POST /test")
    public UploadInfo test(UploadMetadata metadata);

    @RequestLine("POST /upload/{folder}")
    public UploadInfo upload(@Param("folder") String folder, @Param("file") MultipartFile file, @Param("metadata") UploadMetadata metadata);

    @RequestLine("POST /uploadSimple/{folder}")
    public UploadInfo uploadSimple(@Param("folder") String folder, @Param("file") MultipartFile file);

    @RequestLine("POST /uploadArray/{folder}")
    public List<UploadInfo> uploadArray(@Param("folder") String folder, @Param("files") MultipartFile[] files, @Param("metadata") UploadMetadata metadata);

}
