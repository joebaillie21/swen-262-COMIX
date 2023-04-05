package db;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.ComicBook;

public interface iDatabase {

    public void createTable(String sql) throws Exception;

    public ResultSet getTable() throws Exception;

    public ResultSet getTable(String sql) throws Exception;

    public ArrayList<ComicBook> resToArrayList(ResultSet res) throws Exception;

}
