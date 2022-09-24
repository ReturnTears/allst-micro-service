package com.allst.micro.mq.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/**
 * @author Hutu
 * @since 2022-09-24 下午 11:34
 */
@Slf4j
@Component
public abstract class AbstractMqListener<T> {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     *
     */
    public boolean checkMessageId(String messageId) {
        if (redisTemplate.opsForHash().hasKey("edu_mq", messageId)) {
            return true;
        }
        return false;
    }

    /**
     *
     */
    public void updateMessageId(String messageId) {
        redisTemplate.opsForHash().put("edu_mq", messageId, messageId);
        //TODO ma weilong  数据多了可以设置个比较长的有效期  或者持久化到 其他地方 或者定期清理下数据
        //redisTemplate.expire("edu_mq", 30, TimeUnit.DAYS);
    }

    /**
     *
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    abstract public void onMessage(T message);

    /**
     *
     */
    @Recover
    public void recover(Exception ex, Object arg0) {
        log.error("AbstractMqListener - recover - arg0:{} ex", JSON.toJSONString(arg0), ex);
    }
}
