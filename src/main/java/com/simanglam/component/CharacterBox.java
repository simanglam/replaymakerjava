package com.simanglam.component;

import java.io.File;

import com.simanglam.App;
import com.simanglam.model.Character;
import com.simanglam.util.Const;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


public class CharacterBox extends HBox{

    public CharacterBox(Character character){
        super();
        try {
            VBox avatarBox = new VBox();
            character.setAvatar(new Image(App.class.getResource("image.png").toString()));
            ImageView image = new ImageView(character.getAvatar());
            avatarBox.getChildren().add(image);
            image.setFitWidth(100);
            image.setFitHeight(100);
            this.getChildren().add(avatarBox);

            image.setOnMouseClicked(eh -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Json Files", "*.json"));
                File file = fileChooser.showOpenDialog(App.getStage());
        
                if (file == null) return;

                try {
                    character.setAvatar(new Image(file.toURI().toURL().toString()));
                    image.setImage(character.getAvatar());
                }
                catch (Exception e){
                    // TODO: handle exception
                    e.printStackTrace();
                }
            });
        }
        catch (Exception e){
            // TODO: handle exception
            e.printStackTrace();
        }

        VBox characterBox = new VBox();
        this.setPadding(Const.normalPadding);
        characterBox.setSpacing(10);
        TextField nameField = new TextField(character.getName());

        nameField.setEditable(true);
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            character.setName(newValue);
        });
        characterBox.getChildren().add(new HBox(nameField));
        
        CheckBox narratorBox = new CheckBox("Narrator");
        narratorBox.setSelected(character.getNarrator());
        narratorBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            character.setNarrator(newValue);
        });

        characterBox.getChildren().add(new HBox(narratorBox));

        CheckBox invisibleBox = new CheckBox("Invisible");
        invisibleBox.setSelected(!character.getVisible());
        invisibleBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            character.setVisible(!newValue);
        });

        characterBox.getChildren().add(new HBox(invisibleBox));
        this.getChildren().add(characterBox);
    }
}
