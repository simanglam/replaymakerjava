package com.simanglam.model;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javafx.scene.image.Image;

public class Character {
    private Image avatar = null;
    private String nickname;
    private int id;
    private boolean isNarrator = false;
    private boolean isVisible = true;
    
    public Character(String name, int id){
        this.nickname = name;
        this.id = id;
    }

    public void setAvatar(Image avatar) { this.avatar = avatar; }
    public Image getAvatar() { return this.avatar; }

    public void setNarrator(boolean isNarrator) { this.isNarrator = isNarrator; }
    public boolean getNarrator() { return this.isNarrator; }

    public void setVisible(boolean isVisible) { this.isVisible = isVisible; }
    public boolean getVisible() { return this.isVisible; }
    
    public void setId(int id) { this.id = id; }
    public int getId() { return this.id; }

    public void setName(String name) { this.nickname = name; }
    public String getName() { return this.nickname; }

    public void export(OutputStreamWriter os) throws IOException {
        os.write(String.format("define c%d = Character(%s, image = \"c%d.png\")\n", this.id, this.nickname, this.id));
    }
}
