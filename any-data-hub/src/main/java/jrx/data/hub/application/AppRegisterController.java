package jrx.data.hub.application;

import jrx.data.hub.domain.service.IAppRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/3/6 21:04
 */
@Slf4j
@RestController
@RequestMapping("/data/hub")
public class AppRegisterController {

    private IAppRegistrationService appRegistrationService;



}