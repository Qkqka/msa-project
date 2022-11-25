package com.msa.config.api;

import static java.util.concurrent.TimeUnit.SECONDS;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.msa.annotation.ReaderMapper;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fnfnksb@gmail.com
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "com.msa.mapper.reader", sqlSessionFactoryRef = "readerSqlSessionFactory", annotationClass = ReaderMapper.class)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ReaderDataSourceConfig {

    @Bean(name = "readerDataSource")
    @ConfigurationProperties(prefix = "application.datasource.reader")
    public DataSource readerDataSource() {
        HikariDataSource hikari = new HikariDataSource();
        hikari.setPoolName("HikariPool-READER"); // 로그에서 구분하기 위함 (테스트용도)
        hikari.setConnectionTimeout(SECONDS.toMillis(30)); 
        hikari.setMaximumPoolSize(10); 
        hikari.setMinimumIdle(10);
        return hikari;
    }

    @Bean("readerSqlSessionFactory")
    public SqlSessionFactory readerSqlSessionFactory(@Qualifier("readerDataSource") DataSource readerDataSource, ApplicationContext applcationconContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(readerDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.msa..model");
        sqlSessionFactoryBean.setMapperLocations(applcationconContext.getResources("classpath:mapper/reader/*.xml"));

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(false);
        configuration.setJdbcTypeForNull(JdbcType.VARCHAR);
        configuration.setDefaultStatementTimeout(30);

        return sqlSessionFactory;
    }

    @Bean("readerSqlSessionTemplate")
    public SqlSessionTemplate readerSqlSessionTemplate(@Qualifier("readerSqlSessionFactory") SqlSessionFactory readerSqlSessionFactory) {
        return new SqlSessionTemplate(readerSqlSessionFactory);
    }
}
