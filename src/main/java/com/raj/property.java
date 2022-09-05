package com.raj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class property {
    public Connection connection() {
        try {
            FileInputStream fis=new FileInputStream("C:\\Users\\Raj\\IdeaProjects\\Contact\\ContactServlet\\src\\main\\resources\\Dbproperty.properties");
            Properties p = new Properties();
            p.load(fis);

            String url = ""+p.getProperty("url")+p.getProperty("db");

            Class.forName(p.getProperty("classname"));
            Connection con = DriverManager.getConnection(url, p.getProperty("user"), p.getProperty("pass"));

            return con;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
