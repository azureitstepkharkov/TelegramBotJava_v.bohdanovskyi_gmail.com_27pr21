
package code;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
/**
 *
 * @author asp
 */
public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "@Pochtalion_pechkin_bot";
    }

    @Override
    public String getBotToken() {
        return "519380768:AAENIbU5KHDRL2KO-unDsNH4VLG4c3l9SQo";
    }
    
    public static LongPollingBot getBot() {
        return new Bot();
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String messageSend = new SimpleBot().sayInReturn(message_text, true);
            
            if (true) 
            {
                SendMessage message = new SendMessage().setChatId(chat_id).setText(messageSend);
                setButtons(message);
                try {
                    sendMessage(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }  
              //
            }
            else
            {
                String message = update.getMessage().getText();
	        sendMsg(update.getMessage().getChatId().toString(), message);
            }
        }
    }
    
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            //log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    public synchronized void setButtons(SendMessage sendMessage) {
            // Создаем клавиуатуру
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);
            // Создаем список строк клавиатуры
            // Первая строчка клавиатуры
            List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            // Добавляем кнопки в первую строчку клавиатуры
            keyboardFirstRow.add(new KeyboardButton("Привет"));
            // Вторая строчка клавиатуры
//            KeyboardRow keyboardSecondRow = new KeyboardRow();
//            // Добавляем кнопки во вторую строчку клавиатуры
//            keyboardSecondRow.add(new KeyboardButton("Помощь"));
            // Добавляем все строчки клавиатуры в список
            keyboard.add(keyboardFirstRow);
            //keyboard.add(keyboardSecondRow);
            // и устанваливаем этот список нашей клавиатуре
            replyKeyboardMarkup.setKeyboard(keyboard);
     
            
    }


        private void setCallbackData(int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
