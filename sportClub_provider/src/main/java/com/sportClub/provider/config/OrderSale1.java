//package com.sportClub.provider.config;
//
///**
// * @Author: YangF
// * @Date: 2020-08-25 21:38
// * @description:
// */
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Transaction;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class OrderSale1 {
//    private static Integer MAX_GOODS_NUM = 10;
//    private static final Integer MAX_USER_NUM = 100;
//    private static final String LOCK_KEY = "LOCK_KEY";
//
//    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_USER_NUM);
//        for (int i = 0; i < MAX_USER_NUM; i++) {
//            threadPool.execute(new Runnable() {
//                public void run() {
//                    Jedis jedis = new Jedis("192.168.227.131", 6379);
//                    String lock = lock(jedis, LOCK_KEY, 5000L, 5000L);  //加锁
//                    if (lock == null || lock == "") {
//                        System.out.println("服务器正忙，请稍后在试");
//                        return;
//                    }
//                    if (MAX_GOODS_NUM > 0) {
//                        MAX_GOODS_NUM--;
//                        System.out.println("抢到了, 库存剩余" + MAX_GOODS_NUM + "件");
//                    } else {
//                        System.out.println("!!!!!! 抢购失败， 库存剩余" + MAX_GOODS_NUM + "件");
//                    }
//                    unlock(jedis, LOCK_KEY, lock);   //释放锁
//                }
//            });
//        }
//    }
//
//    private static String lock(Jedis jedis, String lockName, Long acquireTimeout, Long processTimeout) {
//        String key = lockName;
//        String value = UUID.randomUUID().toString(); //全球唯一标识符
//        String result = null;
//        Long success = 1L;
//        Long persist = -1L;
//        Long endtime = System.currentTimeMillis() + acquireTimeout;
//        while (System.currentTimeMillis() < endtime) {
//            Long setnx = jedis.setnx(key, value);
//            if (success.equals(setnx)) {
//                result = value;
//                jedis.expire(key, (int) (processTimeout / 1000));
//                break;
//            }
//            if (persist.equals(jedis.ttl(key))) {
//                jedis.expire(key, (int) (processTimeout / 1000));
//            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//        return result;
//    }
//
//    private static boolean unlock(Jedis jedis, String lockName, String uuid) {
//        String key = lockName;
//        while (true) {
//            String value = jedis.get(key);
//            if (value.equals(uuid)) {
//                jedis.watch(key);
//                Transaction tran = jedis.multi();
//                tran.del(key);
//                List<Object> result = tran.exec();
//                if (null == result) {
//                    jedis.unwatch();
//                    continue;
//                }
//                jedis.unwatch();
//                return true;
//            }
//            break;
//        }
//        return false;
//    }
//}
