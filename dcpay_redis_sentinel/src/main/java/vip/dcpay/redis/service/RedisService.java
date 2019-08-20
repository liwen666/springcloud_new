package vip.dcpay.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Tuple;

/**
 * @Auther: liq
 * @Date: 2019/6/28 18:53
 * @Description:
 */
public interface RedisService {

	/**
	 * 发布消息
	 *
	 * @param room
	 * @param users
	 * @return
	 */
	Long publish(String room, String users);

	/**
	 * 锁定某个key5秒
	 * <p>
	 * 调用之前是锁定状态返回false 调用之前是未锁定状态返回true
	 */
	boolean lockKey5s(String key);

	/**
	 * 给某个key加锁
	 *
	 * @param key
	 * @return
	 */
	boolean lock(String key);

	/**
	 * @param key
	 * @param seconds
	 * @return
	 */
	boolean lock(String key, int seconds);

	/**
	 * 释放锁
	 *
	 * @param key
	 */
	void unLock(String key);

	/**
	 * 根据key 获得一个value值
	 *
	 * @param key
	 * @return
	 */
	String get(String key);

	/**
	 * 根据key保存和更新value
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	String save(String key, String value);

	/**
	 * 根据key保存和更新value，并设置一段时间后自动删除
	 *
	 * @param key
	 * @param value
	 * @param second
	 * @return
	 */
	String save(String key, String value, int second);

	/**
	 * 根据key删除
	 *
	 * @param key
	 * @return
	 */
	Long delete(String key);

	/**
	 * 根据key设置一条记录的过期时间 定时多少秒之后自动删除
	 *
	 * @param key
	 * @param second
	 * @return
	 */
	Long setTime(String key, int second);

	// -------------------- map操作 ------------------------------------

	/**
	 * 以map形式保存在缓存中，传一个map。
	 *
	 * @param key
	 * @param map
	 */
	String saveMap(String key, Map<String, String> map);

	/**
	 * 直接获取一个map类型的key返回一个map集合。
	 *
	 * @param key
	 * @return
	 */
	Map<String, String> getMap(String key);

	/**
	 * 可以传一个数组(数组里都是map里面的key),然后返回这些key里所对应的value。
	 *
	 * @param key
	 * @param str
	 * @return
	 */
	List<String> getMapKeys(String key, String[] str);

	/**
	 * 保存一个单个键值对进到map里.如果mKey已经存在于map里将会覆盖之前的值。
	 *
	 * @param key
	 * @param mKey
	 * @param value
	 */
	Long saveMap(String key, String mKey, String value);

	/**
	 * 通过键来获取值。
	 *
	 * @param key
	 * @param mKey
	 * @return
	 */
	String getMap(String key, String mKey);

	/**
	 * 删除某个key 所对应的value。
	 *
	 * @param key
	 * @param mKey
	 * @return
	 */
	Long delMapKey(String key, String mKey);

	/**
	 * 返回所有的keys.只针对map类型的key.
	 *
	 * @param key
	 * @return
	 */
	Set<String> findMapKeys(String key);

	/**
	 * 返回所有的value.只正对map类型的key.
	 *
	 * @param key
	 * @return
	 */
	List<String> findMapValue(String key);

	// ------------ list操作 ----------------------------------

	/**
	 * 保存一个值以list形式存储
	 *
	 * @param key
	 * @param lstValue
	 */
	Long saveList(String key, String lstValue);

	/**
	 * 查询所有
	 *
	 * @param key
	 * @return
	 */
	List<String> getList(String key);

	/**
	 * 将这个传入两个索引将获取任意一段索引的值。(注意： 索引从0开始)
	 *
	 * @param key
	 * @param sta
	 * @param end
	 * @return
	 */
	List<String> getListByIndex(String key, int sta, int end);

	/**
	 * 排序方法 。第二个参数值是"ASC","DESC"。分别表示升序或者降序。传入其他参数返回null.
	 *
	 * @param key
	 * @return
	 */
	List<String> sortList(String key, String sortName);

	/**
	 * 修改某个索引下的值。(索引从0开始)
	 *
	 * @param key
	 * @param index
	 * @param value
	 */
	String updateList(String key, int index, String value);

	/**
	 * 通过索引来获取list里面的值(索引从0开始)
	 *
	 * @param key
	 * @param index
	 * @return
	 */
	String getListIndex(String key, int index);

	/**
	 * 删除list集合中某个特定的值. 由于list是可重复的所以需要下面的方法来批量删除
	 *
	 * @param key
	 * @param value
	 */
	Long delListValue(String key, String value);

