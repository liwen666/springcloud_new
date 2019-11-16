//package jrx.batch.dataflow.demo;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.extension.api.R;
//import com.thoughtworks.xstream.XStream;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.dataflow.core.AppRegistration;
//import org.springframework.cloud.dataflow.core.ApplicationType;
//import org.springframework.cloud.dataflow.registry.service.AppRegistryService;
//import org.springframework.cloud.dataflow.rest.resource.AppRegistrationResource;
//import org.springframework.cloud.dataflow.server.controller.AppRegistryController;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PagedResourcesAssembler;
//import org.springframework.hateoas.PagedResources;
//import org.springframework.hateoas.ResourceSupport;
//import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * <p>
// * 描述
// * </p>
// *
// * @author tx
// * @since 2019/5/26 23:40
// */
//@RestController
//@RequestMapping("/test")
//public class XmlRootElementController {
//
//    @Autowired
//    private AppRegistryService appRegistryService;
//    XmlRootElementController.Assembler assembler = new XmlRootElementController.Assembler();
//    XmlRootElementController.AssemblerTest assemblerTest = new XmlRootElementController.AssemblerTest();
//
//
//    @RequestMapping("/getdata")
//    public XmlRootTest getData(){
//        XmlRootTest xmlRootTest = new XmlRootTest();
//        xmlRootTest.setName("xml");
//        return  xmlRootTest;
//    }
//    @RequestMapping("/getPage")
//    public Page getPage(){
//        XmlRootTest xmlRootTest = new XmlRootTest();
//        xmlRootTest.setName("xml");
//        Page<XmlRootTest> page = new PageImpl<XmlRootTest>(new ArrayList<XmlRootTest>(){{add(xmlRootTest);}});
//        XStream xs=new XStream();
//        /*
//         * 给指定的类型指定别名
//         */
//        xs.alias("pagedEntities", PageImpl.class);//
//        xs.alias("XmlRootTest", XmlRootTest.class);
//        xs.alias("ApplicationType", ApplicationType.class);
//        xs.omitField(PageImpl.class,"pageable");
//
//        String xml = xs.toXML(page);
//        System.out.println(xml);
////        return pagedResourcesAssembler.toResource(page, this.assemblerTest) ;
//        return page;
//    }
//
//
//    @RequestMapping("/apps")
//    public PagedResources<? extends AppRegistrationResource> list(Pageable pageable, PagedResourcesAssembler<AppRegistration> pagedResourcesAssembler, @RequestParam(value = "type",required = false) ApplicationType type, @RequestParam(required = false) String search) {
//        Page<AppRegistration> pagedRegistrations = appRegistryService.findAllByTypeAndNameIsLike(type, search, pageable);
//        String s = JSON.toJSONString(pagedRegistrations);
//        System.out.println(s);
//        return pagedResourcesAssembler.toResource(pagedRegistrations, this.assembler);
//    }
//
//
//    class Assembler extends ResourceAssemblerSupport<AppRegistration, AppRegistrationResource> {
//        public Assembler() {
//            super(AppRegistryController.class, AppRegistrationResource.class);
//        }
//
//        public AppRegistrationResource toResource(AppRegistration registration) {
//            return (AppRegistrationResource)this.createResourceWithId(String.format("%s/%s/%s", registration.getType(), registration.getName(), registration.getVersion()), registration);
//        }
//
//        protected AppRegistrationResource instantiateResource(AppRegistration registration) {
//            return new AppRegistrationResource(registration.getName(), registration.getType().name(), registration.getVersion(), registration.getUri().toString(), registration.isDefaultVersion());
//        }
//    }
//
//    class AssemblerTest extends ResourceAssemblerSupport<XmlRootTest,ResourceSupport > {
//        public AssemblerTest() {
//            super(XmlRootElementController.class, ResourceSupport.class);
//        }
//
//        public ResourceSupport toResource(XmlRootTest registration) {
//            return (ResourceSupport)this.createResourceWithId(String.format("%s/%s/%s", registration.getType(), registration.getName(), registration.getVersion()), registration);
//        }
//
//
//    }
//}
