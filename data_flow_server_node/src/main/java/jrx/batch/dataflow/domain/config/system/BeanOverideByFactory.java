package jrx.batch.dataflow.domain.config.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jrx.batch.dataflow.application.TaskPartitionExecutionController;
import jrx.batch.dataflow.demo.DemoConfig;
import jrx.batch.dataflow.domain.config.annotation.BeanOveride;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.dataflow.core.TaskDeployment;
import org.springframework.cloud.dataflow.server.config.features.TaskConfiguration;
import org.springframework.cloud.dataflow.server.repository.TaskDeploymentRepository;
import org.springframework.cloud.dataflow.server.service.TaskExecutionInfoService;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述
 * 方法1.通过修改beandefinition 来覆盖框架加载的bean
 * 方法2.定义bean名字在bean注入的时候会根据方法参数名字自动匹配方法名相同的bean,如果没有就就自动注入type相同的类
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */

@Configuration
@Slf4j
public class BeanOverideByFactory implements ApplicationRunner {
    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    TaskDeploymentRepository taskDeploymentRepository;
    @PostConstruct
    public void init() {
        /**
         *
         */
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String[] beanNamesForType1 = beanFactory.getBeanNamesForType(TaskExecutionService.class);
        log.info("taskExecutionService-->"+JSON.toJSONString(beanNamesForType1));
//        BeanDefinitionRegistry beanFactory1 = (BeanDefinitionRegistry) beanFactory;
//        BeanDefinition beanDefinition1 =beanFactory1.getBeanDefinition("taskExecutionService");
//        beanFactory1.removeBeanDefinition(beanNamesForType1[0]);
//        beanFactory1.removeBeanDefinition(beanNamesForType1[1]);
//        beanFactory1.registerBeanDefinition("taskService",beanDefinition1);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        //获取所有的RequestMapping
//        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(context,
//                HandlerMapping.class, true, false);
//        for (HandlerMapping handlerMapping : allRequestMappings.values())
//        {
//            //本项目只需要RequestMappingHandlerMapping中的URL映射
//            if (handlerMapping instanceof RequestMappingHandlerMapping)
//            {
//                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
//                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
//                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet())
//                {
//                    RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
//                    HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();
//
//                    RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
//
//                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
//                    String requestUrl = patternsCondition.getPatterns().toString();
//
//                    String controllerName = mappingInfoValue.getBeanType().toString();
//                    String requestMethodName = mappingInfoValue.getMethod().getName();
//                    Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
//
//                    log.info("====请求路径requestMethodName:{},requestUrl:{}",requestMethodName,requestUrl);
//                }
//            }
//        }
    }
}
