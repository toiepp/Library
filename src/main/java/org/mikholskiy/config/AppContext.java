package org.mikholskiy.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan("org.mikholskiy")
@PropertySource("classpath:database.yaml")
@EnableTransactionManagement
public class AppContext {
	private final Environment environment;

	public AppContext(Environment environment) {
		this.environment = environment;
	}

	/* INFO Указываю настройки подключения к базе данных через Hibernate */
	private Properties hibernateProperties() {
		var properties = new Properties();
		properties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.current_session_context_class", "thread");
		return properties;
	}

	/* Бин фабрики сессий */
	@Bean
	@Scope(scopeName = "singleton")
	public LocalSessionFactoryBean sessionFactoryBean() {
		var sessionFactoryBean = new LocalSessionFactoryBean();
		/* Указываю данные для подключения к базе данных
		 * Замена SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")*/
		sessionFactoryBean.setDataSource(dataSource());
		/* Указываю пакет, в котором хранятся модели
		 * Земеняет ...().addAnnotatedClass(...) */
		sessionFactoryBean.setPackagesToScan("org.mikholskiy.domains");
		/* Указываю доп. свойства подключения и настроки Hibernate */
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		return sessionFactoryBean;
	}

	/* Указываю данные для подключения к базе данных
	 * и возвращаю соединение с базой данных */
	@Bean
	public DataSource dataSource() {
		var dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		return dataSource;
	}
}

