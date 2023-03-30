package tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Assert;
import org.junit.Test;

import db.dbBuild;
import db.dbConnection;
import junit.*;

public class testDatabase {

    /**
     * @author Joe Baillie
     */
    @Test
    public void testCreateComics() throws Exception {
        dbBuild.Build();
        Connection con = dbConnection.getConnection();
        PreparedStatement getComics = con.prepareStatement("""
                SELECT * FROM comics;
                """);
        ResultSet comics = getComics.executeQuery();
        // Assert.assertEquals(comics, ;

    }

}
