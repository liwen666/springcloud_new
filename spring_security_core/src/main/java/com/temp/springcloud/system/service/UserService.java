package com.temp.springcloud.system.service;

import com.temp.springcloud.system.domin.User;
import com.temp.springcloud.system.service.dto.UserDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author lw
 * @date 2019.3.20
 */
@CacheConfig(cacheNames = "user")
public interface UserService {

    /**
     * get
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    UserDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    UserDTO create(User resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(User resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * findByName
     * @param userName
     * @return
     */
    @Cacheable(key = "'findByName'+#p0")
    User findByName(String userName);
}
