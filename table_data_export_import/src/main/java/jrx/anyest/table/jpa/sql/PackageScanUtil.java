package jrx.anyest.table.jpa.sql;

import jrx.anyest.table.config.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PackageScanUtil {
	public static Map<String, Map<String, Resource>> resourceStatics = new ConcurrentHashMap<>();
	public static Logger logger = LoggerFactory.getLogger(PackageScanUtil.class);

	public static void main(String[] args) throws IOException {


	}


	public static Resource findResource(String packageParten, String fileName, boolean cache) throws IOException {
		if (cache) {
			if (null == resourceStatics.get(packageParten)) {
				initResource(packageParten);
			}
			Resource resource = resourceStatics.get(packageParten).get(fileName);
			return resource;
		}else{
			initResource(packageParten);
			Resource resource = resourceStatics.get(packageParten).get(fileName);
			return resource;
		}

	}

	public static void initResource(String packageParten) throws IOException {

		String packageSearchPath = "classpath*:" + packageParten.replaceAll("\\.", "/") + "/**/*";
		Map<String, Resource> resourceMap = new HashMap<>();
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
		for (Resource resource : resources) {
			resourceMap.put(resource.getFilename(), resource);
		}
		resourceStatics.put(packageParten, resourceMap);
	}

	public static Resource findResourceByName(String filename, boolean b) throws MalformedURLException {
		if(filename==null){
			filename="config.properties";

		}
		String configPath = SystemConfig.getConfigPath();
		return new FileUrlResource(configPath+filename);
	}
}
