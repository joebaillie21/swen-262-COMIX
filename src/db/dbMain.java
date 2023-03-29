package db;

public class dbMain {

    public static void main(String[] args) throws Exception {

        dbManager.getConnection();
        dbManager.createComixTable();

    }

}
