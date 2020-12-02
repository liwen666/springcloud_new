package jrx.data.hub.domin.service;

import jrx.data.hub.domin.config.GrayVersionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/11/25  17:27
 */
@Service
public class DataServiceVersionService {
    @Autowired
    GrayVersionConfig grayVersionConfig;

    public String getCurrentVersion() {
       return  grayVersionConfig.getCurrentVersion();
    }
}
