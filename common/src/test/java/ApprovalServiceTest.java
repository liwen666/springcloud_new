//import jrx.anyest.engine.base.enums.strategy.PublishMode;
//import jrx.anyest.engine.base.model.strategy.Approval;
//import jrx.anyest.rule.admin.service.ApprovalService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
///**
// * 审核测试
// * @author xiaohang.hu
// * @date 2019/11/07
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ApprovalServiceTest {
//    @Autowired
//    private ApprovalService approvalService;
//
//    @Test
//    public void save(){
//        Approval approval = new Approval();
//        approval.setFinalAuditor("默认");
//        approval.setStrategyInfoId(1);
//        approval.setBeforeState(PublishMode.UNPUBLISH);
//        approval.setApplyState(PublishMode.ONLINE);
//        approval.setApplyTime(new Date());
//        approval.setCurrentLevel(1);
//        approval.setProjectId(1);
//        approval.setFirstAuditor("默认");
//
//        approval = approvalService.save(approval,1);
//        System.out.println(approval);
//    }
//}
