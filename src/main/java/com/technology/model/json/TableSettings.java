package com.technology.model.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iwk0 on 02/03/2015.
 */
public class TableSettings {

    private String page;
    private int total;
    private String records;
    private List<Rows> rows;

    public TableSettings(String page, int total, String records) {
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = new ArrayList<>();
    }

    public void addRow(Rows newRows) {
        rows.add(newRows);
    }
}