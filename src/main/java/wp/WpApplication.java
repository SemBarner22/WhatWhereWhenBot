package wp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import wp.appconfig.BotConfig;

//@Configuration("wp.config")
//@ComponentScan("wp.domain")
//@EnableJpaRepositories(basePackages="wp.repository", entityManagerFactoryRef = "emf")
//@EnableJpaRepositories(basePackages="wp.repository")
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class})
//@EnableAutoConfiguration
//(transactionManagerRef = "dbTransactionManager")
@EnableJpaRepositories
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class WpApplication implements WebMvcConfigurer {
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
//
//    }
    public static void main(String[] args) {
        ApiContextInitializer.init();
        System.out.println("ffffffffffffffffffffffff");
//

//        createBot()
//        TelegramBotsApi botsApi = new TelegramBotsApi();
//        try {
//            botsApi.registerBot(bot);
//        } catch (TelegramApiRequestException e) {
//            Logger.log.error("Can't register bot", e);
//        }
//        System.getProperties().put("proxySet", true);
//        System.getProperties().put("socksProxyHost", "localhost");
//        System.getProperties().put("socksProxyPort", 8080);

        SpringApplication.run(WpApplication.class, args);
    }
}