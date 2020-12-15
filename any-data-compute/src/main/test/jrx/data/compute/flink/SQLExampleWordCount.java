package jrx.data.compute.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.sources.CsvTableSource;
import org.apache.flink.types.Row;
import org.junit.Test;


/**
 */
public class SQLExampleWordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment blinkStreamEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        blinkStreamEnv.setParallelism(1);
        EnvironmentSettings blinkStreamSettings = EnvironmentSettings.newInstance()
                .useBlinkPlanner()
                .inStreamingMode()
                .build();
        StreamTableEnvironment blinkStreamTableEnv = StreamTableEnvironment.create(blinkStreamEnv, blinkStreamSettings);

//        String path = SQLExampleWordCount.class.getClassLoader().getResource("words.txt").getPath();
        String path = "D:\\workspace\\springcloud_new\\any-data-compute\\src\\main\\test\\jrx\\data\\compute\\test.csv";

        CsvTableSource csvTableSource = CsvTableSource.builder()
                .field("word", Types.STRING)
                .path(path)
                .build();
        blinkStreamTableEnv.registerTableSource("zhisheng", csvTableSource);
        Table wordWithCount = blinkStreamTableEnv.sqlQuery("SELECT count(word), word FROM zhisheng GROUP BY word");
        blinkStreamTableEnv.toRetractStream(wordWithCount, Row.class).print();

        blinkStreamTableEnv.execute("Blink Stream SQL Job");
    }


    @Test
    public void remote() {


    }
}
