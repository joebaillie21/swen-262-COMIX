package com.swen.comix.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.swen.comix.db.Database;

public class ImportFromSQL implements Importer {

    private Database db;

    public ImportFromSQL(Database db) {
        this.db = db;
    }

    @Override
    public ArrayList<ComicBookComponent> importToJava() throws Exception {
        ResultSet res = db.getTable();
        ArrayList<ComicBook> cb = db.resToArrayList(res);
        ArrayList<ComicBookComponent> cbc = new ArrayList<>();
        for (ComicBook c : cb) {
            if (c instanceof ComicBookComponent) {
                ComicBookComponent hold = (ComicBookComponent) c;
                cbc.add(hold);
            }
        }
        return cbc;
    }

}
