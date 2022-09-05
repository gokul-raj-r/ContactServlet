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
public class Edit extends HttpServlet {

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        property p = new property();
        PrintWriter out = res.getWriter();

        Reader red = req.getReader();
        JSONParser parser = new JSONParser();
        try {
            JSONObject jobj =(JSONObject) parser.parse(red);
            String i = (String) jobj.get("id");
            Integer id = Integer.parseInt(i);
            String num = (String) jobj.get("number");
            Integer number = Integer.parseInt(num);
            String n = (String) jobj.get("name");
            String email = (String) jobj.get("mail");
            String comp = (String) jobj.get("company");

            try{
                Connection conn = p.connection();
                Statement stm = conn.createStatement();
                ResultSet rs=stm.executeQuery("SELECT * FROM details WHERE id="+id+";");

                while(rs.next())
                    out.println("Before Update: \n"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));

                int status = stm.executeUpdate("UPDATE details SET name = '"+n+"', number= "+number+", email= '"+email+"', company= '"+comp+"' WHERE id ="+id+";");
                if(status==1)
                    out.println("Update successful");
                else
                    out.println("Not Updated");

                ResultSet rsu=stm.executeQuery("SELECT * FROM details WHERE id="+id+";");
                res.setContentType("application/json");
                while(rsu.next())
                    out.println("Current values: \n"+rsu.getInt(1)+"  "+rsu.getString(2)+"  "+rsu.getString(3)+"  "+rsu.getString(4));

            }catch(Exception e){ e.printStackTrace();}

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
