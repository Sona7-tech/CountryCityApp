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
import world.country.city.dao.CityDao;
import world.country.city.model.City;
import world.country.city.model.Country;


public class CityDaoImpl implements CityDao{

    @Override
    public List<City> getCityList() throws Exception {
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT C.ID C_ID, C.NAME C_NAME, O.ID O_ID, O.NAME O_NAME\n" +
"                                         FROM CITIES C\n" +
"                                         INNER JOIN COUNTRIES O\n" +
"                                          ON C.COUNTRY_ID = O.ID\n" +
"                                          WHERE C.ACTIVE = 1 AND O.ACTIVE = 1\n" +
"                                             ORDER BY C_ID";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Country country = new Country();
                country.setID(rs.getLong("O_ID"));
                country.setName(rs.getString("O_NAME"));

                City city = new City();
                city.setID(rs.getLong("C_ID"));
                city.setName(rs.getString("C_NAME"));
                city.setCountry(country);
                cityList.add(city);
            }
        }
        return cityList;
    }

    @Override
    public void updateCity(City city) throws Exception {
       String sql = "UPDATE CITIES SET NAME = ?, COUNTRY_ID = ? WHERE ID = ?";
        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, city.getName());
            ps.setLong(2, city.getCountry().getID());
            ps.setLong(3, city.getID());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void addCity(City city) throws Exception {
       String sql = "INSERT INTO CITIES\n"
                + "VALUES(CITY_SEQ.NEXTVAL,?,?,SYSDATE,1)";
        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, city.getName());
            ps.setLong(2, city.getCountry().getID());
            ps.execute();
            c.commit();

        }
    }

    @Override
    public City getCitybyId(Long paymentId) throws Exception {
        City city = new City();

        String sql = "SELECT C.ID C_ID, C.NAME C_NAME, O.ID O_ID, O.NAME O_NAME\n" +
"                 FROM CITIES C\n" +
"                 INNER JOIN COUNTRIES O\n" +
"                 ON C.COUNTRY_ID = O.ID\n" +
"                 WHERE C.ACTIVE = 1\n" +
"                 AND C.ID = ?";
        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, paymentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
                city.setID(rs.getLong("C_ID"));
                city.setName(rs.getString("C_NAME"));
                Country country = new Country();
                country.setID(rs.getLong("O_ID"));
                country.setName(rs.getString("O_NAME"));
                city.setCountry(country);

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return city;
    }
    
}
