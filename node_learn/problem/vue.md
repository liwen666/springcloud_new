#04 课程 ：
## 评论功能  使用技术  
    var list =  JSON.parse(localSorage.getItem('cmts') ||'[]')
    localSorage ES6中的全局对象池，可以将子组件的东西存进去，父组件取；
    vm组件使用created方法  调用其他方法，使用this.func()
    发表评论使用父组件给子组件传方法名 由子组件掉父组件传来的方法
    <cmt-box @func123='loadComments'></cmt-box>
    this.$emit('func123')   
## vue 获取dom元素和组件
   ref = 'h2id'  相当于dom的id  vm里面   this .$refs.h2id.innerText  拿到dom元素的text
   使用引用可以拿到子组件  this.$refs.mycomponent.func        datas /methods 
   通过引用获取组件，调用方法更加方便
   
##路由  vue-router
  hash(#/)    #只在页面里面跳转，单页面不跳转到其他页面    通过hash  请求路由，换组件不换页面和刷新
  VueRouter      使用 var routerOBJ = new VueRouter({
  routes[//路由匹配规则
   {path: '/login',component: login//放一个组件模板对象 不能使用名称}
  ]
  })
   vm = new Vue({
   el  data methods
   router:routerOBJ
   })
   <a href='#/login'>登陆  <a href="#/register">祖册  切换页面，不跳转
   <router-link to ='/login' tag='span''>登陆  替代a标签，指定元素，点击事件无影响
   <div id ="app"><router-view></router-view></div>  通过访问页面的路由/login 即可将资源展示出来
   {path:'/',redirect:'重定向页面的路径      /login'}
   router  给激活的link加了默认类定义激活元素的样式 可 .router-link-active{color:red; font-weight:800}
   以使用active-class ="自定义激活类名"  这个东西放在 vue-router  {linkActiveClass: myactive}
   
## 路由动画
   .v-enter,v-enter-to{opacity:0;transform:translate(0,0)};    .v-enter-active,.v-leave-active{
   translate: all 0.5 ease 
   }
   <transition mode="out-in"><router-view></router-view> </transition mode="out-in">
   url 传参   vm  { this.$route.query   .id  .name  拿到url的值}
   组件   template:"<h2>  {{  $rute.query.id}}" 输出传的值，  组件可以直接使用组件的data  
   /login:id:name    /login/12/ls  匹配传参   ，使用route.params拿到值  
   子路由    route-link   to= '/account/route'   children:[path:'route']  即可访问到子组件，自动拼接完整的路径
 
 ##命名视图  实现经典布局
    var  header = {
    template:"<H1> header 头部"}
    var  leftbox = {
        template:"<H1> header 头部"}
    var  mainBox = {
        template:"<H1> header 头部"}
        
        <router-view></vouter-view> <router-view name='left'></vouter-view> <router-view name='mainBox'></vouter-view>
      var router = new VueRouter({
      routers:[
     这种方式布局页面不行 path:'/',component:header],
     这种方式布局页面不行 path:'/left',component:leftbox],
     这种方式布局页面不行 path:'/main',component:mainBox],
      })
       routers:[
           path:'/',componens:{ default:header,
           '/left',:leftbox,
           '/main',:mainBox
            ])
 <div class='container'> 将元素左右布局 .continer{display:flex  ;}  .left{flex:2}  .right{flex:8}  
 
#05课 watch 监听  computed 
## 父组件给子组件传值
   var com1= { template:"#..",props:['parentmsg'],data(){return {msg:'子组件的值'}}  <com1 :parentmsg='msg'></com1> 
   <template id='#..'><div></div></template>
   var vm =new Vue({ methods:{getMsg(data){}})
##子组件给父组件传值  
   将父组件的方法通过  @func="parentMethod"  的方式绑定到子组件的func方法上  通过子组件调用
   this.$emit('func',this.msg)  调用子组件的方法  从子组件的方法中调用绑定的func  然后将参数传入
##监听文本框自动拼接案例KeyUp事件监听文本框数据的改变  keyup监听文本框简单一些
   1.v-model='args'   原生的键盘事件  @keyup='getFullName'   this.firstName+this.lastName 
   2.使用vue的watch   vm  ->  watch:{data的属性->firstname:function(){数据改变就会触发这个方法}}监听data中的数据的改变  
   watch:{'first':function(newVal,oldVal){this.fulname=newval+this.lastname}}
###watch监听路由的改变  非dom元素的改变
  watch:{'$route.path':function(newVal,oldVal){  拿到路由地址的改变  }}
###在computed可以定义一些属性，这些属性本质是一个方法  这是一个计算属性 自动求值计算
  computed:{'fullname':function(){return this.firstname+this.lastname}}
  这个计算属性只要计算属性的内部有任何一个元素变化就会自动去求值  fullname不放在data中
  注意 计算属性的值会被缓存 只要值不变就不会重新计算，提高效率
  引用计算属性的值只要计算属性有变化，，页面引用的地方都会跟着改变。
## nrm的安装  npm i nrm -g
  维护了taobao镜像  cnpm 镜像  等
   C:\Users\liwen>nrm ls
   * npm ---- https://registry.npmjs.org/
     cnpm --- http://r.cnpmjs.org/
     taobao - https://registry.npm.taobao.org/
     nj ----- https://registry.nodejitsu.com/
     rednpm - http://registry.mirror.cqupt.edu.cn/
     npmMirror  https://skimdb.npmjs.com/registry/
     edunpm - http://registry.enpmjs.org/
   切换下包地址  nrm use cnpm    使用npm下载包的时候就会再指定路径上下载
 ### cnpm是装包工具  
 
 ##webpack的学习  前端的项目构建工具 ，是node.js开发的前端工具  提高前端页面的响应速度
 ###静态资源
    JS  -.js  - .jsx   
    CSS  -.css   -.less   -.sass  -.scss
    Images  -.jpg .png   .gif    .bmp  .svg  
    字体文件      .svg  .ttf  .eot  .woff  .woff2
    模板文件  .ejs   .vue(再webpack中定义组件的方式)  
### 静态资源问题
     二次请求    获取静态资源  造成请求慢
     各包的复杂依赖关系问题
     webpack  解决方法  1 . 合并 .压缩 ,精灵图  2 处理依赖关系
## webpack基本用法  
   安装在项目中   npm i webpack --save-dev  
   简单案例  npm init -y         npm i Jquery -g 
   导入包 使用  import  $ from 'jquery'   这个是ES6中导入模块的方式
   node 的语法  const $  = require('jquery'')
   浏览器不识别ES六的一些代码 使用webpack来处理
   webpack .\src\main.js   .\dist\bundle.js
   将浏览器不是别的语法转换为可识别的语法
## webpack 配置文件的使用
    

   
     
      
    
    
 
 
 
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
  