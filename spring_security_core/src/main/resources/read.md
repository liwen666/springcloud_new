#spring_sercuity 基础架构
 
 jwt:
   header: Authorization
   secret: mySecret
   # token 过期时间 1个小时
   expiration: 3600000
 #  expiration: 60000
   auth:
     # 授权路径
     path: /login
     # 获取用户信息
     account: /info
     
 #测试参数  
 Authorization=admin
 
 
 @Override
     protected void configure(HttpSecurity http) throws Exception{
 
         http.authorizeRequests().antMatchers("/").permitAll()
                 //安全关闭服务接口，拥有ADMIN权限的用户可以访问该rul
                 .antMatchers("/shutdown").hasRole("ADMIN")
                 // 其他地址的访问均需验证权限（需要登录）
                 .anyRequest().authenticated().and()
                 //开启basic认证，若不添加此项，将不能通过curl的basic方式传递用户信息
                 .httpBasic()
                 //其他配置省略
 
     }
     
     DefaultJwtParser  用来解析凭证
    for(int i$ = 0; i$ < len$; ++i$) {
                char c = arr$[i$];
                if (c == '.') {
                    CharSequence tokenSeq = Strings.clean(sb);
                    String token = tokenSeq != null ? tokenSeq.toString() : null;
                    if (delimiterCount == 0) {
                        base64UrlEncodedHeader = token;
                    } else if (delimiterCount == 1) {
                        base64UrlEncodedPayload = token;
                    }
    
                    ++delimiterCount;
                    sb.setLength(0);
                } else {
                    sb.append(c);
                }
            }