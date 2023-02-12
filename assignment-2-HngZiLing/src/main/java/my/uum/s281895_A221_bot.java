package my.uum;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * This class is for telegram bot function
 */
public class s281895_A221_bot extends TelegramLongPollingBot {

    /**
     * This method is to get the bot username
     * @return bot username
     */
    @Override
    public String getBotUsername() {
        return "s281895_A221_bot";
    }

    /**
     * This method is for get the bot token
     * @return bot token
     */
    @Override
    public String getBotToken() {
        return "5620680075:AAGWctoWYbTb62YriKfMf4Ox2BxoT_g1xis";
    }

    /**
     * This method is for interacting with the user, collect the info entered by the user and store it to database
     *
     * @param ic User IC number with 14 digit including '-'.
     * @param staffId User 6 digit identity number
     * @param fullName User full name
     * @param telNo User 10 or 11 digit telephone number
     * @param email User email address
     * @param purpose User purpose for booking meeeting room
     * @param bookingDate The date booked by the user
     * @param bookingTime The time booked by the user
     * @param stepBook The number of step for booking a meeting room
     * @param stepCancel The number of step for cancel meeting record
     * @param bookingId This is the bookingID that user want to cancel
     */
    static String ic, staffId, fullName, telNo, email, purpose, bookingDate, bookingTime;
    String bookingId;
    int stepBook = 0;
    int stepCancel = 0;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String reply = String.valueOf(message.getText());
        String text = message.getText();
        String chatId = message.getChatId().toString();

