package quickchatpart2;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private String messageID;
    private int numSent;
    private String recipient;
    private String messageContent;
    private String messageHash;
    public static List<Message> sentMessages = new ArrayList<>();
    public static int totalMessagesSent = 0;

    public String generateMessageID() {
        long n = 1000000000L + (long)(Math.random() * 9000000000L);
        return String.valueOf(n);
    }
    public boolean checkMessageID() { return messageID.length() <= 10; }
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    public String createMessageHash() {
        String[] w = messageContent.trim().split("\\s+");
        String first = w[0].replaceAll("[^a-zA-Z]", "");
        String last = w[w.length-1].replaceAll("[^a-zA-Z]", "");
        this.messageHash = messageID.substring(0,2) + ":" + numSent + ":" + (first+last).toUpperCase();
        return this.messageHash;
    }
    public String SentMessage(int o) {
        if (o == 1) return "Message successfully sent.";
        if (o == 2) return "Press 0 to delete the message.";
        return "Message successfully stored.";
    }
    public int returnTotalMessages() { return totalMessagesSent; }
    public void storeMessage() {
        try {
            FileWriter fw = new FileWriter("storedMessages.json", true);
            fw.write("{\"ID\":\"" + messageID + "\",\"Hash\":\"" + messageHash + "\",\"To\":\"" + recipient + "\",\"Msg\":\"" + messageContent + "\"}\n");
            fw.close();
        } catch(Exception e){}
    }
    public void setMessageID(String id){ messageID=id; }
    public void setRecipient(String r){ recipient=r; }
    public void setMessageContent(String c){ messageContent=c; }
    public void setNumSent(int n){ numSent=n; }
    public String getMessageID(){ return messageID; }
    public String getMessageHash(){ return messageHash; }
    public String getRecipient(){ return recipient; }
    public String getMessageContent(){ return messageContent; }
}