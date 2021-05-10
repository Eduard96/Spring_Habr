package com.habr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Validator;
import java.util.Properties;

@Configuration
@ComponentScan("com.habr")
@EnableWebMvc
@EnableCaching
@PropertySource("classpath:persistence.properties")
@EnableJpaRepositories("com.habr.repository")
@EnableTransactionManagement
//@EnableWebFlux
public class HabrAppConfig implements WebMvcConfigurer  {


    @Autowired
    private Environment env;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("users");
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setUrl(env.getRequiredProperty("url"));
        driverManager.setDriverClassName(env.getRequiredProperty("driver"));
        driverManager.setUsername(env.getRequiredProperty("username"));
        driverManager.setPassword(env.getRequiredProperty("password"));
        return driverManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.habr.model");
        sessionFactory.setHibernateProperties(additionalProperties());
        return sessionFactory;
    }

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hbm2ddl"));
        properties.put("hibernate.dialect", env.getRequiredProperty("dialect"));
        properties.put("hibernate.format_sql", true);
        return properties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setPackagesToScan("com.habr.model");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor postProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    //    private final ApplicationContext applicationContext;
//
//    @Autowired
//    public HabrAppConfig(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    /**
     * applicationContext используем для конфигурации
     * в данном случае указываем путь к нашим представлениям
     * (то есть где находится код, который будет "показан" пользвателю)
     * Так же указываем рассширение данных представлений
     * @return
     */
//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setApplicationContext(applicationContext);
//        resolver.setPrefix("WEB-INF/view/");
//        resolver.setSuffix(".html");
//        return resolver;
//    }

    /**
     * Опять какая-то конфигурация для представлений,
     * но не углубился
     * @return
     */
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver());
//        engine.setEnableSpringELCompiler(true);
//        return engine;
//    }

    /**
     * Здесь мы указываем что будем использовать Thymeleaf
     * Данный метод переопределяем из интерфейса WebMvcConfigurer
     * @param //viewResolverRegistry
     */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        viewResolverRegistry.viewResolver(resolver);
//    }

    //    private static final String BASE_URL = "http://localhost:8082";
//    public static final int TIMEOUT = 50000;

//    @Bean
//    public HttpClient httpClientConfig() {
//        return HttpClient
//                .create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
//                .doOnConnected(connection -> {
//                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
//                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
//                });
//    }
//
//    @Bean
//    public WebClient webClientWithTimeout() {
//        return WebClient.builder()
//                .baseUrl(BASE_URL)
//                .clientConnector(new ReactorClientHttpConnector(httpClientConfig()))
//                .build();
//    }

//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder().build();
//    }
}
