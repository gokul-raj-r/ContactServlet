package com.raj;

public class Details {
    String name,email,company;
    int number;
    Details(String n,String e,String c,int num)
    {
        name=n;
        email=e;
        company=c;
        number = num;
    }
    String display()
    {
        String dis =""+name+" "+number+" "+company+" "+email;
        return dis;
    }
}
