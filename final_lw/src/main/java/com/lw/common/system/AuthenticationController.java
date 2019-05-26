package com.lw.common.system;

import com.lw.common.annotation.Log;
import com.lw.common.exception.impl.AccountExpiredException;
import com.lw.common.security.AuthenticationManager;
import com.lw.common.security.UserDetails;
import com.lw.common.security.UserDetailsService;
import com.lw.common.security.domain.AuthorizationUser;
import com.lw.common.security.domain.JwtUser;
import com.lw.common.security.impl.AuthenticationToken;
import com.lw.common.utils.EncryptUtils;
import com.lw.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 17:29
* Version:        1.0
*/
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 登录授权
     * @param authorizationUser
     * @return
     */
    @Log(description = "用户登录")
    @PostMapping(value = "${jwt.auth.path}")
    public ResponseEntity<?> authenticationLogin(@RequestBody AuthorizationUser authorizationUser){

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authorizationUser.getUsername());

        if(!userDetails.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))){
            throw new AccountExpiredException("密码错误");
        }

        if(!userDetails.isEnabled()){
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }

        // 生成令牌
        final String token = jwtTokenUtil.generateToken(userDetails);

        // 返回 token
        return ResponseEntity.ok(new AuthenticationToken(token));
    }

   /**
   * Description:    java类作用描述<br>
   * author:     lw
   * date:     2019/5/26 17:30
   * Version:        1.0
   */
    @GetMapping(value = "${jwt.auth.account}")
    public ResponseEntity getUserInfo(HttpServletRequest request){
        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(jwtTokenUtil.getUserName(request));
        return ResponseEntity.ok(jwtUser);
    }
}
