package com.elay.user.redis;

import java.util.Collection;

//@Service
public interface RedisService {

    public long getExpire(String key);

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public void expire(String key, long time);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key);

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key);

    /**
     * 批量删除key
     *
     * @param keys
     */
    public void del(Collection keys);

    /**
     * 模糊删除
     * @param key
     */
    public void deleteByPrex(String key);

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key);

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value);

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public void set(String key, Object value, long time);

    /**
     * 情况缓存
     */
    public void flush();
}
