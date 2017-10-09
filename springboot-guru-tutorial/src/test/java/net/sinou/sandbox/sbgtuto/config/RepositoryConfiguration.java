package net.sinou.sandbox.sbgtuto.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "net.sinou.sandbox.sbgtuto.domain" })
@EnableJpaRepositories(basePackages = { "net.sinou.sandbox.sbgtuto.repositories" })
@EnableTransactionManagement
public class RepositoryConfiguration {
}