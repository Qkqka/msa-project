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
 * @author fnfnksb@gmail.com
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.msa.mapper.writer", sqlSessionFactoryRef = "writerSqlSessionFactory"/*, annotationClass = WriterMapper.class*/)
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
    @ConfigurationProperties(prefix = "spring.datasource.hikari") // application.yml 참고
    public DataSource writerDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * SqlSessionFactory : 내부적으로 SQLSession을 만들어 내는 존재, 
     *                     개발에서는 SQLSession을 통해서 Connection을 생성하거나 원하는 SQL을 전달하고 결과는 리턴받는 구조로 작성하게 됨.
     *                     Mybatis설정파일을 바탕으로 SqlSessioNFactory를 생성한다. spring bean으로 등록해야 한다.
     * SQLSession : RDB에 인증을 거친 논리적인 연결 상태, 
     *              Mybatis를 이용해 DAO를 구현하려면 SQLSession 객체가 필요 (실제로 구현할 필요는 없다. spring 컨테이너에서 생성하도록 설정하면 자동 구현)
     *              SQL실행이나 트랜잭션 관리를 실행한다.
     *              Tread-safe 하지 않으므로 thread마다 필요에 따라 생성한다.
     * 
     * @param writerDataSource
     * @param applcationconContext
     * @return
     * @throws Exception
     */
    @Primary
    @Bean("writerSqlSessionFactory")
    public SqlSessionFactory writerSqlSessionFactory(@Qualifier("writerDataSource") DataSource writerDataSource, ApplicationContext applcationconContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(writerDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.msa.mapper.entity"); // mapper에서 사용할 도메인 패키지
        sqlSessionFactoryBean.setMapperLocations(applcationconContext.getResources("classpath:*mapper/writer/*Mapper.xml")); // xml 파일 경로

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true); // camel case 자동 매핑
        configuration.setUseGeneratedKeys(true); // insert 시 pk를 bean으로 반환

        return sqlSessionFactory;
    }

    /**
     * SqlSessionTemplate : SQL실행이나 트랜잭션 관리를 실행한다. 
     *                      SQLSession 인터페이스를 구현하며, Tread-safe하다. spring bean으로 등록해야 한다.
     * 
     * @param writerSqlSessionFactory
     * @return
     */
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
        return txManager;
    }
}
