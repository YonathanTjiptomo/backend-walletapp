package com.labkoding.product.ewallet.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "ewalletEntityManagerFactory",
        transactionManagerRef = "targetTransactionManagerEwallet",
        basePackages = {"com.labkoding.product.ewallet.data.ewallet.repo"}
)
public class DbConfig {
    @Primary
    @Bean(name = "ewalletdbDataSource")
    @ConfigurationProperties(prefix = "spring.ewalletdb")
    public DataSource ewalletdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "ewalletEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ewalletEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("ewalletdbDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("com.labkoding.product.ewallet.data.ewallet.model")
                .persistenceUnit("ewalletpersistence")
                .build();

        return em;

//        return builder
//                .dataSource(dataSource)
//                .packages("com.labkoding.product.ewallet.data.ewallet.model")
//                .persistenceUnit("plink")
//                .build();
    }

    @Primary
    @Bean(name = "targetTransactionManagerEwallet")
    public PlatformTransactionManager targetTransactionManagerEwallet(
            @Qualifier("ewalletEntityManagerFactory") EntityManagerFactory ewalletEntityManagerFactory) {
        return new JpaTransactionManager(ewalletEntityManagerFactory);
    }
}

