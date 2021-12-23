package com.cn.redis;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.RedissonLockEntry;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.client.protocol.RedisStrictCommand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class redislock {

    @Autowired
    private Redisson redisson;

    public String deductStock(){

        String lockKey ="";
        RLock redissonLock = redisson.getLock(lockKey);

        try{
            redissonLock.lock();
            /*
            逻辑代码
             */
        }finally {

            redissonLock.unlock();
        }

        return null;
    }

//    public void lock() {
//        try {
//            this.lockInterruptibly();
//        } catch (InterruptedException var2) {
//            Thread.currentThread().interrupt();
//        }
//
//    }

//    publi调用代理人系统异常 void lockInterruptibly() throws InterruptedException {
//        this.lockInterruptibly(-1L, (TimeUnit)null);
//    }
//    public void lockInterruptibly(long leaseTime, TimeUnit unit) throws InterruptedException {
//        long threadId = Thread.currentThread().getId();
//        Long ttl = this.tryAcquire(leaseTime, unit, threadId);
//        if (ttl != null) {
//            RFuture<RedissonLockEntry> future = this.subscribe(threadId);
//            this.commandExecutor.syncSubscription(future);
//
//            try {
//                while(true) {
//                    ttl = this.tryAcquire(leaseTime, unit, threadId);
//                    if (ttl == null) {
//                        return;
//                    }
//
//                    if (ttl >= 0L) {
//                        this.getEntry(threadId).getLatch().tryAcquire(ttl, TimeUnit.MILLISECONDS);
//                    } else {
//                        this.getEntry(threadId).getLatch().acquire();
//                    }
//                }
//            } finally {
//                this.unsubscribe(future, threadId);
//            }
//        }
//    }
//
//    private Long tryAcquire(long leaseTime, TimeUnit unit, long threadId) {
//        return (Long)this.get(this.tryAcquireAsync(leaseTime, unit, threadId));
//    }
//    private <T> RFuture<Long> tryAcquireAsync(long leaseTime, TimeUnit unit, final long threadId) {
//        if (leaseTime != -1L) {
//            return this.tryLockInnerAsync(leaseTime, unit, threadId, RedisCommands.EVAL_LONG);
//        } else {
//            RFuture<Long> ttlRemainingFuture = this.tryLockInnerAsync(this.commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout(), TimeUnit.MILLISECONDS, threadId, RedisCommands.EVAL_LONG);
//            ttlRemainingFuture.addListener(new FutureListener<Long>() {
//                public void operationComplete(Future<Long> future) throws Exception {
//                    if (future.isSuccess()) {
//                        Long ttlRemaining = (Long)future.getNow();
//                        if (ttlRemaining == null) {
//                            RedissonLock.this.scheduleExpirationRenewal(threadId);
//                        }
//
//                    }
//                }
//            });
//            return ttlRemainingFuture;
//        }
//    }
//    <T> RFuture<T> tryLockInnerAsync(long leaseTime, TimeUnit unit, long threadId, RedisStrictCommand<T> command) {
//        this.internalLockLeaseTime = unit.toMillis(leaseTime);
//        return this.commandExecutor.evalWriteAsync(this.getName(), LongCodec.INSTANCE, command,
//                "if (redis.call('exists', KEYS[1]) == 0) then " +
//                        "redis.call('hset', KEYS[1], ARGV[2], 1); " +
//                        "redis.call('pexpire', KEYS[1], ARGV[1]); " +
//                        "return nil; end; " +
//                        "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
//                        "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
//                        "redis.call('pexpire', KEYS[1], ARGV[1]); " +
//                        "return nil; end; return redis.call('pttl', KEYS[1]);",
//                Collections.singletonList(this.getName()),
//                new Object[]{this.internalLockLeaseTime, this.getLockName(threadId)});
//    }
//
//
//    public boolean tryLock() {
//        return (Boolean)this.get(this.tryLockAsync());
//    }
//
//    private void scheduleExpirationRenewal(final long threadId) {
//        if (!expirationRenewalMap.containsKey(this.getEntryName())) {
//
//
//            Timeout task = this.commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
//                public void run(Timeout timeout) throws Exception {
//                    RFuture<Boolean> future = RedissonLock.this.commandExecutor.evalWriteAsync(RedissonLock.this.getName(),
//                            LongCodec.INSTANCE, RedisCommands.EVAL_BOOLEAN,
//                            "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
//                                    "redis.call('pexpire', KEYS[1], ARGV[1]); " +
//                                    "return 1; end; return 0;",
//                            Collections.singletonList(RedissonLock.this.getName()),
//                            new Object[]{RedissonLock.this.internalLockLeaseTime, RedissonLock.this.getLockName(threadId)});
//                    future.addListener(new FutureListener<Boolean>() {
//                        public void operationComplete(Future<Boolean> future) throws Exception {
//                            RedissonLock.expirationRenewalMap.remove(RedissonLock.this.getEntryName());
//                            if (!future.isSuccess()) {
//                                RedissonLock.log.error("Can't update lock " + RedissonLock.this.getName() + " expiration", future.cause());
//                            } else {
//                                if ((Boolean)future.getNow()) {
//                                    RedissonLock.this.scheduleExpirationRenewal(threadId);
//                                }
//
//                            }
//                        }
//                    });
//                }
//            }, this.internalLockLeaseTime / 3L, TimeUnit.MILLISECONDS);
//            if (expirationRenewalMap.putIfAbsent(this.getEntryName(), task) != null) {
//                task.cancel();
//            }
//
//        }
//    }
}
