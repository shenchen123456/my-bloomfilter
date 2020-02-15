package com.bloomfilter;

import com.bloomfilter.config.BloomFilterHelper;
import com.bloomfilter.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.stream.IntStream;

//import org.junit.jupiter.api.Test;

@SpringBootTest(classes = {BloomfilterApplication.class})
@RunWith(SpringRunner.class)
public class BloomfilterApplicationTests {

    @Autowired
    RedisService redisService;

    @Autowired
    BloomFilterHelper<CharSequence> bloomFilterHelper;

    @Test
    public void contextLoads() {
        String key = "bloomfilter";
        IntStream.range(0, 1000).forEach(i -> {
            String v = UUID.randomUUID().toString();
            System.out.println(v);
            redisService.addByBloomFilter(bloomFilterHelper, key, v);
        });
        boolean r = redisService.includeByBloomFilter(bloomFilterHelper, key, "24f0b8d1-6f0d-4c51-b0ba-5445c1b90eac");
        System.out.println(r);
    }


}
