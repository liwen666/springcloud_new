package com.lw.common.log.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lw.common.log.dao.LogMapper;
import com.lw.common.log.domain.Log;
import com.lw.common.log.service.LoggingService;
import com.lw.common.security.SecurityContextHolder;
import com.lw.common.security.UserDetails;
import com.lw.common.security.domain.AuthorizationUser;
import com.lw.common.utils.JwtTokenUtil;
import com.lw.common.utils.el.IpUtil;
import com.lw.common.utils.el.RequestHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 23:34
* Version:        1.0
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoggingServiceImpl implements LoggingService {

    @Autowired
    private LogMapper logMapper;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final String LOGINPATH = "authenticationLogin";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProceedingJoinPoint joinPoint, Log logging){

        // 获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.lw.common.annotation.Log log = method.getAnnotation(com.lw.common.annotation.Log.class);

        // 描述
        if (log != null) {
            logging.setDescription(log.description());
        }

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";

        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 用户名
        String username = "";

        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }

        // 获取IP地址
        logging.setRequestIp(IpUtil.getIP(request));

        if(!LOGINPATH.equals(signature.getName())){
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            username = userDetails.getUsername();
        } else {
            AuthorizationUser user = JSONUtil.toBean(new JSONObject(argValues[0]),AuthorizationUser.class);
            username = user.getUsername();

        }
        logging.setMethod(methodName);
        logging.setUsername(username);
        logging.setParams(params + " }");
        logMapper.insert(logging);
    }
}
