package db;

public class dbMain {

    public static void main(String[] args) throws Exception {

        dbConnection.getConnection();
        dbSetup.createTable();

    }

    public static void createTable() throws Exception {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
