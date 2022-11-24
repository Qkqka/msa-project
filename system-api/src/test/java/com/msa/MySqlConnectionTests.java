package com.msa;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySqlConnectionTests {

    @Value("${application.datasource.writer.driver-class-name}")
    private String driver;

    @Value("${application.datasource.writer.jdbc-url}")
    private String url;

    @Value("${application.datasource.writer.username}")
    private String username;

    @Value("${application.datasource.writer.password}")
    private String password;

    @Test
    public void testConnection() throws Exception {
        // DBMS에게 DB 연결 드라이버의 위치를 알려주기 위한 메소드
        Class.forName(driver);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
