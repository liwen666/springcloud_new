http://localhost:8888/login 请求主页


#读取binlog文件
File binlogFile = ...
 BinaryLogFileReader reader = new BinaryLogFileReader(binlogFile);
 try {
     for (Event event; (event = reader.readEvent()) != null; ) {
         ...
     }
 } finally {
     reader.close();
 }
 
 #监控binlog
 BinaryLogClient client = new BinaryLogClient("hostname", 3306, "username", "password");
 client.registerEventListener(new EventListener() {
 
     @Override
     public void onEvent(Event event) {
         ...
     }
 });
 client.connect();
 
 #
 EventDeserializer eventDeserializer = new EventDeserializer();
 
 // do not deserialize EXT_DELETE_ROWS event data, return it as a byte array
 eventDeserializer.setEventDataDeserializer(EventType.EXT_DELETE_ROWS, 
     new ByteArrayEventDataDeserializer()); 
 
 // skip EXT_WRITE_ROWS event data altogether
 eventDeserializer.setEventDataDeserializer(EventType.EXT_WRITE_ROWS, 
     new NullEventDataDeserializer());
 
 // use custom event data deserializer for EXT_DELETE_ROWS
 eventDeserializer.setEventDataDeserializer(EventType.EXT_DELETE_ROWS, 
     new EventDataDeserializer() {
         ...
     });
 
 BinaryLogClient client = ...
 client.setEventDeserializer(eventDeserializer);
 
 #
 MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
 
 BinaryLogClient binaryLogClient = ...
 ObjectName objectName = new ObjectName("mysql.binlog:type=BinaryLogClient");
 mBeanServer.registerMBean(binaryLogClient, objectName);
 
 // following bean accumulates various BinaryLogClient stats 
 // (e.g. number of disconnects, skipped events)
 BinaryLogClientStatistics stats = new BinaryLogClientStatistics(binaryLogClient);
 ObjectName statsObjectName = new ObjectName("mysql.binlog:type=BinaryLogClientStatistics");
 mBeanServer.registerMBean(stats, statsObjectName);
 
 #
 System.setProperty("javax.net.ssl.trustStore", "/path/to/truststore.jks");
 System.setProperty("javax.net.ssl.trustStorePassword","truststore.password");
 System.setProperty("javax.net.ssl.keyStore", "/path/to/keystore.jks");
 System.setProperty("javax.net.ssl.keyStorePassword", "keystore.password");
 
 BinaryLogClient client = ...
 client.setSSLMode(SSLMode.VERIFY_IDENTITY);
