package com.raj;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
public class Display extends HttpServlet {
    property p = new property();
    public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = res.getWriter();
        try{
            Connection conn = p.connection();
            Statement stm = conn.createStatement();
            ResultSet rs=stm.executeQuery("select * from details");
            res.setContentType("application/json");
            while(rs.next())
                out.println("Entries: \n"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));
//                con.close();
        }catch(Exception e){ e.printStackTrace();}
    }
}