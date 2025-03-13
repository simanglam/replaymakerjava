package com.simanglam.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.simanglam.model.Act;
import com.simanglam.model.Character;

public class GlobalStorage {
    private static GlobalStorage instance = null;
    private HashMap<String, Character> characters;
    private ArrayList<Act> acts;


    private GlobalStorage() {
        characters = new HashMap<>();
        acts = new ArrayList<>();
    }

    synchronized public static GlobalStorage getInstance() {
        if (instance == null) {
            instance = new GlobalStorage();
        }
        return instance;
    }

    public boolean hasCharacter(String name){ return characters.containsKey(name); }
    public Character getCharacter(String name){ return characters.get(name); }
    public ArrayList<Character> getAllCharacters(){ return new ArrayList<>(characters.values()); }
    public void addCharacter(String name){ characters.put(name, new Character(name, characters.size())); }

    public ArrayList<Act> getAllActs(){ return acts; }
    public void addAct(Act act){ act.setId(acts.size()); acts.add(act);}

}
