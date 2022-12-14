package com.msa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class AuthApiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("spring version : " + SpringVersion.getVersion()); //spring version : 5.3.23
    }

}
