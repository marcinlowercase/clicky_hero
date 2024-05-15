package com.example.treytontheoassign;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Combo implements Serializable {

    private String comboName;
    private List<Integer> comboItems = new ArrayList<>();
    private boolean isAttempted;
    private boolean isCorrect;

    public Combo(String comboName) {
        this.comboName = comboName;

        int numberOfItem = (int) (Math.random() * 5 ) + 4;

        for (int i = 0; i < numberOfItem; i++){
            this.comboItems.add((int) (Math.random() * 4));
        }

    }

    public void restartCombo(){
        this.comboItems.clear();

        int numberOfItem = (int) (Math.random() * 5 ) + 4;

        for (int i = 0; i < numberOfItem; i++){
            this.comboItems.add((int) (Math.random() * 4));
        }
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public List<Integer> getComboItems() {
        return comboItems;
    }

    public void setComboItems(List<Integer> comboItems) {
        this.comboItems = comboItems;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "comboName='" + comboName + '\'' +
                ", comboItems=" + comboItems +
                '}';
    }
}
