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