localhost:10012/nacos-client/client

#如何服务端的urp有自定义的path
请求是无法通过的，需要对请求统一添加前缀
通过UrlFeignClientInterceptor  重写header,uri等属性




http://localhost:10012/nacos-client/flux
