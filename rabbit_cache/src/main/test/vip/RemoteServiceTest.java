package vip.dcpay.cache.domain.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.cache.RunApplication;
import vip.dcpay.cache.util.AssertUtils;
import vip.dcpay.merchant.application.dto.FetcherDto;
import vip.dcpay.merchant.application.dto.MerchantFullDto;
import vip.dcpay.merchant.application.inner.MerchantInnerService;
import vip.dcpay.merchant.application.param.business.FetcherFilterCondition;
import vip.dcpay.merchant.application.service.merchant.MerchantService;
import vip.dcpay.vo.basic.Page;
import vip.dcpay.vo.basic.Pagination;
import vip.dcpay.vo.basic.Result;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class RemoteServiceTest {
    @Reference
    private MerchantService merchantService;


    @Reference
    MerchantInnerService merchantInnerService ;

    @Test
    public void getMerchant() {
        String data ="{\"currency\":\"CNY\",\"maxBalance\":1000000000,\"maxLoseLawRate\":1,\"maxTransQuantityInWeek\":1000000000,\"maxTurnoverInToday\":1000000000,\"merchantType\":3,\"minBalance\":0,\"minLoseLawRate\":0,\"minTransQuantityInWeek\":0,\"minTurnoverInToday\":0,\"orderType\":2}";
        FetcherFilterCondition fetcherFilterCondition = JSON.parseObject(data, FetcherFilterCondition.class);
        fetcherFilterCondition.setMerchantTypeList(new ArrayList<Integer>(){{add(1);add(2);add(3);}});
        Result<List<FetcherDto>> fetcher = merchantInnerService.findFetcher(fetcherFilterCondition);
        System.out.println(JSON.toJSONString(fetcher));

    }
    @Test
    public void getFullDto() {
        Result<Page<MerchantFullDto>> pageFullMerchant = merchantService.getPageFullMerchant(1, 1000);
        Page<MerchantFullDto> data = pageFullMerchant.getData();
        List<MerchantFullDto> content = data.getContent();
        Pagination pagination = data.getPagination();

        System.out.println("=========================================================================================");
        System.out.println(JSON.toJSONString(pageFullMerchant));

    }
    //分页查询所有商户信息
    @Test
    public void name() {
        int pageSize = 10000;
        //查询数据
        Pagination build = Pagination.builder().num(1).size(pageSize).total(10001).build();
        int currentPage = getCurrentPage(build);
        while (currentPage!=0){
            System.out.println("============> 查询第  "+currentPage);
            build.setNum(currentPage);
            currentPage = getCurrentPage(build);
            //查询数据
//            查询商户信息
        }
        System.out.println("=========================================================================================");

    }

    public int getCurrentPage(Pagination pageInfo) {
        AssertUtils.expect(pageInfo.getSize()!=0," Pagination 分页参数的 size 不能为0 ");
       int totalPage = ((pageInfo.getTotal()-1)+pageInfo.getSize())/pageInfo.getSize();
       if(totalPage>pageInfo.getNum()){
           return pageInfo.getNum()+1;
       }
        return 0;
    }
}