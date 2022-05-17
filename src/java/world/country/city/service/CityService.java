/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.service;

import world.country.city.request.ReqCity;
import world.country.city.request.ReqUser;
import world.country.city.response.RespCity;
import world.country.city.response.RespCityList;
import world.country.city.response.RespStatus;

public interface CityService {

    RespStatus addCity(ReqCity reqCity);
    RespCityList getCityList(ReqUser reqUser);

    RespCity getCityById(ReqCity reqCity);

    RespStatus updateCity(ReqCity reqCity);
}
