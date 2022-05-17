/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import world.country.city.dao.LoginCompanyDao;
import world.country.city.model.LoginCompany;

/**
 *
 * @author Lenovo
 */
public class LoginCompanyDaoImpl implements LoginCompanyDao {

    LoginCompany loginCompany = new LoginCompany();

    @Override
    public LoginCompany login(String username, String password) throws Exception {

        String sql = "SELECT LU.ID LU_ID, LU.USERNAME LU_USERNAME, LU.COMPANY_NAME, LU.TOKEN  FROM LOGIN_COMPANY LU\n"
                + "WHERE LU.ACTIVE = 1 AND LU.USERNAME = ? AND LU.PASSWORD = ?";

        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                loginCompany.setID(rs.getLong("LU_ID"));
                loginCompany.setUsername(rs.getString("LU_USERNAME"));
                loginCompany.setCompanyName(rs.getString("COMPANY_NAME"));
                loginCompany.setToken(rs.getString("TOKEN"));
            } else {
                loginCompany = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginCompany;
    }

    @Override
    public void updateToken(LoginCompany loginCompany) throws Exception {
        String sql = "UPDATE LOGIN_COMPANY SET TOKEN = ? WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, loginCompany.getToken());

            ps.setLong(2, loginCompany.getID());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public LoginCompany checkToken(Long userID, String token) throws Exception{
     LoginCompany loginUser = new LoginCompany();
     String sql ="SELECT * FROM LOGIN_COMPANY WHERE ACTIVE = 1 AND ID = ? AND TOKEN =?";
     try(Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
         ps.setLong(1, userID);
         ps.setString(2, token);
         ResultSet rs = ps.executeQuery();
         if(rs.next()){
             loginUser.setID(rs.getLong("ID"));
             loginUser.setUsername(rs.getString("USERNAME"));
             loginUser.setCompanyName(rs.getString("COMPANY_NAME"));
             loginUser.setToken(rs.getString("TOKEN"));
             
         }
     }  
        return loginUser;
    
     }
    }
