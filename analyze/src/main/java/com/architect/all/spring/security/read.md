## 进入登录页面是每次都跳转两次登录

   原因是没有设置静态访问路径  加载css文件和js文件时验证错误
   
   
#用户名密码->(Authentication(未认证)  ->  AuthenticationManager ->AuthenticationProvider->UserDetailService->UserDetails->Authentication(已认证）

#用户配置自动登录
    AbstractRememberMeServices
      String paramValue = request.getParameter(parameter);
                if (paramValue != null &&
                 (paramValue.equalsIgnoreCase("true") || 
                 paramValue.equalsIgnoreCase("on") ||
                  paramValue.equalsIgnoreCase("yes") ||
                   paramValue.equals("1"))) {
              
    PersistentTokenBasedRememberMeServices
    根据前端传过来的参数   后台设置的  remember-config
    值为  yes  on  ....  就会持久化 到数据库
    onLoginSuccess  向页面写入  cookie  将token写到页面

    AbstractAuthenticationProcessingFilter.java
    doFilter-->   