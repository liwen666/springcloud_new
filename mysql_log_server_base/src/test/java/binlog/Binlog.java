package binlog;

import com.example.SpringBootEurekaApplication;
import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootEurekaApplication.class)
@WebAppConfiguration
public class Binlog {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    @Test
    public void testFoo() throws IOException {
        String filePath = "D:\\idea2018workspace\\springcloud_new\\mysql_log_server_base\\src\\test\\java\\binlog\\mysql-bin.000006_2";
        File binlogFile = new File(filePath);
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        File file = new File("D:\\idea2018workspace\\springcloud_new\\mysql_log_server_base\\src\\test\\java\\binlog\\log_2.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BinaryLogFileReader reader = new BinaryLogFileReader(binlogFile, eventDeserializer);
        try {
            for (Event event; (event = reader.readEvent()) != null; ) {
                System.out.println(event.toString());
                fileOutputStream.write(event.toString().getBytes());
            }
        } finally {
            reader.close();
        }
    }
}