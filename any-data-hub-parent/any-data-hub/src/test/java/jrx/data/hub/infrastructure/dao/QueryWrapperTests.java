//package jrx.batch.dataflow.infrastructure.dao;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import jrx.batch.dataflow.infrastructure.model.TaskExecution;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class QueryWrapperTests {
//
//    @Autowired
//    private TaskTaskBatchMapper mapper;
//
//    /**
//     * <p>
//     * 根据根据 entity 条件，删除记录,QueryWrapper实体对象封装操作类（可以为 null）
//     * 下方获取到queryWrapper后删除的查询条件为name字段为null的and年龄大于等于12的and email字段不为null的
//     * 同理写法条件添加的方式就不做过多介绍了。
//     * </p>
//     */
//    @Test
//    public void delete() {
//        QueryWrapper<TaskExecution> queryWrapper = new QueryWrapper<TaskExecution>();
//        queryWrapper
//                .isNull("name")
//                .ge("age", 12)
//                .isNotNull("email");
//        int delete = mapper.delete(queryWrapper);
//        System.out.println("delete return count = " + delete);
//    }
//
//
//    /**
//     * <p>
//     * 根据 entity 条件，查询一条记录,
//     * 这里和上方删除构造条件一样，只是seletOne返回的是一条实体记录，当出现多条时会报错
//     * </p>
//     */
//    @Test
//    public void selectOne() {
//        QueryWrapper<TaskExecution> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", "lqf");
//
//        TaskExecution TaskExecution = mapper.selectOne(queryWrapper);
//        System.out.println(TaskExecution);
//    }
//
//
//    /**
//     * <p>
//     * 根据 Wrapper 条件，查询总记录数
//     * </p>
//     *
//     * @param queryWrapper 实体对象
//     */
//    @Test
//    public void selectCount() {
//        QueryWrapper<TaskExecution> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", "lqf");
//
//        Integer count = mapper.selectCount(queryWrapper);
//        System.out.println(count);
//    }
//
//
//    /**
//     * <p>
//     * 根据 entity 条件，查询全部记录
//     * </p>
//     *
//     * @param queryWrapper 实体对象封装操作类（可以为 null）为null查询全部
//     */
//    @Test
//    public void selectList() {
//        List<TaskExecution> list = mapper.selectList(null);
//
//        System.out.println(list);
//    }
//
//    /**
//     * <p>
//     * 根据 Wrapper 条件，查询全部记录
//     * </p>
//     *
//     * @param queryWrapper 实体对象封装操作类（可以为 null）
//     */
//    @Test
//    public void selectMaps() {
//        QueryWrapper<TaskExecution> queryWrapper = new QueryWrapper<>();
//        queryWrapper.isNotNull("name");
//        List<Map<String, Object>> maps = mapper.selectMaps(queryWrapper);
//        for (Map<String, Object> map : maps) {
//            System.out.println(map);
//        }
//    }
//
//    /**
//     * 打印结果
//     * {name=lqf, id=1046282328366391406, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391407, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391408, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391409, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391410, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391411, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391412, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391413, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391414, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391415, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391416, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391417, age=12, email=lqf@163.com, status=false}
//     * {name=lqf, id=1046282328366391418, age=12, email=lqf@163.com, status=false}
//     * json类型的键值对模式
//     */
//
//    /**
//     * <p>
//     * 根据 entity 条件，查询全部记录（并翻页）
//     * </p>
//     *
//     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
//     * @param queryWrapper 实体对象封装操作类（可以为 null）
//     */
//    @Test
//    public void selectPage() {
//        Page<TaskExecution> page = new Page<>(1, 5);
//        QueryWrapper<TaskExecution> queryWrapper = new QueryWrapper<>();
//
//        IPage<TaskExecution> TaskExecutionIPage = mapper.selectPage(page, queryWrapper);
//        System.out.println(TaskExecutionIPage);
//    }
//
//    /**
//     * 打印结果
//     * ==>  Preparing: SELECT COUNT(1) FROM TaskExecution
//     * ==> Parameters:
//     * <==    Columns: COUNT(1)
//     * <==        Row: 100
//     * ==>  Preparing: SELECT id,name,age,email,status FROM TaskExecution LIMIT 0,5
//     * ==> Parameters:
//     * <==    Columns: id, name, age, email, status
//     * <==        Row: 1046282328366391319, lqf, 12, lqf@163.com, 0
//     * <==        Row: 1046282328366391320, lqf, 12, lqf@163.com, 0
//     * <==        Row: 1046282328366391321, lqf, 12, lqf@163.com, 0
//     * <==        Row: 1046282328366391322, lqf, 12, lqf@163.com, 0
//     * <==        Row: 1046282328366391323, lqf, 12, lqf@163.com, 0
//     * <==      Total: 5
//     *
//     *
//     * 这里需要在项目中加入分页插件
//     *   @Bean
//     *     public PaginationInterceptor paginationInterceptor() {
//     *         return new PaginationInterceptor();
//     *     }
//     */
//
//
//    /**
//     * <p>
//     * 根据 Wrapper 条件，查询全部记录（并翻页）
//     * </p>
//     *
//     * @param page         分页查询条件
//     * @param queryWrapper 实体对象封装操作类
//     */
//    @Test
//    public void selectMapsPage() {
//        Page<TaskExecution> page = new Page<>(1, 5);
//        QueryWrapper<TaskExecution> queryWrapper = new QueryWrapper<>();
//
//        IPage<Map<String, Object>> mapIPage = mapper.selectMapsPage(page, queryWrapper);
//        System.out.println(mapIPage);
//    }
//
//    /**
//     * 和上个分页同理只是返回类型不同
//     */
//
//
//    /**
//     * <p>
//     * 根据 whereEntity 条件，更新记录
//     * </p>
//     *
//     * @param entity        实体对象 (set 条件值,不能为 null)
//     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
//     */
//    @Test
//    public void update() {
//
//        //修改值
//        TaskExecution TaskExecution = new TaskExecution();
//
//        //修改条件s
//        UpdateWrapper<TaskExecution> TaskExecutionUpdateWrapper = new UpdateWrapper<>();
//        TaskExecutionUpdateWrapper.eq("name", "lqf");
//
//        int update = mapper.update(TaskExecution, TaskExecutionUpdateWrapper);
//
//        System.out.println(update);
//    }
//
//    /**
//     * 打印结果
//     * ==>  Preparing: UPDATE TaskExecution SET name=?, status=? WHERE name = ?
//     * ==> Parameters: zhangsan(String), true(Boolean), lqf(String)
//     * <==    Updates: 100
//     * Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@56a4f272]
//     * 100
//     * 2018-10-02 15:08:03.928  INFO 7972 --- [       Thread-2] o.s.w.c.s.GenericWebApplicationContext   : Closing org.springframework.web.context.support.GenericWebApplicationContext@37313c65: startup date [Tue Oct 02 15:08:00 CST 2018]; root of context hierarchy
//     * 2018-10-02 15:08:03.937  INFO 7972 --- [       Thread-2] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
//     * 2018-10-02 15:08:04.053  INFO 7972 --- [       Thread-2] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
//     *
//     * Process finished with exit code 0
//     */
//
//}
