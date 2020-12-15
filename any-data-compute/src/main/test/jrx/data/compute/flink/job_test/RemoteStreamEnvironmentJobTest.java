/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jrx.data.compute.flink.job_test;

import jrx.data.compute.flink.RemoteStreamEnvironmentTest;
import jrx.data.compute.test.TestLogger;
import org.apache.flink.api.common.JobID;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.dag.Transformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.RemoteStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableConfig;
import org.apache.flink.table.api.bridge.java.internal.StreamTableEnvironmentImpl;
import org.apache.flink.table.catalog.CatalogManager;
import org.apache.flink.table.catalog.FunctionCatalog;
import org.apache.flink.table.module.ModuleManager;
import org.apache.flink.table.operations.ModifyOperation;
import org.apache.flink.types.Row;
import org.apache.flink.util.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Tests for the {@link RemoteStreamEnvironment}.
 */
public class RemoteStreamEnvironmentJobTest extends TestLogger {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testPortForwarding() throws Exception {
//        String host = "10.0.20.11";
//        int port = 8081;
//        String host = "192.168.137.111";
        String host = "lacolhost";
        int port = 8081;
        JobID jobId = new JobID();

        final Configuration clientConfiguration = new Configuration();
        RemoteStreamEnvironmentTest.TestExecutorServiceLoader testExecutorServiceLoader = new RemoteStreamEnvironmentTest.TestExecutorServiceLoader(jobId);
        final StreamExecutionEnvironment env = new RemoteStreamEnvironment(
                testExecutorServiceLoader,
                host,
                port,
                clientConfiguration,
                null,
                null,
                null
        );

        DataStreamSource<Integer> elements = env.fromElements(1, 2, 3);

        StreamTableEnvironmentImpl tEnv = getStreamTableEnvironment(env, elements);

        Time minRetention = Time.minutes(1);
        Time maxRetention = Time.minutes(10);
        tEnv.getConfig().setIdleStateRetentionTime(minRetention, maxRetention);
        Table table = tEnv.fromDataStream(elements);
        DataStream<Row> rowDataStream = tEnv.toAppendStream(table, Row.class);
        rowDataStream.print();
    }


    private StreamTableEnvironmentImpl getStreamTableEnvironment(
            StreamExecutionEnvironment env,
            DataStreamSource<Integer> elements) {
        TableConfig config = new TableConfig();
        CatalogManager catalogManager = CatalogManagerMocks.createEmptyCatalogManager();
        ModuleManager moduleManager = new ModuleManager();
        return new StreamTableEnvironmentImpl(
                catalogManager,
                moduleManager,
                new FunctionCatalog(config, catalogManager, moduleManager),
                config,
                env,
                new TestPlanner(elements.getTransformation()),
                new ExecutorMock(),
                true
        );
    }

    @Test
    public void name() throws Exception {
        final String inputData = "ABC,2.20,3\nDEF,5.1,5\nDEF,3.30,1\nGHI,3.30,10";
        final String dataPath = createInputData(inputData);
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<POJOItem> data = env.readCsvFile(dataPath).pojoType(POJOItem.class, new String[]{"f1", "f3", "f2"});
        List<POJOItem> result = data.collect();
        String expected = "ABC,3,2.20\nDEF,5,5.10\nDEF,1,3.30\nGHI,10,3.30";
    }

    private String createInputData(String data) throws Exception {
        File file = tempFolder.newFile("input");
        FileUtils.writeFileUtf8(file, data);

        return file.toURI().toString();
    }

    /**
     * POJO.
     */
    public static class POJOItem {
        public String f1;
        private int f2;
        public double f3;

        public int getF2() {
            return f2;
        }

        public void setF2(int f2) {
            this.f2 = f2;
        }

        @Override
        public String toString() {
            return String.format(Locale.US, "%s,%d,%.02f", f1, f2, f3);
        }
    }

    private static class TestPlanner extends PlannerMock {
        private final Transformation<?> transformation;

        private TestPlanner(Transformation<?> transformation) {
            this.transformation = transformation;
        }

        @Override
        public List<Transformation<?>> translate(List<ModifyOperation> modifyOperations) {
            return Collections.singletonList(transformation);
        }
    }

}
