##配置idea的快速注释

1-->   settings ->  在设置界面的搜索框中输入live template查找到Live Templates配置项
2-->点击最右侧的『+』号按钮，添加一个模板组，这里组名我们填写lw，这样就完成了一个模板组的创建，接下来我们将在这个模板组下创建类注释及方法注释模板。
3-->添加【Live Template】，输入模板基础信息（模板名称填写ccmt，模板介绍填写类注释模板），填写完成后单击第二张图片箭头指向处设置要针对的语言（这里我们只选择了Java和JavaScript）。
4-->点击最下面的一个连接选择注释的文件类型
5-->填入注释/**
    * @Description:    java类作用描述
    * @Author:         yc
    * @CreateDate:     $DATE$ $TIME$
    * @UpdateUser:     yc
    * @UpdateDate:     $DATE$ $TIME$
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @Version  等是参数  需要取值，注释不规范javadoc 会报错
    方法注释
    *
     * 功能描述: <br>
     * 〈$END$〉
     * @Param: $param$
     * @Return: $return$
     * @Author: $user$
     * @Date: $DATE$ $TIME$
     */
6-->为注释中的变量设置取值方法
7-->在java 或者其他配置代码的地方输入lw即可快捷调用注释
## 注释可以用html的方式配置
    /**
    * Description:   mybatis配置
    * author:     lw
    * date:     2019/5/26 12:23
    * Version:        1.0
    * <a href="https://www.baidu.com">百度</a>
    */