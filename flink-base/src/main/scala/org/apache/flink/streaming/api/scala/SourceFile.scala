package org.apache.flink.streaming.api.scala

import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

/**
 * description: SourceList 
 * date: 2020/8/28 19:02 
 * version: 1.0
 *
 * @author 阳斌
 *         邮箱：1692207904@qq.com
 *         类的说明：从文件读取数据
 */
object SourceFile {

  def main(args: Array[String]): Unit = {
    //1.创建执行的环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //2.从指定路径获取数据
    val fileDS: DataStream[String] = env.readTextFile("input/data.log")

    //3.打印
    fileDS.print()

    //4.执行
    env.execute("sensor")

  }
}
/**
 * 在读取文件时，文件路径可以是目录也可以是单一文件。如果采用相对文件路径，会从当前系统参数user.dir中获取路径
 * System.getProperty("user.dir")
 */
/**
 * 如果在IDEA中执行代码，那么系统参数user.dir自动指向项目根目录，
 * 如果是standalone集群环境, 默认为集群节点根目录，当然除了相对路径以外，
 * 也可以将路径设置为分布式文件系统路径，如HDFS
 val fileDS: DataStream[String] =
 env.readTextFile( "hdfs://hadoop02:9000/test/1.txt")
 */