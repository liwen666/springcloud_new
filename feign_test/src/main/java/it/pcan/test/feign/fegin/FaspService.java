//package it.pcan.test.feign.fegin;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Map;
//
///**
// * 平台接口服务
// *
// * @author xinre
// * @date 2019/5/28 13:15
// */
//@ConditionalOnProperty(prefix = "portal.login.platform", name = "enable", matchIfMissing = true)
////@FeignClient(name = "permissionService", url = "${portal.login.platform.address:http://127.0.0.1}")
//@FeignClient(name = "permissionService", url = "http://192.168.100.169:16000")
//@RequestMapping("/fasp/restapi/v1/")
//public interface FaspService {
//
//
//    //////////////////// ca //////////////////////////////////////////////////
//
//    /**
//     * 获取所有用户信息
//     *
//     * @param zoning 区划
//     * @return
//     */
//    @GetMapping(value = "ca/users/businessuser/{prevonce}/all")
//    @ResponseBody
//    Map<String, Object> listUserByZoning(@PathVariable(name = "prevonce") String zoning);
//
//    /**
//     * 获取角色信息
//     *
//     * @param year 年度
//     * @return
//     */
//    @GetMapping(value = "ca/roles/{year}")
//    @ResponseBody
//    Map<String, Object> listRoleByYear(@PathVariable(name = "year") String year);
//
//    /**
//     * 获取用户拥有的角色信息
//     *
//     * @param userId 用户Id
//     * @param year   年度
//     * @return
//     */
//    @GetMapping(value = "ca/users/{guid}/roles/{year}")
//    @ResponseBody
//    Map<String, Object> listRoleByUserAndYear(@PathVariable(name = "guid") String userId,
//                                              @PathVariable(name = "year") String year);
//
//
//    //////////////////// dic //////////////////////////////////////////////////
//
//    /**
//     * 获取单位信息
//     *
//     * @param zoning 区划
//     * @param year   年度
//     * @return
//     */
//    @GetMapping(value = "dic/ranges/agnecy/{prevonce}/{year}/")
//    @ResponseBody
//    Map<String, Object> listUnitByZoningAndYear(@PathVariable(name = "prevonce") String zoning,
//                                                @PathVariable(name = "year") String year);
//
//    /**
//     * 获取机构信息
//     *
//     * @param elementCode 要素编码（财政机构："department",区划："admdiv"）
//     * @param zoning      区划
//     * @param year        年度
//     * @return
//     */
//    @GetMapping(value = "dic/ranges/{elementcode}/{prevonce}/{year}/")
//    @ResponseBody
//    Map<String, Object> listOrgByElementCodeAndZoningAndYear(@PathVariable(name = "elementcode") String elementCode,
//                                                             @PathVariable(name = "prevonce") String zoning,
//                                                             @PathVariable(name = "year") String year);
//
//    //////////////////// sec //////////////////////////////////////////////////
//
//    /**
//     * 获取用户信息
//     *
//     * @param tokenid
//     * @return
//     */
//    @GetMapping(value = "sec/tokenids/{tokenid}/user")
//    @ResponseBody
//    Map<String, Object> getUserByTokenId(@PathVariable(name = "tokenid") String tokenid);
//
//}
