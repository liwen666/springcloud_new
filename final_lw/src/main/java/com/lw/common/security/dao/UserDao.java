package com.lw.common.security.dao;

import com.lw.common.security.domain.User;
import org.springframework.stereotype.Repository;
/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 16:29
* Version:        1.0
*/
@Repository
public interface UserDao {
    User findByEmail(String username);

    User findByUsername(String username);
}
