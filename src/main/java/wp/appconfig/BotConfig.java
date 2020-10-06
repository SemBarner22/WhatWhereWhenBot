package wp.appconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import wp.Bot;
import wp.BotStateContext;
import wp.Service.LocaleMessageService;
import wp.Service.ReplyMessagesService;
import wp.Service.TeamService;
import wp.TelegramFacade;
import wp.cache.UserDataCache;
import wp.handlers.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BotConfig {

    @Autowired
    private TeamService teamService;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }

    @Value("${bot.name}")
    private String name;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.path}")
    private String path;

    @Bean
    public Bot createBot() {
//        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        LocaleMessageService localeMessageService = new LocaleMessageService("ru-RU", messageSource());
        ReplyMessagesService replyMessagesService = new ReplyMessagesService(localeMessageService);
        UserDataCache userDataCache = new UserDataCache();
        List<InputMessageHandler> list = new ArrayList<>();
        list.add(new AskTeamHandler(userDataCache, replyMessagesService));
        list.add(new StartHandler(userDataCache, replyMessagesService));
        list.add(new DefaultHandler(userDataCache, replyMessagesService));
        list.add(new CreateJoinHandler(userDataCache, replyMessagesService));
        BotStateContext botStateContext = new BotStateContext(list);
        TelegramFacade facade = new TelegramFacade(botStateContext, userDataCache, replyMessagesService, teamService);

        Bot bot = new Bot(facade);

        bot.setBotName(name);
        bot.setBotToken(token);
        bot.setBotPath(path);

        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
//            log.error("Can't register bot", e);
        }

//        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
//        options.setProxyHost("localhost");
//        options.setProxyPort(8080);

//        Bot bot = new Bot(options);



        return bot;
    }


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
