package priv.zhouhuayi.framework.util.redis;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
  
/** 
 *  
 * @author zhy 
 * 
 */  
public class RedisDealUtil {  
      
    private final static String LIST_NAME = ":list";  
      
    private final static String MAP_NAME = ":map";  
      
    private final static int SIZE = 1000;  
      
    /** 
     * 倒序初始化数据到redis 
     *  
     * @author zhy 
     * @param key redis存储的键 
     * @param listData 存储的集合数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @return 
     */  
    public static boolean initListDesc(String key, List<Map<String, Object>> listData, String uniqueColumn) {  
        boolean bool = true;  
        Jedis jedis = null;  
        int listSize = listData.size();  
        try {  
            jedis = RedisConnUtil.getJedis();  
            if(listSize > SIZE) {  
                int num = listSize / SIZE + ((listSize % SIZE) > 0 ? 1 : 0);  
                for (int i = 0; i < num; i++) {  
                    addListRightDeal(jedis, listData, uniqueColumn, key, (i * SIZE));  
                }  
            } else {  
                addListRightDeal(jedis, listData, uniqueColumn, key);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 升序初始化数据到redis 
     *  
     * @author zhy 
     * @param key redis存储的键 
     * @param listData 存储的集合数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @return 
     */  
    public static boolean initListAesc(String key, List<Map<String, Object>> listData, String uniqueColumn) {  
        boolean bool = true;  
        Jedis jedis = null;  
        int listSize = listData.size();  
        try {  
            jedis = RedisConnUtil.getJedis();  
            if(listSize > SIZE) {  
                int num = listSize / SIZE + ((listSize % SIZE) > 0 ? 1 : 0);  
                for (int i = 0; i < num; i++) {  
                    addListLeftDeal(jedis, listData, uniqueColumn, key, (i * SIZE));  
                }  
            } else {          
                addListLeftDeal(jedis, listData, uniqueColumn, key);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 倒序追加数据到redis 
     *  
     * @author zhy 
     * @param key redis存储的键 
     * @param listData 存储的集合数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @return 
     */  
    public static boolean appendListDesc(String key, List<Map<String, Object>> listData, String uniqueColumn) {  
        boolean bool = true;  
        Jedis jedis = null;  
        int listSize = listData.size();  
        try {  
            jedis = RedisConnUtil.getJedis();  
            Pipeline pline = jedis.pipelined();  
            for (int i = listSize - 1; i > - 1 ; i--) {  
                Map<String, Object> map = listData.get(i);  
                String uniqueColumnVlue = map.get(uniqueColumn).toString();  
                String value = JSON.toJSONString(map);  
                pline.hset(key+MAP_NAME, uniqueColumnVlue, value);  
                pline.rpush(key+LIST_NAME, uniqueColumnVlue);  
            }  
            pline.sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 升序追加数据到redis 
     *  
     * @author zhy 
     * @param key redis存储的键 
     * @param listData 存储的集合数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @return 
     */  
    public static boolean appendListAesc(String key, List<Map<String, Object>> listData, String uniqueColumn) {  
        boolean bool = true;  
        Jedis jedis = null;  
        try {  
            jedis = RedisConnUtil.getJedis();  
            addListLeftDeal(jedis, listData, uniqueColumn, key);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 左侧追加数据到redis 
     *  
     * @author zhy 
     * @param jedis jedis连接 
     * @param key redis存储的键 
     * @param listData 存储的集合数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @return 
     */  
    private static void addListLeftDeal(Jedis jedis, List<Map<String, Object>> listData, String uniqueColumn, String key) {  
        try {  
            int listSize = listData.size();  
            Pipeline pline = jedis.pipelined();  
            for (int i = 0; i < listSize ; i++) {  
                Map<String, Object> map = listData.get(i);  
                appendMapLeft(pline, map, uniqueColumn, key);  
            }  
            pline.sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 右侧追加数据到redis 
     *  
     * @author zhy 
     * @param jedis jedis连接 
     * @param key redis存储的键 
     * @param listData 存储的集合数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @param start 循环开始位置 
     * @return 
     */  
    private static void addListLeftDeal(Jedis jedis, List<Map<String, Object>> listData, String uniqueColumn, String key, int start) {  
        try {  
            int listSize = listData.size();  
            int numData = listSize - start;  
            numData = numData > SIZE ? SIZE : numData;  
            Pipeline pline = jedis.pipelined();  
            for (int j = 0; j < numData; j++) {  
                Map<String, Object> map = listData.get(start + j);  
                appendMapLeft(pline, map, uniqueColumn, key);  
            }  
            pline.sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 右侧追加数据到redis 
     *  
     * @author zhy 
     * @param jedis jedis连接 
     * @param key redis存储的键 
     * @param listData 存储的集合数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @return 
     */  
    private static void addListRightDeal(Jedis jedis, List<Map<String, Object>> listData, String uniqueColumn, String key) {  
        try {  
            int listSize = listData.size();  
            Pipeline pline = jedis.pipelined();  
            for (int i = 0; i < listSize ; i++) {  
                Map<String, Object> map = listData.get(i);  
                appendMapRight(pline, map, uniqueColumn, key);  
            }  
            pline.sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 右侧追加数据到redis 
     *  
     * @author zhy 
     * @param jedis jedis连接 
     * @param key redis存储的键 
     * @param listData 存储的集合数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @param start 循环开始位置 
     * @return 
     */  
    private static void addListRightDeal(Jedis jedis, List<Map<String, Object>> listData, String uniqueColumn, String key, int start) {  
        try {  
            int listSize = listData.size();  
            int numData = listSize - start;  
            numData = numData > SIZE ? SIZE : numData;  
            Pipeline pline = jedis.pipelined();  
            for (int j = 0; j < numData; j++) {  
                Map<String, Object> map = listData.get(start + j);  
                appendMapRight(pline, map, uniqueColumn, key);  
            }  
            pline.sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 拼接Map数据到redis List 左侧 
     *  
     * @author zhy 
     * @param jedis jedis连接 
     * @param map 添加的map数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @param key redis存储的键 
     */  
    private static void appendMapLeft(Pipeline pline, Map<String, Object> map, String uniqueColumn, String key) {  
        try {  
            String uniqueColumnVlue = map.get(uniqueColumn).toString();  
            String value = JSON.toJSONString(map);  
            pline.hset(key+MAP_NAME, uniqueColumnVlue, value);  
            pline.lpush(key+LIST_NAME, uniqueColumnVlue);  
            pline.sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 拼接Map数据到redis List 左侧 
     *  
     * @author zhy 
     * @param jedis jedis连接 
     * @param map 添加的map数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @param key redis存储的键 
     */  
    private static void appendMapRight(Pipeline pline, Map<String, Object> map, String uniqueColumn, String key) {  
        try {  
            String uniqueColumnVlue = map.get(uniqueColumn).toString();  
            String value = JSON.toJSONString(map);  
            pline.hset(key+MAP_NAME, uniqueColumnVlue, value);  
            pline.rpush(key+LIST_NAME, uniqueColumnVlue);  
            pline.sync();   
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 往List 追加Map 到左侧 
     *  
     * @author zhy 
     * @param map 添加的map数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @param key redis存储的键 
     * @return 
     */  
    public static boolean appendMapLeft(String key, Map<String, Object> map, String uniqueColumn) {  
        boolean bool = true;  
        Jedis jedis = RedisConnUtil.getJedis();  
        try {  
            Pipeline pline = jedis.pipelined();  
            String uniqueColumnVlue = map.get(uniqueColumn).toString();  
            String value = JSON.toJSONString(map);  
            pline.hset(key+MAP_NAME, uniqueColumnVlue, value);  
            pline.lpush(key+LIST_NAME, uniqueColumnVlue);  
            pline.sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
            bool = false;  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 往List 追加Map 到右侧 
     *  
     * @author zhy 
     * @param map 添加的map数据 
     * @param uniqueColumn list唯一不重复值的字段名称 
     * @param key redis存储的键 
     * @return 
     */  
    public static boolean appendMapRight(String key, Map<String, Object> map, String uniqueColumn) {  
        boolean bool = true;  
        Jedis jedis = RedisConnUtil.getJedis();  
        try {  
            Pipeline pline = jedis.pipelined();  
            String uniqueColumnVlue = map.get(uniqueColumn).toString();  
            String value = JSON.toJSONString(map);  
            pline.hset(key+MAP_NAME, uniqueColumnVlue, value);  
            pline.rpush(key+LIST_NAME, uniqueColumnVlue);  
            pline.sync();  
        } catch (Exception e) {  
            e.printStackTrace();  
            bool = false;  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 获取list集合 
     *  
     * @author zhy 
     * @param key 键名 
     * @param start 开始位置 
     * @param end 结束位置 
     * @return 
     */  
    public static JSONArray getListByRange(String key, Long start, Long end) {  
        JSONArray jsonArray = null;  
        Jedis jedis = null;  
        try {  
            jedis = RedisConnUtil.getJedis();  
            List<String> list = jedis.lrange(key+LIST_NAME, start, end);  
            String[] keyArray = list.toArray(new String[list.size()]);  
            jsonArray = JSON.parseArray(JSON.toJSONString(jedis.hmget(key + MAP_NAME, keyArray)));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        RedisConnUtil.close(jedis);  
        return jsonArray;  
    }  
      
    /** 
     * 获取list集合 
     *  
     * @author zhy 
     * @param key 键名 
     * @param pageNum 起始页 
     * @param pageSize 每页的大小 
     * @return 
     */  
    public static JSONArray getListByPage(String key, int pageNum, int pageSize) {  
        JSONArray jsonArray = null;  
        Jedis jedis = null;  
        long start = (pageNum - 1) * pageSize;  
        long end = pageNum * pageSize - 1;  
        try {  
            jedis = RedisConnUtil.getJedis();  
            List<String> list = jedis.lrange(key+LIST_NAME, start, end);  
            jsonArray = JSON.parseArray(JSON.toJSONString(jedis.hmget(key + MAP_NAME, list.toArray(new String[pageSize]))));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        RedisConnUtil.close(jedis);  
        return jsonArray;  
    }  
      
    /** 
     * 获取Hash中的值 
     *  
     * @author zhy 
     * @param key 键名 
     * @param uniqueColumnValue 存储进来的唯一字段值 
     * @return 
     */  
    public static JSON getListHash(String key, String uniqueColumnValue) {  
        JSON json = null;  
        Jedis jedis = null;  
        try {  
            jedis = RedisConnUtil.getJedis();  
            json = JSON.parseObject(jedis.hget(key + MAP_NAME, uniqueColumnValue));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        RedisConnUtil.close(jedis);  
        return json;  
    }  
      
    /** 
     * 修改数据 
     *  
     * @author zhy 
     * @param key 键名 
     * @param uniqueColumnValue 存储进来的唯一字段值 
     * @param mapData 要更新的数据 
     * @return 
     */  
    public static boolean updateList(String key, String uniqueColumnValue, Map<String, Object> mapData) {  
        boolean bool = true;  
        Jedis jedis = null;  
        try {  
            jedis = RedisConnUtil.getJedis();  
            bool = jedis.hset(key + MAP_NAME, uniqueColumnValue, JSON.toJSONString(mapData)) > 0 ? false : true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            bool = false;  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 删除字段 
     *  
     * @author zhy 
     * @param key 键名 
     * @param uniqueColumnValue 存储进来的唯一字段值 
     * @return 
     */  
    public static boolean deleteList(String key, String uniqueColumnValue) {  
        boolean bool = true;  
        Jedis jedis = null;  
        try {  
            jedis = RedisConnUtil.getJedis();  
            jedis.hdel(key + MAP_NAME, uniqueColumnValue);  
            jedis.lrem(key + LIST_NAME, 0, uniqueColumnValue);  
        } catch (Exception e) {  
            e.printStackTrace();  
            bool = false;  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 删除Key 
     *  
     * @author zhy 
     * @param key 键名 
     * @return 
     */  
    public static boolean delKey(String key) {  
        boolean bool = true;  
        Jedis jedis = null;  
        try {  
            jedis = RedisConnUtil.getJedis();  
            jedis.del(key + MAP_NAME);  
            jedis.del(key + LIST_NAME);  
        } catch (Exception e) {  
            e.printStackTrace();  
            bool = false;  
        }  
        RedisConnUtil.close(jedis);  
        return bool;  
    }  
      
    /** 
     * 获取list长度 
     *  
     * @author zhy 
     * @param key 键名 
     * @return 
     */  
    public static long getListLen(String key) {  
        long len = 0;  
        Jedis jedis = null;  
        try {  
            jedis = RedisConnUtil.getJedis();  
            len = jedis.llen(key + LIST_NAME);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        RedisConnUtil.close(jedis);  
        return len;  
    }  
}  