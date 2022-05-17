/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.impl;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBHelper {
    public static Connection getConnection() throws Exception{
        Context context = new InitialContext();
       DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CountryCity");
     Connection c =  dataSource.getConnection();
     return c;
    }
}
