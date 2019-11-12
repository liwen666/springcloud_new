package jrx.batch.dataflow.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.core.AppRegistration;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.registry.service.AppRegistryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@RestController
@RequestMapping("/spring/batch")
public class SpringBatchController {
    @Autowired
    private AppRegistryService appRegistryService;

    @RequestMapping("/apps")
    public Page<? extends AppRegistration> list(Pageable pageable, @RequestParam(value = "type",required = false) ApplicationType type, @RequestParam(required = false) String search) {
        Page<AppRegistration> pagedRegistrations = appRegistryService.findAllByTypeAndNameIsLike(type, search, pageable);
        return pagedRegistrations;
    }
}
