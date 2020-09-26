package wp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.ApiContextInitializer;

@Configuration("wp.config")
@ComponentScan("wp.domain")
@EnableJpaRepositories(basePackages="wp.repository")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class WpApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        ApiContextInitializer.init();
//
//        Bot bot = new Bot();
//
//        TelegramBotsApi botsApi = new TelegramBotsApi();
//        try {
//            botsApi.registerBot(bot);
//        } catch (TelegramApiRequestException e) {
////            Logger.log.error("Can't register bot", e);
//        }
//        System.getProperties().put("proxySet", true);
//        System.getProperties().put("socksProxyHost", "localhost");
//        System.getProperties().put("socksProxyPort", 8080);

        SpringApplication.run(WpApplication.class, args);
    }
}