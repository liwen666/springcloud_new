import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/4/3  15:05
 */
public class JavaRunTimeTest {

    private static ThreadPoolExecutor executor;

    static {
        executor = new ThreadPoolExecutor(6, 10, 5,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1024),
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 流处理
     * @param stream
     */
    private static void clearStream(InputStream stream) {
        //处理buffer的线程
        executor.execute(new Runnable() {
            @Override
            public void run() {

                String line = null;

                try (BufferedReader in = new BufferedReader(new InputStreamReader(stream,"gbk"));) {
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static boolean execCommond(String... args) {
        boolean flg = true;
        Runtime run = Runtime.getRuntime();
        try {
            Process p;
            if (args != null && args.length == 1) {
                p = run.exec(args[0]);
            } else {
                p = run.exec(args);
            }

            InputStream stream=p.getInputStream();
            System.out.println( stream + "....getInputStream..");

            //消费正常日志
            clearStream(stream);
            //消费错误日志
            clearStream(p.getErrorStream());

            if (p.waitFor() != 0) {
                if (p.exitValue() == 1) {
                    System.out.println("=============exec=====================命令执行失败!");
                    flg = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            flg = false;
        }
        return flg;
    }

    public static void main(String[] args) {

//        java JavaRunTimeTest "nohup sh aaa.sh"
        /*
          linux必须通过.sh命令来执行文件
         */
//        CommdUtils.execCommond("ping localhost");
        System.out.println(args[0]);
        JavaRunTimeTest.execCommond(args[0]);
//        JavaRunTimeTest.execCommond("cd /home/liwen/flink12.2/jobhome/1222/f984edbf64ec4255a8721f5dfaf2032b & jar -uvf /home/liwen/flink12.2/jobhome/1222/f984edbf64ec4255a8721f5dfaf2032b/job-cdc-1.0.0-SNAPSHOT.jar ./*.json");

    }
}
