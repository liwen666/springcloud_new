#h2的操作
 
 并发往H2插入数据可以借助redis锁来完成  防止mq与初始化线程多并发问题
 
 
    @Override
    public long hsetnx(String s, String zabc, String s1) {
        try (Jedis jedis = getResource()) {
            return jedis.hsetnx(s, zabc,s1);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return 0;
        }

    }