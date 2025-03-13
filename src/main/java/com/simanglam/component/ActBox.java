package com.simanglam.component;

import com.simanglam.model.Act;
import com.simanglam.util.Const;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ActBox extends HBox {
    public Act act;
    
    public ActBox(Act act){
        this.act = act;
        this.getChildren().add(new Text(act.getName()));
        this.setPadding(Const.normalPadding);
        this.setPrefWidth(100000);
    }
}
