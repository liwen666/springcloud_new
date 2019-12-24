package jrx.batch.dataflow.domain.service.remote;

import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class JobServerLaunch {
    @Test
    public void ping() throws IOException {

        String property = System.getProperty("os.name");
        String msg;
        StringBuilder sb = new StringBuilder();
        InputStream fis = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ping localhost");
//               Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", XXXX});
            // 注意，我将原来的15行注释掉了，变成了下面的写法。声明，我调用的command是Lunix下的命令，如果你用的是windows的话，不需要这么写。
            // 为什么要使用这样的写法，因为项目需要考虑到单引号双引号等，转换加/的原因。
            fis = process.getInputStream();
            @Cleanup BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis, "gbk"));
            while ((msg = bufferedReader.readLine()) != null) {
                sb.append(msg);
                sb.append("\n");
            }
            log.info(sb.toString());
            process.waitFor();
            log.info(process.exitValue() + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                process.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void launchJobServer() throws IOException {
        String property = System.getProperty("os.name");
        log.info("操作系统是：{}",property);
        String msg;
        StringBuilder sb = new StringBuilder();
        InputStream fis = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(" java -jar C:\\Users\\liwen\\Desktop\\jrx\\jobserverhome\\http_batch_demoRELEASE.jar");
            fis = process.getInputStream();
           @Cleanup BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis, "gbk"));
            while ((msg = bufferedReader.readLine()) != null) {
                sb.append(msg);
                sb.append("\n");
            }
            log.info(sb.toString());
            process.waitFor();
            log.info(process.exitValue() + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                process.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}