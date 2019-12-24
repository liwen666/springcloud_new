//package it.pcan.test.feign.remote;
//
//import feign.Param;
//import feign.RequestLine;
//import jrx.batch.dataflow.util.JsonResult;
//
//import java.util.List;
//
///**
// * <p>
// * 描述
// * </p>
// *
// * @author lw
// * @since 2019/11/11 16:57
// */
//public interface JobExecuteClient extends RemoteClient {
//
//
//    /**
//     * 执行任务通过分区id 绑定
//     *
//     * @return
//     */
//    @RequestLine("POST /batch/http/job/execjob")
//    JsonResult executeRemoteJob(@Param("arguments") List<String> arguments);
//
//
//}
