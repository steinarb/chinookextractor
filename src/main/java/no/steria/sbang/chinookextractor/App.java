package no.steria.sbang.chinookextractor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

    
        String url = "jdbc:postgresql://localhost/Chinook";
        String user = "chinook";
        String password = "klrp320";

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * from \"InvoiceLine\"");

            JSONArray resultSetAsJson = ResultSetConverter.convert(resultSet);
            for(int i=0; i<resultSetAsJson.length(); ++i) {
            	JSONObject rowAsJson = resultSetAsJson.getJSONObject(i);
            	System.out.println(rowAsJson);
            }

        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(App.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException ex) {
                Logger logger = Logger.getLogger(App.class.getName());
                logger.log(Level.WARNING, ex.getMessage(), ex);
            }
        }    }
}
