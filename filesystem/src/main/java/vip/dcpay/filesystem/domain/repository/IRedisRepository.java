package vip.dcpay.filesystem.domain.repository;

/**
 * author lw
 * date 2019/8/5 20:00
 * 
 */
public interface IRedisRepository {

    /**
     * 手动计算佣金开启
     *
     * @return
     */
    boolean isManualCalculateOpen();

}
