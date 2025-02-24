package com.simanglam.model;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ActDeserializer.class)
public class Act {
    private ArrayList<Message> dialogs;
    private String name;
    private int id;

    public Act(String name){
        dialogs = new ArrayList<>();
        this.name = name;
    }

    public ArrayList<Message> getrDialogs(){
        return dialogs;
    }

    public void addDialog(Message message){
        this.dialogs.add(message);
    }

    public void addDialog(Message ...messages){
        dialogs.addAll(Arrays.asList(messages));
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void export(OutputStreamWriter os) throws IOException {
        os.write(String.format("label act%d:\n", this.id));
        for (Message message : dialogs){
            message.export(os);
        }
    }
}
