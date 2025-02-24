package com.simanglam.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simanglam.App;
import com.simanglam.component.CharacterBox;
import com.simanglam.model.Act;
import com.simanglam.model.Character;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML VBox characterListPane;
    @FXML VBox scenePane;
    @FXML VBox sceneDetailPane;

    @FXML
    public void loadFile(){

        Stage stage = App.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        File file = fileChooser.showOpenDialog(stage);
        
        if (file == null) return;

        Act act;
        try {
            act = new ObjectMapper().readValue(file, Act.class);
        }
        catch (IOException e){
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("Error reading file");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
            return;
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }

        act.setId(acts.size());
        acts.add(act);
    

        characters.sort((a, b) -> a.getName().compareTo(b.getName()));

        characterListPane.getChildren().clear();
        characters.forEach(character -> {
            characterListPane.getChildren().add(new CharacterBox(character));
        });
    }

    @FXML
    public void exportFile(){
        System.out.println("Exporting file");
        try(OutputStreamWriter os = new OutputStreamWriter(System.out)) {
            for(Character character : characters){
                character.export(os);
            }
            for (Act act : acts){
                act.export(os);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
