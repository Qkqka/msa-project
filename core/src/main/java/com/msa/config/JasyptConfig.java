package com.msa.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://github.com/ulisesbocchio/jasypt-spring-boot
 * @author fnfnksb@gmail.com
 */
@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String key;

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key); // 암호화할 때 사용하는 키 --> VM Option에 설정
        config.setAlgorithm("PBEWithMD5AndDES"); // 암호화 알고리즘 - default PBEWITHHMACSHA512ANDAES_256
        config.setKeyObtentionIterations("1000"); // 반복할 해싱 회수 - default 1000
        config.setPoolSize("1"); // 인스턴스 pool - default 1
        config.setProviderName("SunJCE"); // - default SunJCE
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스 - default org.jasypt.salt.RandomSaltGenerator
        config.setStringOutputType("base64"); //인코딩 방식 - default base64

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(config);

        return encryptor;
    }

}
