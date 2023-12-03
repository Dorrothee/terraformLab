package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;

public class NoteFunctions {

    public void addNote(String titleInput, String dateInput, String contentInput, PriorityQueue<Note> priorityQueue){
        try{
            Date dateFormatted = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);
            Note note = new Note(titleInput, dateFormatted, contentInput);
            priorityQueue.add(note);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void peekHighestPriority(PriorityQueue<Note> priorityQueue){
        if (!priorityQueue.isEmpty()){
            System.out.println("Note with the highest priority is: ");
            Note noteHP = priorityQueue.peek();
            System.out.println(noteHP.title + " (was created on " + noteHP.date + ")");
        }
        else{
            System.out.print("The queue is empty. Nothing to show\n\n");
        }
    }

    public Note pollHighestPriority(PriorityQueue<Note> priorityQueue){
        if (!priorityQueue.isEmpty()){
            System.out.println("Note with the highest priority is: ");
            Note noteHP = priorityQueue.poll();
            System.out.println(noteHP.title + " (was created on " + noteHP.date + ")");
        }
        else{
            System.out.print("The queue is empty. Nothing to show\n\n");
        }
        return null;
    }

    public void clearQueue(PriorityQueue priorityQueue){
        if (!priorityQueue.isEmpty()){
            System.out.println("Size of the queue before clearing: " + priorityQueue.size());
            priorityQueue.clear();
            System.out.println("Size of the queue after clearing: " + priorityQueue.size());
        }
        else{
            System.out.print("The queue is empty. Nothing to clear\n\n");
        }
    }

    public void pollQueue(PriorityQueue<Note> priorityQueue){
        if (!priorityQueue.isEmpty()){
            System.out.println("Our notes from the queue: ");
            while (!priorityQueue.isEmpty()){
                Note note = priorityQueue.poll();
                System.out.println("Note \"" + note.title + "\", Date: " + note.date + ", Content: " + note.content);
            }
        }
        else{
            System.out.print("The queue is empty. Nothing to show\n\n");
        }
    }
}
