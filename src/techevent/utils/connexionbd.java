package techevent.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Wael
 */
public class connexionbd {
    private static String url="jdbc:mysql://127.0.0.1:3306/techevent";
    private static String user = "root";
    private static String password = "";
    private static Connection conn;

    private static  connexionbd inst;

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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


    