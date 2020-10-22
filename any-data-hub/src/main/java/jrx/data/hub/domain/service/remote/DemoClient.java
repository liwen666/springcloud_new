package jrx.data.hub.domain.service.remote;

import feign.Param;
import feign.RequestLine;
import jrx.data.hub.util.JsonResult;

import java.util.List;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/10/22 16:25
 */
public interface DemoClient extends RemoteClient {


    /**
     * 执行任务通过分区id 绑定
     *
     * @return
     */
    @RequestLine("POST /batch/http/job/execjob")
    JsonResult executeRemoteJob(@Param("arguments") List<String> arguments);


}
