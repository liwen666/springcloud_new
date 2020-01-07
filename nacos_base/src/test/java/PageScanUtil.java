import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;

public class PageScanUtil {
    public static Logger logger = LoggerFactory.getLogger(PageScanUtil.class);
    static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    public static void main(String[] args) throws IOException {

        findInputStream("","data_flow_server_node-dev.yaml");
    }
   public static InputStream findInputStream(String relativePackage,String fileName) throws IOException {
       String packageSearchPath = "classpath*:"+relativePackage.replaceAll("\\.","/")+"/**/*";
       Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
       for(Resource resource:resources){
           if(resource.getFilename().equals(fileName)){
               logger.info("查找到资源：{}",fileName);
               return resource.getInputStream();
           }
       }
        return null;
    }
}