        if (text.equals("/start") || text.equals("/booking") || text.equals("/cancel") || text.equals("/list") || text.equals("0")) {
            SendMessage sendMessage = new SendMessage();
            switch (text) {
                case "/start": {
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Hello, Welcome to s281895_A221_Bot\n\n" +
                            "Click /booking if you want book a meeting room\n" +
                            "Click /cancel if you want to cancel a meeting room\n" +
                            "Click /list if you want to display the list of users");
                    break;
                }
                case "/cancel": {
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("May I know your staff id?\n\nReply 0: Back to Main Menu");
                    stepCancel = 1;
                    break;
                }
                case "/list": {
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(SQLite.list());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Click /booking if you want book a meeting room\n" +
                            "Click /cancel if you want to cancel a meeting room\n" +
                            "Click /list if you want to display the list of users");
                    break;
                }
                case "0": {
                    stepBook = 0;
                    stepCancel = 0;
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Hello, Welcome to s281895_A221_Bot\n\n" +
                            "Click /booking if you want book a meeting room\n" +
                            "Click /cancel if you want to cancel a meeting room\n" +
                            "Click /list if you want to display the list of users");
                    break;
                }
                case "/booking": {
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("First at all, may I know your ic number?\n\nReply 0: Back to Main Menu");
                    stepBook = 1;
                    break;
                }
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (message.hasText()) {
            SendMessage sendMessage = new SendMessage();
            switch (stepBook) {
                case 1: {
                    sendMessage.setChatId(chatId);
                    if (reply.matches(".*[a-zA-Z]+.*")) {
                        sendMessage.setText("Ic number should not contain alphabet, please enter again\n\nReply 0: Back to Main Menu");
                    } else {
                        if (reply.length() == 14) {
                            ic = reply;
                            stepBook = 2;
                            sendMessage.setText("May I know your staff id?\n\nReply 0: Back to Main Menu");
                        } else {
                            sendMessage.setText("Failed, please enter 14 digit number including '-'\n\nReply 0: Back to Main Menu");
                        }
                    }
                    break;
                }
                case 2: {
                    staffId = reply;
                    stepBook = 3;
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("May I have your full name please?\n\nReply 0: Back to Main Menu");
                    break;
                }
                case 3: {
                    fullName = reply;
                    stepBook = 4;
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("How about your telephone number?\n\nReply 0: Back to Main Menu");
                    break;
                }
                case 4: {
                    sendMessage.setChatId(chatId);
                    if (reply.length() < 10 || reply.length() > 11)
                        sendMessage.setText("Please enter 10 or 11 digit phone number\n\nReply 0: Back to Main Menu");
                    else {
                        telNo = reply;
                        stepBook = 5;
                        sendMessage.setText("Kindly provide your email address?\n\nReply 0: Back to Main Menu");
                    }
                    break;
                }
                case 5: {
                    email = reply;
                    stepBook = 6;
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("May I know the purpose of you booking?\n\n" +
                            "Reply 1: Group Discussion\n" +
                            "Reply 2: Hold Meeting\n" +
                            "Reply 3: Conduct lecture\n" +
                            "Reply 4: Take a quiz\n" +
                            "Reply 5: Use equipment\n\n" +
                            "Reply 0: Back to Main Menu");
                    break;
                }
                case 6: {
                    sendMessage.setChatId(chatId);
                    if (reply.equals("1") || reply.equals("2") || reply.equals("3") || reply.equals("4") || reply.equals("5")) {
                        switch (reply) {
                            case "1":
                                purpose = "Group Discussion";
                                break;
                            case "2":
                                purpose = "Hold Meeting";
                                break;
                            case "3":
                                purpose = "Conduct lecture";
                                break;
                            case "4":
                                purpose = "Take a quiz";
                                break;
                            case "5":
                                purpose = "Use equipment";
                                break;
                        }
                        stepBook = 7;
                        sendMessage.setText("Please provide a preferred booking date for meeting room? \n(eg: 25.12.2022)\n\n" +
                                "Reply 0: Back to Main Menu");
                    } else {
                        stepBook = 6;
                        sendMessage.setText("Please enter 1-5 only\n\nReply 0: Back to Main Menu");
                    }
                    break;
                }
                case 7: {
                    sendMessage.setChatId(chatId);
                    bookingDate = reply;
                    stepBook = 8;
                    sendMessage.setText("Please select a preferred time slot of booking meeting room?\n\n" +
                            "Reply 1: 8:30am-10:30am\n" +
                            "Reply 2: 10:30am-12:30pm\n" +
                            "Reply 3: 12:30pm-2:30pm\n" +
                            "Reply 4: 2:30pm-4:30pm\n" +
                            "Reply 5: 4:30pm-6:30pm\n\n" +
                            "Reply 0: Back to Main Menu");
                    break;
                }
                case 8: {
                    String answer = reply;
                    sendMessage.setChatId(chatId);
                    if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4") || answer.equals("5")) {
                        switch (answer) {
                            case "1":
                                bookingTime = "8:30am-10:30am";
                                break;
                            case "2":
                                bookingTime = "10:30am-12:30pm";
                                break;
                            case "3":
                                bookingTime = "12:30pm-2:30pm";
                                break;
                            case "4":
                                bookingTime = "2:30pm-4:30pm";
                                break;
                            case "5":
                                bookingTime = "4:30pm-6:30pm";
                                break;
                        }
                        stepBook = 9;
                        sendMessage.setText("This is your booking detail\n" +
                                "\nIC no: " + ic + "\nStaff ID: " + staffId + "\nName: " + fullName +
                                "\nTel no: " + telNo + "\nEmail: " + email + "\nPurpose: " + purpose + "\nDate: " + bookingDate + "\nTime: " + bookingTime +
                                "\n\nAre these correct?\n" +
                                "Reply 1: Yes\nReply 2: No, I would like to make a correction (Remind: You need to start from ic no and enter again from the beginning)\n\nReply 0: Back to Main Menu");


                    } else {
                        stepBook = 8;
                        sendMessage.setText("Please enter 1-5 only\n\nReply 0: Back to Main Menu");
                    }
                    break;
                }
                case 9: {
                    if (reply.equals("1")) {
                        SQLite.insertList(staffId, bookingDate, bookingTime);
                        SQLite.insert(ic, staffId, fullName, telNo, email, purpose, bookingDate, bookingTime);
                        sendMessage.setChatId(chatId);
                        sendMessage.setText("Booking successfully");
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        stepBook = 0;
                        sendMessage.setChatId(chatId);
                        sendMessage.setText("Click /booking if you want book a meeting room\n" +
                                "Click /cancel if you want to cancel a meeting room\n" +
                                "Click /list if you want to display the list of users");
                    } else if (reply.equals("2")) {
                        stepBook = 1;
                        sendMessage.setChatId(chatId);
                        sendMessage.setText("First at all, may I know your ic number?\n\nReply 0: Back to Main Menu");
                    }
                    break;
                }
            }

            switch (stepCancel) {
                case 1: {
                    staffId = reply;
                    sendMessage.setChatId(chatId);
                    if (SQLite.select(staffId).equals("")) {
                        sendMessage.setText("Sorry, I can't find the booked meeting room with this staff id, please enter again\n\nReply 0: Back to Main Menu");
                    } else {
                        stepCancel = 2;
                        sendMessage.setText("I found your booking detail\n" + SQLite.select(reply) +
                                "\nChoose one booking id for the record you want to cancel\nReply booking ID  (eg: 1)\n\nReply 0: Back to Main Menu");
                    }
                    break;
                }

                case 2: {
                    bookingId = reply;
                    sendMessage.setChatId(chatId);
                    if (SQLite.selectRecord(bookingId).equals("")) {
                        sendMessage.setText("Sorry, I can't find the booked meeting room with this booking id, please enter again\n\nReply 0: Back to Main Menu");
                    } else {
                        stepCancel = 3;
                        sendMessage.setText(SQLite.selectRecord(bookingId) +
                                "\nAre you sure want to cancel this booking?\n" +
                                "Reply 1: Yes\nReply 2: No\n\nReply 0: Back to Main Menu");
                    }
                    break;
                }
                case 3: {
                    sendMessage.setChatId(chatId);
                    if (reply.equals("1")) {
                        SQLite.delete(bookingId);
                        sendMessage.setText("Booking record was deleted successfully!");
                    } else if (reply.equals("2")) {
                        stepCancel = 0;
                        sendMessage.setText("Stop canceling meeting room booking records");
                    }
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Click /booking if you want book a meeting room\n" +
                            "Click /cancel if you want to cancel a meeting room\n" +
                            "Click /list if you want to display the list of users");
                    break;
                }
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}