package org.example;

import java.util.Date;

class Note implements Comparable<Note> {
    String title;
    Date date;
    String content;

    public Note(String title, Date date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;

    }

    @Override
    public int compareTo(Note otherNote) {
        // Implement comparison logic here based on your requirements
        // For example, compare notes based on their dates
        return this.date.compareTo(otherNote.date);
    }
}
