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
        request.setPomFile(new File("D:\\work\\any-data-hub-parent\\pom.xml"));
//        request.setPomFile(new File("D:\\workspace\\strategy-topology\\strategy\\pom.xml"));
//        request.setPomFile(new File("D:\\flink12\\flink_cdc\\pom.xml"));
//        request.setPomFile(new File("D:\\flink12\\flink-1.12.1-src\\flink-engine\\pom.xml"));
//        request.setPomFile(new File("D:\\workspace\\strategy-topology\\pom.xml"));
        /*---------------------------------------------------------------------------------/
       cmd.exe /X /C "D:\flink12.2\maven\apache-maven-3.6.3\bin\mvn.cmd -D maven.repo.local=D:\flink12.2\maven\m2 -s D:\flink12.2\maven\apache-maven-3.6.3\conf\settings.xml clean install"

       cmd.exe /X /C "D:\flink12.2\maven\apache-maven-3.6.3\bin\mvn.cmd -D maven.repo.local=D:\flink12.2\maven\m2 -s D:\flink12.2\maven\apache-maven-3.6.3\conf\settings.xml depeloy"
        validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy. -
        /---------------------------------------------------------------------------------*/
        request.setGoals( Arrays.asList( "clean","install  -Dmaven.test.skip=true" ) );
//        request.setGoals( Arrays.asList( "depeloy" ) );
        request.setUserSettingsFile(new File("D:\\flink12.2\\maven\\apache-maven-3.6.3\\conf\\settings.xml"));
        request.setLocalRepositoryDirectory(new File("D:\\flink12.2\\maven\\m2\\repository"));
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
        mavenProperties.setLocalRepository("D:\\.m2\\repository");
        MavenResource parse = MavenResource.parse("com.jrx.anytxn:anytxn-log-spring-boot-starter:jar:1.0.0-SNAPSHOT", mavenProperties);
//        MavenResource parse = MavenResource.parse("com.riveretech.est:topology:jar:1.0.0-SNAPSHOT", mavenProperties);
        boolean exists = parse.exists();
        System.out.println(exists);
        File file = parse.getFile();
        InputStream inputStream = parse.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\workspace\\springcloud_new\\maven-code-compile\\src\\test\\java\\jrx\\data\\hub\\code\\utils\\"+file.getName());
        byte [] cache=new byte[1024];
        int index=0;
        while ((index=inputStream.read(cache))!=-1){
            fileOutputStream.write(cache,0,index);
        }
    }

    @Test
    public void deployLocalFile() throws MavenInvocationException {
        System.setProperty("maven.home","D:\\flink12.2\\maven\\apache-maven-3.6.3");
        InvocationRequest request = new DefaultInvocationRequest();
        request.setUserSettingsFile(new File("D:\\flink12.2\\maven\\apache-maven-3.6.3\\conf\\settings.xml"));
        request.setPomFile(new File("D:\\workspace\\strategy-topology\\strategy\\pom.xml"));
        /*---------------------------------------------------------------------------------/
       cmd.exe /X /C "D:\flink12.2\maven\apache-maven-3.6.3\bin\mvn.cmd -D maven.repo.local=D:\flink12.2\maven\m2 -s D:\flink12.2\maven\apache-maven-3.6.3\conf\settings.xml clean install"

       cmd.exe /X /C "D:\flink12.2\maven\apache-maven-3.6.3\bin\mvn.cmd -D maven.repo.local=D:\flink12.2\maven\m2 -s D:\flink12.2\maven\apache-maven-3.6.3\conf\settings.xml depeloy"
        validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy. -
        /---------------------------------------------------------------------------------*/
        request.setGoals( Arrays.asList( "clean","install" ) );
//        指定依赖仓库  不配置默认用settint中的配置
        request.setLocalRepositoryDirectory(new File("D:\\flink12.2\\maven\\m2\\repository"));
        Invoker invoker = new DefaultInvoker();
        invoker.execute(request);
        InvocationResult result = invoker.execute(request);
    }

    @Test
    public void deployLocalFile2() throws MavenInvocationException {
        System.setProperty("maven.home","D:\\flink12.2\\maven\\apache-maven-3.6.3");
        InvocationRequest request = new DefaultInvocationRequest();
//        request.setUserSettingsFile(new File("D:\\flink12.2\\maven\\apache-maven-3.6.3\\conf\\settings.xml"));
        /*---------------------------------------------------------------------------------/
       cmd.exe /X /C "D:\flink12.2\maven\apache-maven-3.6.3\bin\mvn.cmd -D maven.repo.local=D:\flink12.2\maven\m2 -s D:\flink12.2\maven\apache-maven-3.6.3\conf\settings.xml clean install"

        new CommandExecutor().exec("cmd.exe /X /C  cd D: & cd D:\\work\\any-data-hub-parent\\any-data-processor\\src\\main\\resources\\job\\cfdafdasjob\\6dd7f449b15047969616b22671ecc9d2 & jar -uvf D:\\work\\any-data-hub-parent\\any-data-processor\\src\\main\\resources\\job\\cfdafdasjob\\6dd7f449b15047969616b22671ecc9d2\\job-cdc-1.0.0-SNAPSHOT.jar ./*.json ", "gbk");

       cmd.exe /X /C "D:\flink12.2\maven\apache-maven-3.6.3\bin\mvn.cmd -D maven.repo.local=D:\flink12.2\maven\m2 -s D:\flink12.2\maven\apache-maven-3.6.3\conf\settings.xml depeloy"
        validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy. -
        /---------------------------------------------------------------------------------*/
//        request.setGoals( Arrays.asList( "install:install-file -DgroupId=com.temp -DartifactId=spire.pdf -Dversion=17.3.0 -Dpackaging=jar -Dfile=D:\\workspace\\test_workspace\\lib\\Spire.Pdf.jar" ) );
        request.setGoals( Arrays.asList( "install:install-file -DgroupId=com.jrx.anytxn -DartifactId=anytxn-log-spring-boot-starter -Dversion=1.0.0-SNAPSHOT -Dpackaging=jar -Dfile=D:\\workspace\\springcloud_new\\maven-code-compile\\src\\test\\java\\jrx\\data\\hub\\code\\utils\\anytxn-log-spring-boot-starter-1.0.0-SNAPSHOT.jar" ) );
//        指定依赖仓库  不配置默认用settint中的配置
        request.setLocalRepositoryDirectory(new File("D:\\flink12.2\\maven\\m2\\repository"));
        Invoker invoker = new DefaultInvoker();
        invoker.execute(request);
        InvocationResult result = invoker.execute(request);
    }
}