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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 커넥션 풀의 커넥션을 관리하기 위한 객체
 * 이 객체를 통해 커넥션을 획득 반납 등의 작업을 한다.
 * https://insanelysimple.tistory.com/317
 *
 * @MapperScan : *Mapper.java 패키지 경로, 멀티DB사용시 mapper클래스 파일 스켄용 basePackages를 DB별로 따로 설정
 * @EnableTransactionManagement : annotation 기반 트랜잭션 관리 사용, 필요한가..?
 * 
 * @author crewmate
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.msa.mapper.write", sqlSessionFactoryRef = "writerSqlSessionFactory"/*, annotationClass = WriterMapper.class*/)
public class WriterDataSourceConfig {

    /**
     * 
     * @ConfigurationProperties : 해당 Bean이 생성 되면서 해당 프로퍼티에 대한 값을 가져와 사용한다.
     *  ex) @ConfigurationProperties(prefix = "spring.datasource.firstDataSource.hikari")
     * 
     * @return
     */
    @Primary
    @Bean(name = "writerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource") // application.yml 참고
    public DataSource writerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("writerSqlSessionFactory")
    public SqlSessionFactory writerSqlSessionFactory(@Qualifier("writerDataSource") DataSource writerDataSource, ApplicationContext applcationconContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(writerDataSource);
        //sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.msa.entity"); // mapper에서 사용할 도메인 패키지
        sqlSessionFactoryBean.setMapperLocations(applcationconContext.getResources("classpath:mapper/writer/*Mapper.xml")); // xml 파일 경로

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true); // camel case 자동 매핑
        configuration.setUseGeneratedKeys(true); // insert 시 pk를 bean으로 반환

        return sqlSessionFactory;
    }

    @Primary
    @Bean("writerSqlSessionTemplate")
    public SqlSessionTemplate writerSqlSessionTemplate(@Qualifier("writerSqlSessionFactory") SqlSessionFactory writerSqlSessionFactory) {
        return new SqlSessionTemplate(writerSqlSessionFactory);
    }

    /**
     * spring의 트랜잭션 관리 클래스
     * 
     * @param writeDataSource
     * @return
     */
    @Primary
    @Bean("writerTransactionManager")
    public TransactionManager writeTransactionManager(@Qualifier("writerDataSource") DataSource writerDataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(writerDataSource);
        txManager.setGlobalRollbackOnParticipationFailure(false); // default true, 기존 트랜잭션 실패시 전역 롤백 여부
        return txManager;
    }
}
