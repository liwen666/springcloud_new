#!/usr/bin/env bash

KAFKA_DML_TOPIC=101_dml_maxwell,local_dml
KAFKA_DDL_TOPIC=101_ddl_maxwell,local_ddl
SPRING_KAFKA_BOOTSTRAP_SERVERS=172.16.102.23:9092
SERVER_NAME=local
 [[ ! -e "$JAVA_HOME/bin/java" ]] && JAVA_HOME=$HOME/jdk/java
 [[ ! -e "$JAVA_HOME/bin/java" ]] && JAVA_HOME=/usr/java
 [[ ! -e "$JAVA_HOME/bin/java" ]] && JAVA_HOME=/opt/taobao/java
 [[ ! -e "$JAVA_HOME/bin/java" ]] && unset JAVA_HOME
 
CLASSPATH="${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/dt.jar:${PROJECT_HOME}/*"
SHELL_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)

#利用pwd命令获取当前工程目录，实际获取到的是该shell脚本的目录。再利用sed命令将/bin替换为空
PROJECT_HOME=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd | sed 's/\/sbin//')

#mkdir -vp $PROJECT_HOME/../batch_schedule/{platform/bigspace,workspace,jarhome,jobserverhome,tomcat_dir}
echo "work directory: $PROJECT_HOME"
echo "SHELL_DIR: $SHELL_DIR"
APPLICATION_MAIN=kafka_server_base-1.0-SNAPSHOT.jar
TPID=0
#环境变量
if [[ -n "$1" ]]; then
    PROFILE=$1
	SERVER_NAME=$1_maxwell_monitor
	else
	PROFILE=local
	SERVER_NAME=local_maxwell_monitor
fi

cp kafka_server_base-1.0-SNAPSHOT.jar $SERVER_NAME.jar
APPLICATION_MAIN=$SERVER_NAME.jar
getPID(){
    javaps=`jps -l | grep $APPLICATION_MAIN`
    if [ -n "$javaps" ]; then
        TPID=`echo $javaps | awk '{print $1}'`
    else
        TPID=0
    fi
}


#tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
getPID
if [ $TPID -ne 0 ]; then
        echo "$APPLICATION_MAIN is running, ps info: $TPID"
else
        echo "$APPLICATION_MAIN is not running."
fi

LOG_DIR=${PROJECT_HOME}/logs



#GC日志参数
#-XX:+PrintGC 输出GC日志
#-XX:+PrintGCDetails 输出GC的详细日志
#-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
#-XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
#-XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
#-Xloggc:../logs/gc.log 日志文件的输出路径
# -XX:+DisableExplicitGC 禁止显示的调用gc发方法
GC_OPTS="-XX:+DisableExplicitGC"
GC_OPTS="${GC_OPTS} -XX:+PrintGCDetails"
GC_OPTS="${GC_OPTS} -XX:+PrintGCDateStamps"
GC_OPTS="${GC_OPTS} -XX:GCLogFileSize=10M"
GC_OPTS="${GC_OPTS} -XX:NumberOfGCLogFiles=10"
GC_OPTS="${GC_OPTS} -XX:+UseGCLogFileRotation"
GC_OPTS="${GC_OPTS} -XX:ErrorFile=${LOG_DIR}/hs_err_pid<pid>.log"
GC_OPTS="${GC_OPTS} -Xloggc:${LOG_DIR}/gc.log"


#内存溢出记录dump文件
#HEAP_DUMP="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_DIR}/heap_dump_`date '+%Y-%m-%d—%H%M%S'`.hprof"
HEAP_DUMP="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_DIR}/"