	/**
	 * 删除一定数目指定的值。
	 *
	 * @param key
	 * @param count
	 * @param value
	 */
	Long delListValueNum(String key, int count, String value);

	// ---------------- set方法的使用 ---------------------------------------

	/**
	 * 保存一个key以set形式保存
	 *
	 * @param key
	 * @param value
	 */
	Long saveSet(String key, String value);

	/**
	 * 获取指定key下set的所有元素。
	 *
	 * @param key
	 * @return
	 */
	Set<String> getSet(String key);

	/**
	 * 删除指定的值
	 */
	Long delSet(String key, String value);

	/**
	 * 判断一个key 下有没有这个值。
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	Boolean ynSetValue(String key, String value);

	// ------------------- SortedSet方法的使用 --------------------------

	/**
	 * SortedSet 是set与list的结合。中间的Double值可以看做是动态的索引。而且redis将会自动按照按照升序排序。 SortedSet
	 * 需要传一个Double型的参数 作为索引 ，redis并且会根据这个参数的大小来排序。
	 *
	 * @param key
	 * @param dob
	 * @param member
	 */
	Long saveSortedSet(String key, Double dob, String member);

	/**
	 * 获取所有的值
	 *
	 * @param key
	 * @return
	 */
	Set<String> getSortedSet(String key);

	/**
	 * 删除某个值
	 *
	 * @param key
	 * @param value
	 */
	Long delSortedSet(String key, String value);

	/**
	 * 删除从最小索引到最大索引的值
	 *
	 * @param key
	 * @param min
	 * @param max
	 */
	Long delSortedSetforIndex(String key, Double min, Double max);

	/**
	 * 返回当前值的索引
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	Double getSortedSetIndex(String key, String value);

	/**
	 * 返回在索引范围内的所有值
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	Set<String> getSortedSetIndex(String key, Double min, Double max);

	// ----------------------- 工具方法 -----------------------------------

	/**
	 * 以秒为单位返回 key 的剩余过期时间
	 *
	 * @param key
	 * @return 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key
	 *         的剩余生存时间
	 */
	Long getKeyTime(String key);

	/**
	 * 为指定的 key 设置值及其过期时间。如果 key 已经存在， 将会替换旧的值。
	 *
	 * @param key
	 * @param seconds
	 * @param value
	 * @return 设置成功时返回 OK
	 */
	String saveStringAndTime(String key, int seconds, String value);

	/**
	 * 检查给定 key 是否存在
	 *
	 * @param key
	 * @return
	 */
	Boolean ynKeys(String key);

	<T> List<T> getList1(String key);

	<T> String setList(String key, List<T> list);

	/**
	 * 查找所有包含参数 的 key
	 *
	 * @param patt
	 * @return 符合给定模式的 key 列表
	 */
	Set<String> keys(String patt);

	/**
	 * 删除所有包含参数 的 key
	 *
	 * @param patt
	 * @return
	 */
	Set<String> delkeys(String patt);

	/**
	 * 查找所有以参数起始 的 key
	 *
	 * @param patt
	 * @return
	 */
	Set<String> noPerkeys(String patt);

	/**
	 * 将 key 中储存的数字值增一。
	 * <p>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
	 * <p>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
	 * <p>
	 * 本操作的值限制在 64 位(bit)有符号数字表示之内。
	 *
	 * @param key
	 * @return 执行命令之后 key 的值
	 */
	Long incr(String key);

	/**
	 * 获得有序集合成员个数
	 */
	Long zcard(String key);

	/**
	 * 获得有序集中指定成员的位置，分数越大，位置越前，0是第一位
	 */
	Long zrevrank(String key, String member);

	/**
	 * 添加一个成员到有序集
	 */
	Long zadd(String key, double score, String member);

	/**
	 * 获得指定区间内的成员信息，不含分值
	 */
	Set<Tuple> zrangeWithScores(String key, long start, long end);

	/**
	 * 获得分值
	 */
	Double zscore(String key, String member);

	/**
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Set<Tuple> zrevrangeWithScores(String key, long start, long end);

	/**
	 * 添加到List中（同时设置过期时间）
	 * 
	 * @param key     key值
	 * @param seconds 过期时间 单位s
	 * @param value
	 * @return
	 */
	boolean lpush(String key, int seconds, String... value);

	/**
	 * 添加到List
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	boolean lpush(String key, String... value);

	/**
	 * 获取队列数据
	 * 
	 * @param byte[] key 键名
	 * @return
	 */
	byte[] rpop(byte[] key);

	/**
	 * @param key
	 * @return
	 */
	String rpop(String key);

	/**
	 * 获取队列数据
	 * 
	 * @return
	 */
	List<String> brpop(int seconds, String key);

}
