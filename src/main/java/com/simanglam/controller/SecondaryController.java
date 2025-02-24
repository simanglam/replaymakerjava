package com.simanglam.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.simanglam.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}