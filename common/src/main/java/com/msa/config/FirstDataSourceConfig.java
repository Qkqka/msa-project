package com.msa.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 커넥션 풀의 커넥션을 관리하기 위한 객체
 * 이 객체를 통해 커넥션을 획득 반납 등의 작업을 한다.
 * https://insanelysimple.tistory.com/317
 * 
 * @author fnfnksb@gmail.com
 */
@Configuration
@MapperScan("com.msa.mapper.write") // *Mapper.java 파일 경로
public class FirstDataSourceConfig {

    @Primary
    @Bean(name = "firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.firstDataSource.hikari")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

//    @Primary
//    @Bean("firstSqlSessionFactory")
//    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource firstDataSource, ApplicationContext applcationconContext) {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(firstDataSource);
//        sqlSessionFactoryBean.setMapperLocations(applcationconContext.getResources("classpath:mapper/writer/*.xml")); // xml 파일 경로
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager firstTransactionManager(@Qualifier("firstDataSource") DataSource firstDataSource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(firstDataSource);
//        return dataSourceTransactionManager;
//    }
}
