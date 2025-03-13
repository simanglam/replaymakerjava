package com.simanglam.component;

import com.simanglam.model.Message;
import com.simanglam.util.Const;

import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SceneMessageBox extends HBox {
    
    Message message;
    FlowPane flowPane;

    public SceneMessageBox(Message message){
        this.message = message;
        flowPane = new FlowPane();
        this.getChildren().add(flowPane);
        this.setPadding(Const.normalPadding);
        
        Text nameText = new Text(message.getAuthor().getName());
        nameText.setStyle("-fx-font: 24 arial;");
        HBox nameBox = new HBox(nameText);
        nameBox.setPadding(Const.normalPadding);
        flowPane.getChildren().add(nameBox);

        TextArea messageArea = new TextArea(message.getContent());
        messageArea.setPrefRowCount(3);
        flowPane.getChildren().add(new HBox(messageArea));
    }

}
