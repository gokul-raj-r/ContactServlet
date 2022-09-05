package com.raj;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Add extends HttpServlet
{
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
    {
        Reader red = req.getReader();
        JSONParser parser = new JSONParser();

        property p=new property();

        try {
            JSONObject jobj =(JSONObject) parser.parse(red);
//            String i = (String) jobj.get("id");
//            Integer id = Integer.parseInt(i);
            String num = (String) jobj.get("number");
            Integer number = Integer.parseInt(num);
            String n = (String) jobj.get("name");
            String email = (String) jobj.get("mail");
            String comp = (String) jobj.get("company");
            PrintWriter out = res.getWriter();

            try{
               Connection cn = p.connection();
                Statement stmt = cn.createStatement();

                int r = stmt.executeUpdate("insert into details (number,name,email,company) values("+number+",'"+n+"','"+email+"','"+comp+"')");
                ResultSet rs=stmt.executeQuery("select * from details");
                out.println("Created...");
            }catch(Exception e){ e.printStackTrace();}
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
