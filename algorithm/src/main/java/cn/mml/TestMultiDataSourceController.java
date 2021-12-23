package cn.mml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController("TestMultiDataSourceController.v1")
@RequestMapping("/v1/test-multi-source")
public class TestMultiDataSourceController {

    //@Autowired
    //private RedisMultisourceClient multisourceClient;
  
//   	@Autowired
//    private RedisHelper redisHelper;
  
  	/**
  	 * 操作指定的数据源的指定db
  	 */
    @GetMapping("/test-1")
    public void test01() {
//        String key = "test-" + UUID.randomUUID().toString();
//        String value = "value-" + UUID.randomUUID().toString();
//        log.info("key = {}, value = {}", key, value);
//      	// 写入source1数据源的1号库
//        multisourceClient.opsDbOne("source1").opsForValue().set(key, value);
//     	  // 写入source2数据源的1号库
//      	multisourceClient.opsDbOne("source2").opsForValue().set(key, value);
//
//      	// 写入source1数据源的2号库
//        multisourceClient.opsDbOne("source1").opsForValue().set(key, value);
//     	  // 写入source2数据源的2号库
//      	multisourceClient.opsDbOne("source2").opsForValue().set(key, value);
    }
  
  	/**
     * 操作默认数据源的指定db
     */
    @GetMapping("/test-01")
    public void test012() {
        // 操作1号db
//        redisHelper.opsDbOne().opsForValue().set(getRandomValue(), getRandomValue());
//        // 操作2号db
//        redisHelper.opsDbTwo().opsForValue().set(getRandomValue(), getRandomValue());
//        // 操作3号db
//        redisHelper.opsDbThree().opsForValue().set(getRandomValue(), getRandomValue());
//        // 操作4号db
//        redisHelper.opsDbFour().opsForValue().set(getRandomValue(), getRandomValue());
    }
  
  	/**
     * 使用多数据源客户端操作默认数据源并且动态切换db
     */
  	@GetMapping("/test-100")
    public void test100() {
        // 操作默认的数据源的1号db
//        multisourceClient.opsDbOne(DEFAULT_SOURCE).opsForValue().set(getRandomValue(), getRandomValue());
//        // 操作默认的数据源的2号db
//        multisourceClient.opsDbTwo(DEFAULT_SOURCE).opsForValue().set(getRandomValue(), getRandomValue());
//        // 操作默认的数据源的3号db
//        multisourceClient.opsDbThree(DEFAULT_SOURCE).opsForValue().set(getRandomValue(), getRandomValue());
    }
}
