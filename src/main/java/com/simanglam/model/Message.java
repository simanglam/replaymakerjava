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
        os.write(String.format("%s \"%s\"\n", author.getName(), content));
    }

}
