package com.zhanghe.creater.jdbc;
import java.sql.DriverManager;
import java.sql.SQLException;  
import java.sql.Connection;   

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
  
@Repository
public class ConnectDb {  
	@Value("${database_driver}")
    private String driveClassName = "";
	@Value("${database_url}")
    private String url = "";   
	@Value("${database_user}")
    private String user = "";  
	@Value("${database_password}")
    private String password = "";  
      
    public Connection Connect(){  
        Connection conn = null;  
          
        //load driver  
        try {  
            Class.forName(driveClassName);  
        } catch (ClassNotFoundException  e) {  
            System.out.println("load driver failed!");  
            e.printStackTrace();  
        }  
          
        //connect db  
        try {  
            conn = DriverManager.getConnection(url, user, password);  
        } catch (SQLException e) {  
            System.out.println("connect failed!");  
            e.printStackTrace();  
        }         
          
        return conn;  
    }  
}  