package bibliotech_bot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.telegram.telegrambots.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.exceptions.*;

public class Main {

	public static void main(String[] args) throws ParseException {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
       
        try {
            api.registerBot(new BiblioTech());
        } catch (TelegramApiRequestException e) {
        	e.printStackTrace();
        }
    }

}
