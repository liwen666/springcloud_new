package jrx.data.hub.code.utils;

import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Arrays;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/3/24  15:53
 */
public class MavenUtils {
    public static void main(String[] args) throws MavenInvocationException {
        System.setProperty("maven.home","D:\\flink12.2\\maven\\apache-maven-3.6.3");
        InvocationRequest request = new DefaultInvocationRequest();
//        request.setPomFile(new File("D:\\work\\any-data-hub-parent\\any-data-hub-component\\code-compile\\pom.xml"));
//        request.setPomFile(new File("D:\\workspace\\strategy-topology\\strategy\\pom.xml"));
        request.setPomFile(new File("D:\\workspace\\strategy-topology\\strategy\\pom.xml"));
//        request.setPomFile(new File("D:\\workspace\\strategy-topology\\pom.xml"));
//        request.setGoals( Arrays.asList( "clean", "install" ) );
        request.setGoals(Arrays.asList("clean", "install"));
        request.setUserSettingsFile(new File("D:\\flink12.2\\maven\\apache-maven-3.6.3\\conf\\settings.xml"));
        request.setLocalRepositoryDirectory(new File("D:\\flink12.2\\maven\\m2"));
        Invoker invoker = new DefaultInvoker();
        invoker.execute(request);
        InvocationResult result = invoker.execute(request);

        if (result.getExitCode() != 0) {
            throw new IllegalStateException("Build failed.");
        }
    }



}
