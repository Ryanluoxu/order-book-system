package io.ryanluoxu.orderbook.common.config;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value={"classpath:persistence.hibernate.properties"})
@EnableTransactionManagement
public class HibernateConfig {

	@Autowired
	protected Environment environment;

	@Value("#{'io.ryanluoxu.orderbook.bean'.split(',')}")
	protected List<String> packageList;

	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		final LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setHibernateProperties(hiberateProperties());
		sessionFactoryBean.setPackagesToScan(packageList.toArray(new String[0]));
		sessionFactoryBean.setDataSource(dataSource());
		return sessionFactoryBean;
	}

	@Bean
    public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver.class"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		dataSource.setMaxIdle(1); // Reduce chance that the connections become full
		return dataSource;
    }

	private Properties hiberateProperties() {
		return new Properties() {
			private static final long serialVersionUID = -7856812307150575089L;
			{
				setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
				setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));
				setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
			}
		};
	}
}
