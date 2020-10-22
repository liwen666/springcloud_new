package jrx.data.hub.domain.nacos;

import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import jrx.data.hub.domain.exception.RemoteRuntimeExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Slf4j
@Service
public class HttpJobServiceNameSpace {
    @Autowired
    private NodeServerConfigProperties nodeServerConfigProperties;


    /**
     * 查询服务节点
     */
    public String getJobServerUrl(String jobServer) {
        NamingService namingService = nodeServerConfigProperties.namingServiceInstance();
        Instance instance = null;
        try {
            instance = namingService.selectOneHealthyInstance(jobServer);
        } catch (Exception e) {
            e.printStackTrace();
             throw new RemoteRuntimeExeption("获取服务地址异常：服务名称 jobServer:"+jobServer+"  异常消息 error:"+e.getMessage());
        }
        return "http://" + instance.getIp() + ":" + instance.getPort();
    }

}
