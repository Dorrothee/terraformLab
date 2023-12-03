package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityQueueNote {
    public static void main(String[] args) throws IOException {
        NoteFunctions funcObj = new NoteFunctions();
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Note> priorityQueue = new PriorityQueue<>(Comparator.comparing(note -> note.date));
        boolean done = true;
        do{
            System.out.println("\n----------------------------------------------");
            System.out.println("1 - create and add a note");
            System.out.println("2 - get the note with the highest priority");
            System.out.println("3 - get the note with the highest priority and then remove it");
            System.out.println("4 - clear the queue");
            System.out.println("5 - get all notes and clear the queue");
            System.out.println("0 - stop program and exit");
            System.out.println("\n----------------------------------------------");
            System.out.print("Choose the command to execute: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Enter the title for your note: ");
                    String titleInput = reader.readLine();

                    System.out.print("Enter the date when your note was created using format yyyy-mm-dd: ");
                    String dateInput = reader.readLine();

                    System.out.print("Enter the content for your note: ");
                    String contentInput = reader.readLine();

                    funcObj.addNote(titleInput, dateInput, contentInput, priorityQueue);
                    break;
                case 2:
                    funcObj.peekHighestPriority(priorityQueue);
                    break;
                case 3:
                    funcObj.pollHighestPriority(priorityQueue);
                    break;
                case 4:
                    funcObj.clearQueue(priorityQueue);
                    break;
                case 5:
                    funcObj.pollQueue(priorityQueue);
                    break;
                case 0:
                    done = false;
                    scanner.close();

            }
        } while(done);
    }
}

