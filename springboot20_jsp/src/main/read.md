
#swagger  ui
http://localhost:10000/swagger-ui.html#/
http://localhost:10000/swagger-ui.html#/swagger-demo-controller
**API指定的 integer  让请求无法到达 用String即可

#feignclient 测试
http://localhost:10000/c

#cookies
 Cookie domainCookie = new Cookie("tokenId2","999999");
            domainCookie.setMaxAge(100);
            domainCookie.setHttpOnly(true);
            domainCookie.setDomain("10.10.15.71");
  给cookies指定访问的域，防止cookie被泄露。