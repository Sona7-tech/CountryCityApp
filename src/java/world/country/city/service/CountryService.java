/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.service;

import java.util.List;
import world.country.city.model.Country;
import world.country.city.request.ReqUser;
import world.country.city.response.RespCountry;
import world.country.city.response.RespCountryList;

/**
 *
 * @author Lenovo
 */
public interface CountryService  {
    RespCountryList getCountryList(ReqUser reqUser);
}