#JVM启动参数
#-Dspring.profiles.active=$PROFILE: 启动环境配置选择（使用配置文件控制）
#-Duser.timezone: 设置时区
#-XX:ParallelGCThreads: 并行收集器的线程数
#-XX:+UseConcMarkSweepGC: 使用旧生代并发收集器
#-XX:+UseParNewGC: 使用新生代并发收集器
#-Xmn: 新生代大小（建议新生代占整个堆1/3 - 1/4合适）
#-Xms: 初始堆大小
#-Xloggc: gc日志地址
#-Djava.rmi.server.hostname=127.0.0.1: rmi设置服务ip(需要时使用)
#-Dcom.sun.management.jmxremote: jmx监控(需要时使用)
#-Dcom.sun.management.jmxremote.port=1099: jmx监控端口(需要时使用)
#-Dcom.sun.management.jmxremote.authenticate=false: 关闭jmx监控权限(需要时使用)
#-Dcom.sun.management.jmxremote.ssl=false: 关闭jmx监控认证(需要时使用)
#JAVA_OPT="-server -Xms256m -Xmx2048m -Duser.timezone=GMT+8 -XX:ParallelGCThreads=20 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC ${GC_OPTS} ${HEAP_DUMP}"

JAVA_OPT="-Dspring.profiles.active=${PROFILE}"
JAVA_OPT=" -Xms200M -Xmx512M  ${JAVA_OPT} -Duser.timezone=GMT+8 -XX:+PrintCommandLineFlags"


#JMX参数, 生产环境不开启
#JMX_OPTS="-Dcom.sun.management.jmxremote.port=1099"
#JMX_OPTS="${JMX_OPTS} -Dcom.sun.management.jmxremote.ssl=false"
#JMX_OPTS="${JMX_OPTS} -Dcom.sun.management.jmxremote.authenticate=false"

#JVM内存，不同的环境和模式设置不同
#JAVA_MEM_OPTS=""



#JAVA_OPT="${JAVA_OPT} -javaagent:/data/agent/skywalking-agent.jar -Dskywalking.agent.service_name=AnyBatchNode_${SERVER_NAME}  -Dskywalking.collector.backend_service=39.0.158.101:11800"


JAVA_OPT="${JAVA_OPT} -XX:+UseFastAccessorMethods"
JAVA_OPT="${JAVA_OPT} -XX:+ParallelRefProcEnabled"

echo -e "\033[31m 注意：当前使用的是${PROFILE}环境，请根据系统实际情况设置最大最小内存参数,当前默认值：${JAVA_MEM_OPTS} \033[0m"

JAVA_OPT="${JAVA_OPT} ${GC_OPTS} ${HEAP_DUMP}"

# print out env properties
echo "工程目录: ${PROJECT_HOME}"
echo "配置文件变量：\$PROFILE=${PROFILE}"
echo "日志目录：\$LOG_DIR=${LOG_DIR}"
echo \$JAVA_OPT="${JAVA_OPT}"

#标准日志输出，生成环境不开启
LOG_FILE="start.out"


#创建日志文件和目录
logDir(){
    if [[ ! -d "${LOG_DIR}" ]]; then
        mkdir "${LOG_DIR}"
    fi
    if [[ ! -f "${LOG_DIR}/${LOG_FILE}" ]]; then
        touch "${LOG_DIR}/${LOG_FILE}"
    fi
}




startup(){
    getPID
    logDir
    echo "================================================================================================================"
    if [[ ${TPID} -ne 0 ]]; then
        echo "${APPLICATION_MAIN} already started (PID=${TPID})"
        echo "================================================================================================================"
    else
        echo "Starting ${APPLICATION_MAIN}\n"
		nohup java -jar      ${JAVA_OPT}   ${PROJECT_HOME}/$SERVER_NAME.jar   --kafka.dml.topic=$KAFKA_DML_TOPIC --kafka.ddl.topic=$KAFKA_DDL_TOPIC --spring.kafka.bootstrap-servers=$SPRING_KAFKA_BOOTSTRAP_SERVERS    > $LOG_DIR/start.out &
        sleep 1
        getPID
        if [[ ${TPID} -ne 0 ]]; then
            echo "${APPLICATION_MAIN} (PID=${TPID})...[Success]"
            echo "================================================================================================================"
        else
            echo "${APPLICATION_MAIN} boot failed. [Failed]"
            echo "================================================================================================================"
        fi
        echo "tail log file : ${LOG_DIR}/${LOG_FILE}"
        tail -f "${LOG_DIR}/${LOG_FILE}"
    fi
}

if [[ -e ${PROJECT_HOME}/tpid ]]; then
    rm -f ${PROJECT_HOME}/tpid
fi

#服务启动
startup
tail -f $LOG_DIR/start.out


