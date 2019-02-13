package techevents.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class connexionbd {
    private static String url="jdbc:mysql://127.0.0.1:3306/techevent";
    private static String user = "root";
    private static String password = "";
    private static Connection conn;

    private static  connexionbd inst;
    
    private connexionbd(){
     try{
        conn = DriverManager.getConnection(url,user,password);
            System.out.println("success");
        }
        catch(SQLException e){
        System.out.println("erreur");
        }
    }
    public static connexionbd getinstance(){
        if (inst == null)
       { inst = new connexionbd();
      
       }
        return inst;
        
    }
    public Connection getConn() {
        return conn;
    }
    }


    