//package com.temp.jpa.jpa.dao;
//
//import jrx.anyest.mc.console.model.app.AppServer;
//import jrx.anyest.mc.security.enums.State;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//public interface AppServerRepository extends JpaRepository<AppServer, Long>, JpaSpecificationExecutor<AppServer> {
//
//    List<AppServer> findByStateInAndIdNotIn(List<State> states, Set<Long> ids);
//
//    List<AppServer> findByStateIn(List<State> states);
//
//    List<AppServer> findByAppInfo_Id(Long appInfoId, Pageable pageable);
//
//    List<AppServer> findByAppInfo_IdAndNameLike(Long appInfoId, String name, Pageable pageable);
//
//    AppServer findByAddressLike(String address);
//
////    @Modifying
//    @Query(value = "select t from AppServer t where t.address like ?1")
//    List<AppServer> findAppsByAddressLike(String address);
//
//    AppServer findByUrlLike(String url);
//
////    @Modifying
//    @Query(value = "select t from AppServer t where t.url like ?1")
//    List<AppServer> findAppsByUrlLike(String url);
//
//    AppServer findByServiceId(String ServiceId);
//
//    List<AppServer> findByModifiedTimeLessThan(Date modifiedTime);
//}
