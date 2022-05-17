/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.dao;

import java.util.List;
import world.country.city.model.City;

/**
 *
 * @author Lenovo
 */
public interface CityDao {
    List <City> getCityList() throws Exception;
    void updateCity(City city) throws Exception;
    void addCity(City city) throws Exception;
    City getCitybyId(Long paymentId) throws Exception;
}
