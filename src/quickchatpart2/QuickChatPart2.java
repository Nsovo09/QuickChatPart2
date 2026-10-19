package quickchatpart2;

import java.util.Scanner;

public class QuickChatPart2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to QuickChat.");
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();
        System.out.println("Welcome " + user);

        System.out.print("How many messages do you want to send? ");
        int numMessages = Integer.parseInt(scanner.nextLine());
        int sent = 0;
        
        while (sent < numMessages) {
            System.out.print("Enter recipient (+277...): ");
            String recipient = scanner.nextLine();
            System.out.print("Enter message (max 250): ");
            String content = scanner.nextLine();
            
            if (content.length() > 250) {
                System.out.println("Too long by " + (content.length()-250));
                continue;
            }
            
            Message msg = new Message();
            msg.setMessageID(msg.generateMessageID());
            msg.setNumSent(sent+1);
            msg.setRecipient(recipient);
            msg.setMessageContent(content);
            
            String check = msg.checkRecipientCell();
            if (!check.equals("Cell phone number successfully captured.")) {
                System.out.println(check);
                continue;
            }
            
            msg.createMessageHash();
            System.out.println("\nID: " + msg.getMessageID());
            System.out.println("Hash: " + msg.getMessageHash());
            System.out.println("To: " + recipient);
            System.out.println("Msg: " + content);
            System.out.print("Choose: 1=Send, 2=Disregard, 3=Store: ");
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1) {
                System.out.println(msg.SentMessage(1));
                Message.sentMessages.add(msg);
                Message.totalMessagesSent++;
                sent++;
            } else if (choice == 2) {
                System.out.println(msg.SentMessage(2));
                sent++;
            } else {
                System.out.println(msg.SentMessage(3));
                msg.storeMessage();
                sent++;
            }
        }
        System.out.println("Total sent: " + Message.totalMessagesSent);
        System.out.println("Done!");
    }
}