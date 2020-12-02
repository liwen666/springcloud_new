package jrx.data.hub.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jrx.data.hub.infrastructure.entity.McUser;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Repository
public interface McUserMapper extends BaseMapper<McUser> {

}
