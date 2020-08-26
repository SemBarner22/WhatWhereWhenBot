package wp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Configuration
@EnableAutoConfiguration
@ComponentScan
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