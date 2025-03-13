package com.simanglam.model;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Message {
    private String content;
    private Character author;
    
    public Message(String content, Character author){
        this.content = content;
        this.author = author;
    }

    public void export(OutputStreamWriter os) throws IOException {
        if (author.getVisible() == false) return;
        if (author.getNarrator()) os.write(String.format("\t\"%s\"\n", author.getId(), content.replace("\n", "\\n")));
        else os.write(String.format("\tc%d \"%s\"\n", author.getId(), content.replace("\n", "\\n")));
    }

    public Character getAuthor(){
        return author;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }
}
