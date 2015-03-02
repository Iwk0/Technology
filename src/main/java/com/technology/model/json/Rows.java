package com.technology.model.json;

import java.util.List;

/**
 * Created by Iwk0 on 02/03/2015.
 */
public class Rows {

    private String id;
    private List<String> cell;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCell() {
        return cell;
    }

    public void setCell(List<String> cell) {
        this.cell = cell;
    }
}