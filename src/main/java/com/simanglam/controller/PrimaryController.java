package com.simanglam.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;

import javax.imageio.stream.FileImageInputStream;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simanglam.App;
import com.simanglam.component.ActBox;
import com.simanglam.component.CharacterBox;
import com.simanglam.component.SceneMessageBox;
import com.simanglam.model.Act;
import com.simanglam.model.Character;
import com.simanglam.model.Message;
import com.simanglam.util.Const;
import com.simanglam.util.GlobalStorage;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;

public class PrimaryController {

    @FXML VBox characterListPane;
    @FXML FlowPane scenePane;
    @FXML VBox sceneDetailPane;

    GlobalStorage model;
    Act currentAct;

    @FXML
    public void initialize() {
        model = GlobalStorage.getInstance();
        sceneDetailPane.setFillWidth(true);
        loadFile(new File("/Users/simanglam/Downloads/[結團]怪物們與綺想教團 - 哈索萊賓館 - 狼的夢 [1218583764544782387].json"));
        loadFile(new File("/Users/simanglam/Downloads/[結團]怪物們與綺想教團 - 哈索萊賓館 - 夜晚的工業區。 [1204449201774137374].json"));
    }

    public void loadFile(File file){

        Act newAct = null;
        try {
            newAct = new ObjectMapper().readValue(file, Act.class);
        }
        catch (DatabindException e){
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("File format error.");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
            return;
        }
        catch (IOException e){
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("Error reading file.");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
            return;
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }

        
        newAct.setId(model.getAllActs().size());
        model.addAct(newAct);

        characterListPane.getChildren().clear();
        model.getAllCharacters().forEach(character -> {
            CharacterBox characterBox = new CharacterBox(character);
            characterListPane.getChildren().add(characterBox);
        });

        scenePane.getChildren().clear();
        model.getAllActs().forEach((Act act) -> {
            final Delta delta = new Delta();
            ActBox actBox = new ActBox(act);
            scenePane.getChildren().add(actBox);
            actBox.setOnMouseClicked((MouseEvent event) -> {
                if (currentAct == act) return;
                sceneDetailPane.getChildren().clear();
                act.getrDialogs().forEach((Message message) -> {
                    sceneDetailPane.getChildren().add(new SceneMessageBox(message));
                });
                delta.y = actBox.getLayoutY() + actBox.getTranslateY() - event.getSceneY();
            });

            actBox.setOnMousePressed((MouseEvent event) -> {
                delta.y = event.getSceneY();
            });

            actBox.setOnMouseDragged((MouseEvent event) -> {
                actBox.setTranslateY(event.getSceneY() - delta.y);
            });

            actBox.setOnMouseReleased((MouseEvent event) -> {
                actBox.setLayoutY(actBox.getTranslateY() + actBox.getLayoutY());
                actBox.setTranslateY(0);
                ObservableList<Node> newOrder = FXCollections.observableArrayList(
                    scenePane.getChildren()
                );
                newOrder.sort((Node a, Node b) -> {
                    return (a.getLayoutY() > b.getLayoutY()) ? 1 : -1;
                });
                scenePane.getChildren().setAll(newOrder);
                model.getAllActs().clear();
                for (Node node : newOrder){
                    model.getAllActs().add(((ActBox)node).act);
                    System.out.println(((ActBox)node).act.getName());
                }
                scenePane.layout();
            });

        });
    }

    @FXML
    public void loadFile(){

        Stage stage = App.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        Window.getWindows();
        File file = fileChooser.showOpenDialog(stage);
        
        if (file == null) return;
        loadFile(file);
    }

    @FXML
    public void savedToFile(){
        
    }

    @FXML
    public void exportFile(){
        System.out.println("Exporting file");
        try(OutputStreamWriter os = new FileWriter("/Users/simanglam/Desktop/Projects/replaymakerjava/out.txt")) {
            for(Character character : model.getAllCharacters()){
                character.export(os);
            }
            for (Act act : model.getAllActs()){
                act.export(os);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            // TODO: Handle Exception
            e.printStackTrace();
        }
    }
}


class Delta { double x, y; }