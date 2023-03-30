package tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import db.dbBuild;
import db.dbConnection;
import junit.*;

public class testDatabase {

    @Test
    public void testLoadData() throws Exception {
        // Setup
        Connection con = dbConnection.getConnection();
        ResultSet expected = null;

        // Invoke
        dbBuild.Build();
        PreparedStatement getData = con.prepareStatement("SELECT * FROM comics");
        ResultSet result = getData.executeQuery();

        // Analyze
        while (result.next()) {

        }

    }
}
