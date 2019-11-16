package jrx.batch.dataflow.domain.enums;

import jrx.batch.dataflow.domain.config.system.PropertiesThreadLocalHolder;

public enum BatchNodePropertiesKey {
   PARENT_ID;  //批次执行时 调度中心传入的任务执行  父id



    public static void main(String[] args) {
       PropertiesThreadLocalHolder.addProperties(BatchNodePropertiesKey.PARENT_ID.name(),"12344");
        System.out.println(BatchNodePropertiesKey.PARENT_ID.name());
        System.out.println();
        System.out.println(PropertiesThreadLocalHolder.getProperties(BatchNodePropertiesKey.PARENT_ID.name()));
    }

}
