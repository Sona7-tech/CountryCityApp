/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import world.country.city.dao.CountryDao;
import world.country.city.model.Country;

public class CountryDaoImpl implements CountryDao{

    @Override
    public List<Country> getCountryList() throws Exception {
        
        List<Country> countryList = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM COUNTRIES\n" +
"                WHERE ACTIVE = 1";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                Country country = new Country();
                country.setID(rs.getLong("ID"));
                country.setName(rs.getString("NAME"));
               
                countryList.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return countryList;
       
    }
    
}
