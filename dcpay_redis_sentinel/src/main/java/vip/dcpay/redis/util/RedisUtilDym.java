package vip.dcpay.redis.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.nutz.lang.Mirror;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import vip.dcpay.log.sdk.MyLogManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: liq
 * @Date: 2019/7/1 17:31
 * @Description:
 */
public class RedisUtilDym<T> {

    private String ex_message = "访问redis异常";
    private final String DB = "RedisDB:";

    protected JedisPool jedisPool;
    private JedisSentinelPool jedisSentinelPool;
    private Class<T> clazz;
    private String clazzName;
    private RuntimeSchema<T> schema;

    public RedisUtilDym(Class<T> clazz, JedisSentinelPool jedisSentinelPool) {
        this.clazz = clazz;
        this.clazzName = DB + this.clazz.getName().replace(".", ":");
        this.schema = RuntimeSchema.createFrom(clazz);
        this.jedisSentinelPool = jedisSentinelPool;
    }

    private Jedis getResource() {
        return jedisSentinelPool.getResource();
    }

    /**
     * 以map形式保存在缓存中，传一个map。
     *
     * @param key
     * @param map
     */
    public void hmset(String key, Map<String, String> map) {
        try (Jedis jedis = getResource()) {
            jedis.hmset(key, map);
        } catch (Exception e) {
            MyLogManager.error(ex_message, e);
            e.printStackTrace();
        }
    }

    /**
     * 获得一个对象
     */
    public T get(String id) {
        try (Jedis jedis = getResource()) {
            String key = this.clazzName + ":" + id;
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                T t = clazz.newInstance();
                ProtostuffIOUtil.mergeFrom(bytes, t, schema);
                return t;
            }
        } catch (Exception e) {
            MyLogManager.error(ex_message, e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存入一个对象
     */
    public void put(T t, String id) {
        String key;
        if (id == null) {
            Mirror<?> mirror = Mirror.me(t.getClass());
            // 如果创建时间存在就不能再次修改
            Object obj = mirror.getValue(t, "id");
            key = this.clazzName + ":" + obj;
        } else {
            key = this.clazzName + ":" + id;
        }
        try (Jedis jedis = getResource()) {
            jedis.del(key.getBytes());
            byte[] bytes = ProtostuffIOUtil.toByteArray(t, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            String set = jedis.set(key.getBytes(), bytes);
        } catch (Exception e) {
            MyLogManager.error(ex_message, e);
            e.printStackTrace();
        }
    }

    /**
     * 返回同类的所有对象List<T> list
     */
    public List<T> list() {
        List<T> list = new ArrayList<T>();
        try (Jedis jedis = getResource()) {
            Set<String> keys = jedis.keys(this.clazzName + "*");
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                byte[] bytes = jedis.get(iterator.next().getBytes());
                if (bytes != null) {
                    T t = clazz.newInstance();
                    ProtostuffIOUtil.mergeFrom(bytes, t, schema);
                    list.add(t);
                }
            }
        } catch (Exception e) {
            MyLogManager.error(ex_message, e);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 清空同类的所有对象
     */
    public void clear() {
        try (Jedis jedis = getResource()) {
            Set<String> keys = jedis.keys(this.clazzName + "*");
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                jedis.del(iterator.next());
            }
        } catch (Exception e) {
            MyLogManager.error(ex_message, e);
            e.printStackTrace();
        }
    }

    /**
     * 该方法可能会出现逾期外的结果 <br>
     * 若要删除某个id，请使用 {@code deleteOne(Long id)}
     */
    @Deprecated
    public void delete(String id) {
        try (Jedis jedis = getResource()) {
            String key = this.clazzName + ":" + id;
            Set<String> keys = jedis.keys(key);
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                jedis.del(iterator.next());
            }
        } catch (Exception e) {
            MyLogManager.error(ex_message, e);
            e.printStackTrace();
        }
    }

    /**
     * 删除某个id作为后缀的redis记录
     *
     * @param id
     * @return 删除的条数
     */
    public Long deleteOne(String id) {
        try (Jedis jedis = getResource()) {
            String key = this.clazzName + ":" + id;
            Long num = jedis.del(key);
            return num;
        } catch (Exception e) {
            MyLogManager.error(ex_message, e);
            e.printStackTrace();
        }
        return null;
    }


}
