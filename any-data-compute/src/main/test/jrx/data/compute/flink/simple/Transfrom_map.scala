package jrx.data.compute.flink.simple

import org.apache.flink.streaming.api.scala._

/**
 * description: SourceList 
 * date: 2020/8/28 19:02 
 * version: 1.0
 *
 * @author 阳斌
 *         邮箱：1692207904@qq.com
 *         类的说明：从集合读取数据
 */
object Transfrom_map {

  def main(args: Array[String]): Unit = {
      //1.创建执行的环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    //2.从集合中读取数据
    val sensorDS: DataStream[WaterSensor] = env.fromCollection(
      // List(1,2,3,4,5)
      List(
        WaterSensor("ws_001", 1577844001, 45.0),
        WaterSensor("ws_002", 1577844015, 43.0),
        WaterSensor("ws_003", 1577844020, 42.0)
      )
    )

    val sensorDSMap = sensorDS.map(x => (x.id+"_1",x.ts+"_1",x.vc + 1))

    //3.打印
    sensorDSMap.print()
    //4.执行
    env.execute("sensor")

  }

  /**
   * 定义样例类：水位传感器：用于接收空高数据
   *
   * @param id 传感器编号
   * @param ts 时间戳
   * @param vc 空高
   */
  case class WaterSensor(id: String, ts: Long, vc: Double)


}