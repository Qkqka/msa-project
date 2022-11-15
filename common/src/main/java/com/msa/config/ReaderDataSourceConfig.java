package com.msa.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 커넥션 풀의 커넥션을 관리하기 위한 객체
 * 이 객체를 통해 커넥션을 획득 반납 등의 작업을 한다.
 * https://insanelysimple.tistory.com/317
 * 
 * @author fnfnksb@gmail.com
 */
@Configuration
@MapperScan(basePackages = "com.msa.mapper.reader", sqlSessionFactoryRef = "readerSqlSessionFactory") // *Mapper.java 파일 경로
public class ReaderDataSourceConfig {

    @Bean(name = "readerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource readerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("readerSqlSessionFactory")
    public SqlSessionFactory readerSqlSessionFactory(@Qualifier("readerDataSource") DataSource readerDataSource, ApplicationContext applcationconContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(readerDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.msa.mapper.entity"); // mapper에서 사용할 도메인 패키지
        sqlSessionFactoryBean.setMapperLocations(applcationconContext.getResources("classpath:mapper/reader/*.xml")); // xml 파일 경로

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true); // camel case 자동 매핑

        return sqlSessionFactory;
    }

    @Bean("readerSqlSessionTemplate")
    public SqlSessionTemplate readerSqlSessionTemplate(@Qualifier("readerSqlSessionFactory") SqlSessionFactory readerSqlSessionFactory) {
        return new SqlSessionTemplate(readerSqlSessionFactory);
    }
}
