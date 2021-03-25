package jrx.data.hub.code.utils;

import org.apache.maven.shared.invoker.*;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.deployer.resource.maven.MavenProperties;
import org.springframework.cloud.deployer.resource.maven.MavenResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 插件测试
 */
public class MavenUtilsTest {

    @Test
    public void name() throws MavenInvocationException {
        System.setProperty("maven.home","D:\\flink12.2\\maven\\apache-maven-3.6.3");
        InvocationRequest request = new DefaultInvocationRequest();
//        request.setPomFile(new File("D:\\work\\any-data-hub-parent\\any-data-hub-component\\code-compile\\pom.xml"));
        request.setPomFile(new File("D:\\workspace\\strategy-topology\\strategy\\pom.xml"));
        request.setGoals( Arrays.asList( "clean","install" ) );
        request.setUserSettingsFile(new File("D:\\flink12.2\\maven\\apache-maven-3.6.3\\conf\\settings.xml"));
        request.setLocalRepositoryDirectory(new File("D:\\flink12.2\\maven\\m2"));
        List<String> projects = request.getProjects();
        Invoker invoker = new DefaultInvoker();
        invoker.execute(request);
        InvocationResult result = invoker.execute(request);
    }

    @Test
   public void resource() {
        MavenProperties mavenProperties = new MavenProperties();
        mavenProperties.setLocalRepository("D:\\flink12.2\\maven\\m2");
        MavenResource parse = MavenResource.parse("maven://jrx.tutorial:b01-simple-job:jar:1.0.0", mavenProperties);
        boolean exists = parse.exists();
        System.out.println(exists);
    }

    @Test
    public void gerFile() throws IOException {
        MavenProperties mavenProperties = new MavenProperties();
        mavenProperties.setLocalRepository("D:\\flink12.2\\maven\\m2");
        MavenResource parse = MavenResource.parse("com.riveretech.est:strategy:jar:1.0.0-SNAPSHOT", mavenProperties);
//        MavenResource parse = MavenResource.parse("com.riveretech.est:topology:jar:1.0.0-SNAPSHOT", mavenProperties);
        boolean exists = parse.exists();
        System.out.println(exists);
        File file = parse.getFile();
        InputStream inputStream = parse.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\workspace\\springcloud_new\\code-compile\\src\\test\\java\\jrx\\data\\hub\\code\\utils\\"+file.getName());
        byte [] cache=new byte[1024];
        int index=0;
        while ((index=inputStream.read(cache))!=-1){
            fileOutputStream.write(cache,0,index);
        }
    }
}