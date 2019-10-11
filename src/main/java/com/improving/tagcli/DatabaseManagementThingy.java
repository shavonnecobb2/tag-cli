package com.improving.tagcli;

import com.improving.tagcli.database.EmoteDAO;
import com.improving.tagcli.database.ItemDAO;
import com.improving.tagcli.models.Emote;
import com.improving.tagcli.models.Item;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DatabaseManagementThingy {
    private Scanner scanner = new Scanner(System.in);
    private final EmoteDAO emoteDAO;
    private final ItemDAO itemDAO;
    private Emote emote;
    private Item item;

    public DatabaseManagementThingy(EmoteDAO emoteDAO, ItemDAO itemDAO) {
        this.emoteDAO = emoteDAO;
        this.itemDAO = itemDAO;
    }

    public void execute() {
        String input;
        boolean loop = true;
        while (loop) {
            System.out.println("Do you want to (1) Add Something or (2) Read Something?");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Do you want to add an (1) Emote or (2) Item?");
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    emote = new Emote();
                    System.out.println("What would you like for your prompt to be?");
                    input = scanner.nextLine();
                    emote.setPrompt(input);
                    System.out.println("What would you like for your text to be?");
                    input = scanner.nextLine();
                    emote.setText(input);
                    emoteDAO.insertEmote(emote);
                    System.out.println("SUCCESS!");
                } else if (choice == 2) {
                    item = new Item();
                    System.out.println("What would you like for the name to be?");
                    input = scanner.nextLine();
                    item.setName(input);
                    itemDAO.insertItem(item);
                    System.out.println("SUCCESS!");
                }
            } else if (choice == 2) {
                System.out.println("Do you want to read (1) An Emote or (2) An Item?");
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    System.out.println("What emote would you like to select?");
                    input = scanner.nextLine();
                    emoteDAO.findByName(input);
                } else {
                    if (choice == 2) {
                        System.out.println("What item would you like to select?");
                        input = scanner.nextLine();
                        itemDAO.findByName(input);
                    }
                }
            }
        }
    }
}

