package com.faisaljarkass.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.faisaljarkass.demo.repositories",
        entityManagerFactoryRef = "restEntityManager",
        transactionManagerRef = "restTransactionManager"
)
public class RestDatasourceConfig {

    @Autowired
    Environment environment;

    @Bean(name = "restEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean restEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(plandkDataSource());
        em.setPackagesToScan("com.faisaljarkass.demo.domains");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("spring.jpa.database-platform", environment.getProperty("spring.jpa.database-platform"));
        properties.put("spring.jpa.show-sql", environment.getProperty("spring.jpa.show-sql"));
        properties.put("spring.jpa.properties.hibernate.format_sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));

        properties.put("spring.jpa.hibernate.ddl-auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));

        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.database-platform"));

        properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        properties.put("spring.jpa.properties.hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));
        properties.put("hibernate.format_sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "restDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.rest")
    public DataSource plandkDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "restTransactionManager")
    @Primary
    public PlatformTransactionManager plandkTransactionManager(@Qualifier("restEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
