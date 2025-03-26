package org.example.bot.Logic;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    public final String CONFIGURATION_BOT_FILE = "src/config/config.properties";

    private String botName;
    private String botToken;

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public Config() {
        load();
    }

    public String getBotName() { return botName; }
    public String getBotToken() { return botToken; }

    public String getDbUrl() { return dbUrl; }
    public String getDbUser() { return dbUser; }
    public String getDbPassword() { return dbPassword; }

    public void load() {
        Properties botSettings = new Properties();
        try (FileInputStream is = new FileInputStream(CONFIGURATION_BOT_FILE)){
            botSettings.load(is);
            System.out.println("Config loaded successfully");
        } catch (Exception e){
            System.out.println("Failed to load config");
        }
        botName = botSettings.getProperty("BotName", " ");
        botToken = botSettings.getProperty("BotToken", " ");
        dbUrl = botSettings.getProperty("DB_URL", "");
        dbUser = botSettings.getProperty("DB_USER", "");
        dbPassword = botSettings.getProperty("DB_PASSWORD", "");
    }
}
