package com.raj;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Delete extends HttpServlet {
    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        property p = new property();
        PrintWriter out = res.getWriter();

        Reader red = req.getReader();
        JSONParser parser = new JSONParser();
        try {
            JSONObject jobj = (JSONObject) parser.parse(red);
            String i = (String) jobj.get("id");
            Integer id = Integer.parseInt(i);

            try {
                Connection conn = p.connection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM details WHERE id=" + id + ";");

                while (rs.next())
                    out.println("Deleting Entry: \n" + rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));

                int status = stm.executeUpdate("DELETE FROM details WHERE id="+id+";");
                if (status == 1)
                    out.println("Deleted successful");
                else
                    out.println("Not Deleted");
                res.setContentType("application/json");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }
