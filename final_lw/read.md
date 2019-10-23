
连接内存模式的数据库
jdbc:h2:mem:test



        List<Object> bpmn = fiscalIntegrationService.listBean("1500", "2017", "pay");
        System.out.println(JSON.toJSONString(bpmn));
        System.out.println("=====================节点描述=================================");
        List<Object> bpmn1 = fiscalIntegrationService.listNodeDescription("1500", "2017", "pay");
        System.out.println(JSON.toJSONString(bpmn1));
        System.out.println("==========================批处理类型============================");
        List<Object> bpmn2 = fiscalIntegrationService.listBatchType("1500", "2017", "pay");
        System.out.println(JSON.toJSONString(bpmn2));
======================================================
[{"beanName":"工作流联调","beanId":"payWfbranchBusinessCustomCondition"}]
=====================节点描述=================================
[{"code":"新增","codeName":"1001"},{"code":"已送审","codeName":"1002"},{"code":"已下达","codeName":"1003"},{"code":"部门审核","codeName":"1004"},{"code":"业务处审核","codeName":"1005"},{"code":"绩效处审核","codeName":"1006"},{"code":"绩效处评审","codeName":"1007"},{"code":"绩效处复核","codeName":"1008"},{"code":"已淘汰","codeName":"1009"},{"code":"已反馈","codeName":"1010"},{"code":"第三方评审","codeName":"1011"},{"code":"已退回-新增","codeName":"1012"},{"code":"已退回-已送审","codeName":"1013"},{"code":"已退回-业务处","codeName":"1014"},{"code":"已退回-绩效处审核","codeName":"1015"},{"code":"已退回-绩效处评审","codeName":"1016"},{"code":"已退回-第三方","codeName":"1017"},{"code":"新增","codeName":"000"},{"code":"已审核","codeName":"001"},{"code":"已发送","codeName":"002"},{"code":"已打印","codeName":"003"},{"code":"已生成","codeName":"004"},{"code":"已退回","codeName":"005"},{"code":"已支付","codeName":"006"},{"code":"已清算","codeName":"007"},{"code":"已送审","codeName":"008"},{"code":"国库接收(业务)","codeName":"009"},{"code":"已记账","codeName":"010"},{"code":"已终审","codeName":"011"},{"code":"已审核2","codeName":"012"},{"code":"已作废","codeName":"013"},{"code":"国库初审","codeName":"014"},{"code":"国库复审","codeName":"015"},{"code":"国库接收","codeName":"016"},{"code":"已下达","codeName":"017"},{"code":"单位初审","codeName":"018"},{"code":"一级单位审核","codeName":"019"},{"code":"二级单位审核","codeName":"020"},{"code":"业务初审","codeName":"021"},{"code":"业务复审","codeName":"022"},{"code":"银行已签收","codeName":"023"},{"code":"人社厅初审","codeName":"024"},{"code":"人社厅复审","codeName":"025"},{"code":"人社厅终审","codeName":"026"},{"code":"工资信息财政终审","codeName":"027"},{"code":"财政审核（工资）","codeName":"028"},{"code":"会计科审批","codeName":"029"},{"code":"已退回-新增","codeName":"04|000"},{"code":"已退回-已审核","codeName":"04|001"},{"code":"已退回-已发送","codeName":"04|002"},{"code":"已退回-已打印","codeName":"04|003"},{"code":"已退回-已生成","codeName":"04|004"},{"code":"已退回","codeName":"04|005"},{"code":"已退回-已支付","codeName":"04|006"},{"code":"已退回-已送审","codeName":"04|008"},{"code":"已退回-已审核2","codeName":"04|012"},{"code":"已退回-国库初审","codeName":"04|014"},{"code":"已退回-国库复审","codeName":"04|015"},{"code":"已退回-国库接收","codeName":"04|016"},{"code":"已退回-已下达","codeName":"04|017"},{"code":"已退回-单位初审","codeName":"04|018"},{"code":"已退回-一级单位审核","codeName":"04|019"},{"code":"已退回-二级单位审核","codeName":"04|020"},{"code":"已退回-业务初审","codeName":"04|021"},{"code":"已退回-业务复审","codeName":"04|022"},{"code":"已退回-已签章","codeName":"04|023"},{"code":"已退回-人社厅初审","codeName":"04|024"},{"code":"已退回-人社厅复审","codeName":"04|025"},{"code":"已退回-人社厅终审","codeName":"04|026"},{"code":"已退回-财政审核（工资）","codeName":"04|028"},{"code":"已退回-会计科审批","codeName":"04|030"}]
==========================批处理类型============================
[{"code":"back","codeName":"退回"},{"code":"obsolete","codeName":"作废"},{"code":"sendaudit","codeName":"送审"},{"code":"audit","codeName":"审核"},{"code":"create","codeName":"新增"}]


http://localhost:8081/newpay/test/callback


http://2477v852j2.zicp.vip:27765/newpay/test/callback
