package my.uum;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * This class is for running the telegram bot
 */
public class App extends SQLite {
    /**
     * This method is for running the telegram bot
     * @param args This is main method
     */
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new s281895_A221_bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        SQLite.connect();
    }
}
