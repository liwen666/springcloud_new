package vip.dcpay.redis.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.util.Pool;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.redis.service.RedisService;
import vip.dcpay.redis.util.SerializeUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: liq
 * @Date: 2019/6/28 18:53
 * @Description:
 */
public class RedisServiceImpl implements RedisService {

    private String ex_message = "访问redis异常";

    private Pool<Jedis> jedisPool;

    public RedisServiceImpl(Pool<Jedis> jedisPool) {
        Assert.isTrue(null != jedisPool, "连接池初始化失败");

        this.jedisPool = jedisPool;
    }

    private Jedis getResource() {
        return jedisPool.getResource();
    }

    @Override
    public Long publish(String room, String users) {
        try (Jedis jedis = getResource()) {
            return jedis.publish(room, users);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return 0L;
    }

    @Override
    public boolean lockKey5s(String key) {
        boolean isNewlock = false;
        try (Jedis j = getResource()) {
            key = "lock_" + key;
            int num = j.setnx(key, System.currentTimeMillis() + 5000L + "").intValue();
            if (num == 1) {
                j.expire(key, 5);
                isNewlock = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);

        }
        return isNewlock;
    }

    @Override
    public boolean lock(String key) {
        return lock(key, 5);
    }

    @Override
    public boolean lock(String key, int seconds) {
        boolean lockSuccess = false;
        try (Jedis jedis = getResource()) {
            String lockKey = "lock_" + key;
            String value = System.currentTimeMillis() + seconds * 1000 + "";
            long result = jedis.setnx(lockKey, value);
            if (result == 1) {
                // 设置锁的存活时间为n秒
                jedis.expire(lockKey, seconds);
                lockSuccess = true;
                return lockSuccess;
            } else {
                String lockTimeStr = jedis.get(lockKey);
                if (StringUtils.isNumeric(lockTimeStr)) {
                    // 如果key存在，锁存在
                    long lockTime = Long.valueOf(lockTimeStr);
                    if (lockTime < System.currentTimeMillis()) {
                        // 锁已过期
                        String newValue = System.currentTimeMillis() + seconds * 1000 + "";
                        String originStr = jedis.getSet(lockKey, String.valueOf(newValue));
                        jedis.expire(lockKey, seconds);
                        if (StringUtils.isNoneBlank(originStr) && originStr.equals(lockTimeStr)) {
                            // 表明锁由该线程获得
                            lockSuccess = true;
                            return lockSuccess;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            lockSuccess = false;
            MyLogManager.error(ex_message, e);
        }
        return lockSuccess;
    }

    @Override
    public void unLock(String key) {
        try (Jedis jedis = getResource()) {
            String lockKey = "lock_" + key;
            jedis.del(lockKey);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
    }

    @Override
    public String get(String key) {
        try (Jedis jedis = getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public String save(String key, String value) {
        try (Jedis jedis = getResource()) {
            return jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public String save(String key, String value, int second) {
        try (Jedis jedis = getResource()) {
            String result = jedis.set(key, value);
            jedis.expire(key, second);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public Long delete(String key) {
        try (Jedis jedis = getResource()) {
            return jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return (long) 0;
        }
    }

    @Override
    public Long setTime(String key, int second) {
        try (Jedis jedis = getResource()) {
            return jedis.expire(key, second);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return 0L;
    }

    // -------------------- map操作 ------------------------------------

    @Override
    public String saveMap(String key, Map<String, String> map) {
        try (Jedis jedis = getResource()) {
            return jedis.hmset(key, map);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Map<String, String> getMap(String key) {
        try (Jedis jedis = getResource()) {
            Map<String, String> map = jedis.hgetAll(key);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public List<String> getMapKeys(String key, String[] str) {
        try (Jedis jedis = getResource()) {
            List<String> list = jedis.hmget(key, str);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Long saveMap(String key, String mKey, String value) {
        try (Jedis jedis = getResource()) {
            return jedis.hset(key, mKey, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return -1l;
    }

    @Override
    public String getMap(String key, String mKey) {
        try (Jedis jedis = getResource()) {
            String ss = jedis.hget(key, mKey);
            return ss;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Long delMapKey(String key, String mKey) {
        try (Jedis jedis = getResource()) {
            return jedis.hdel(key, mKey);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Set<String> findMapKeys(String key) {
        try (Jedis jedis = getResource()) {
            Set<String> ss = jedis.hkeys(key);
            return ss;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public List<String> findMapValue(String key) {
        try (Jedis jedis = getResource()) {
            List<String> ss = jedis.hvals(key);
            return ss;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    // ------------ list操作 ----------------------------------

    @Override
    public Long saveList(String key, String lstValue) {
        try (Jedis jedis = getResource()) {
            return jedis.lpush(key, lstValue);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return -1L;
    }

    @Override
    public List<String> getList(String key) {
        try (Jedis jedis = getResource()) {
            List<String> list = jedis.lrange(key, 0, -1);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public List<String> getListByIndex(String key, int sta, int end) {
        try (Jedis jedis = getResource()) {
            List<String> list = jedis.lrange(key, sta, end);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public List<String> sortList(String key, String sortName) {
        try (Jedis jedis = getResource()) {

            SortingParams sortingParameters = new SortingParams();
            SortingParams alpha = sortingParameters.alpha();
            if (sortName.equals("DESC")) {
                SortingParams desc = alpha.desc();
                List<String> sortList = jedis.sort(key, desc);
                return sortList;
            }
            if (sortName.equals("ASC")) {
                List<String> sort = jedis.sort(key, alpha);
                return sort;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public String updateList(String key, int index, String value) {
        try (Jedis jedis = getResource()) {
            return jedis.lset(key, index, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public String getListIndex(String key, int index) {
        try (Jedis jedis = getResource()) {
            String s = jedis.lindex(key, index);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Long delListValue(String key, String value) {
        try (Jedis jedis = getResource()) {
            return jedis.lrem(key, 1, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return -1L;
    }

    @Override
    public Long delListValueNum(String key, int count, String value) {
        try (Jedis jedis = getResource()) {
            return jedis.lrem(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return -1L;
    }

    // ---------------- set方法的使用 ---------------------------------------

    @Override
    public Long saveSet(String key, String value) {
        try (Jedis jedis = getResource()) {
            return jedis.sadd(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return -1L;
    }

    @Override
    public Set<String> getSet(String key) {
        try (Jedis jedis = getResource()) {
            Set<String> s = jedis.smembers(key);
            return s;
        } catch (Exception e) {
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public Long delSet(String key, String value) {
        try (Jedis jedis = getResource()) {
            return jedis.srem(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return 0L;
        }
    }

    @Override
    public Boolean ynSetValue(String key, String value) {
        Boolean b = false;
        try (Jedis jedis = getResource()) {
            b = jedis.sismember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return b;
    }

    // ------------------- SortedSet方法的使用 --------------------------

    @Override
    public Long saveSortedSet(String key, Double dob, String member) {
        try (Jedis jedis = getResource()) {
            return jedis.zadd(key, dob, member);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public Set<String> getSortedSet(String key) {
        try (Jedis jedis = getResource()) {
            Set<String> set = jedis.zrange(key, 0, -1);
            return set;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public Long delSortedSet(String key, String value) {
        try (Jedis jedis = getResource()) {
            return jedis.zrem(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public Long delSortedSetforIndex(String key, Double min, Double max) {
        try (Jedis jedis = getResource()) {
            return jedis.zcount(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public Double getSortedSetIndex(String key, String value) {
        try (Jedis jedis = getResource()) {
            Double dob = jedis.zscore(key, value);
            return dob;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public Set<String> getSortedSetIndex(String key, Double min, Double max) {
        try (Jedis jedis = getResource()) {
            Set<String> set = jedis.zrangeByScore(key, min, max);
            return set;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    // ----------------------- 工具方法 -----------------------------------

    @Override
    public Long getKeyTime(String key) {
        try (Jedis jedis = getResource()) {
            Long l = jedis.ttl(key);
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public String saveStringAndTime(String key, int seconds, String value) {
        try (Jedis jedis = getResource()) {
            String setex = jedis.setex(key, seconds, value);
            if (setex.equals("OK")) {
                return setex;
            } else {
                return "NO";
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return null;
        }
    }

    @Override
    public Boolean ynKeys(String key) {
        Boolean b = false;
        try (Jedis jedis = getResource()) {
            b = jedis.exists(key);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
            return b;
        }
    }

    @Override
    public <T> List<T> getList1(String key) {
        try (Jedis jedis = getResource()) {
            byte[] in = jedis.get(key.getBytes());
            @SuppressWarnings("unchecked")
            List<T> list = (List<T>) SerializeUtil.unserialize(in);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public <T> String setList(String key, List<T> list) {
        try (Jedis jedis = getResource()) {
            return jedis.set(key.getBytes(), SerializeUtil.serialize(list));
        } catch (Exception e) {

            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Set<String> keys(String patt) {
        try (Jedis jedis = getResource()) {
            Set<String> keys = jedis.keys("*" + patt + "*");
            return keys;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Set<String> delkeys(String patt) {
        try (Jedis jedis = getResource()) {
            Set<String> keys = jedis.keys("*" + patt + "*");
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                jedis.del(iterator.next());
            }
            return keys;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Set<String> noPerkeys(String patt) {
        try (Jedis jedis = getResource()) {
            Set<String> keys = jedis.keys(patt + "*");
            return keys;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Long incr(String key) {
        try (Jedis jedis = getResource()) {
            return jedis.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Long zcard(String key) {
        try (Jedis jedis = getResource()) {
            return jedis.zcard(key);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Long zrevrank(String key, String member) {
        try (Jedis jedis = getResource()) {
            return jedis.zrevrank(key, member);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        try (Jedis jedis = getResource()) {
            return jedis.zadd(key, score, member);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        try (Jedis jedis = getResource()) {
            Set<Tuple> set = jedis.zrangeWithScores(key, start, end);
            if (set == null) {
                set = Collections.emptySet();
            }
            return set;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return Collections.emptySet();
    }

    @Override
    public Double zscore(String key, String member) {
        try (Jedis jedis = getResource()) {
            return jedis.zscore(key, member);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        try (Jedis jedis = getResource()) {
            Set<Tuple> set = jedis.zrevrangeWithScores(key, start, end);
            if (set == null) {
                set = Collections.emptySet();
            }
            return set;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return Collections.emptySet();
    }

    @Override
    public boolean lpush(String key, int seconds, String... value) {
        boolean result = this.lpush(key, value);
        if (result) {
            try (Jedis jedis = getResource()) {
                // 设置时间
                jedis.expire(key, seconds);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                MyLogManager.error(ex_message, e);
            }
        }
        return false;
    }

    @Override
    public boolean lpush(String key, String... value) {
        try (Jedis jedis = getResource()) {
            if (key == null || value == null) {
                return false;
            }
            jedis.lpush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return false;
    }

    @Override
    public byte[] rpop(byte[] key) {
        byte[] bytes = null;
        try (Jedis jedis = getResource()) {
            bytes = jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return bytes;
    }

    @Override
    public String rpop(String key) {
        String data = null;
        try (Jedis jedis = getResource()) {
            data = jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return data;
    }

    @Override
    public List<String> brpop(int seconds, String key) {
        List<String> result = null;
        try (Jedis jedis = getResource()) {
            result = jedis.brpop(seconds, key);
        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error(ex_message, e);
        }
        return result;
    }

}
