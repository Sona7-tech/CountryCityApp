/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.dao;

import java.util.List;
import world.country.city.model.Country;

/**
 *
 * @author Lenovo
 */
public interface CountryDao {
    
    List <Country> getCountryList() throws Exception;
}
