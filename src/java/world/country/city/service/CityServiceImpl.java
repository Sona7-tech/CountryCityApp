/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.service;

import java.util.ArrayList;
import java.util.List;
import world.country.city.dao.CityDao;
import world.country.city.dao.LoginCompanyDao;
import world.country.city.impl.LoginCompanyDaoImpl;
import world.country.city.model.City;
import world.country.city.model.Country;
import world.country.city.model.LoginCompany;
import world.country.city.request.ReqCity;
import world.country.city.request.ReqUser;
import world.country.city.response.RespCity;
import world.country.city.response.RespCityList;
import world.country.city.response.RespCountry;
import world.country.city.response.RespStatus;
import world.country.city.util.ExceptionConstants;

public class CityServiceImpl implements CityService{

    private CityDao cityDao;
    private LoginCompanyDao loginCompanyDao;
    public CityServiceImpl(CityDao cityDao) {
       this.cityDao = cityDao;
    }
    public CityServiceImpl(CityDao cityDao, LoginCompanyDao loginCompanyDao) {
       this.cityDao = cityDao;
       this.loginCompanyDao = loginCompanyDao;
    }

    @Override
    public RespCityList getCityList(ReqUser reqUser) {
       
        RespCityList response = new RespCityList();
        List<RespCity> respCityList = new ArrayList<RespCity>();
        Long userID = reqUser.getUserId();
        String token = reqUser.getToken();
        try {
            if(userID == null || token == null){
            response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data"));
            return response;
            }
            LoginCompany loginCompany = loginCompanyDao.checkToken(userID, token);
            if(loginCompany == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_TOKEN,"Invalid token"));
                return response;
            }
            List<City> cityList = cityDao.getCityList();
            if(cityList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.CITY_NOT_FOUND,"City not found"));
                
                return response;
            }
            for(City city: cityList){
                RespCity respCity = new RespCity();
                respCity.setCityId(city.getID());
                respCity.setName(city.getName());
                RespCountry respCountry = new RespCountry();
                respCountry.setCountryId(city.getCountry().getID());
                respCountry.setName(city.getCountry().getName());
                respCity.setCountry(respCountry);
                respCityList.add(respCity);
                
            }
            response.setCityList(respCityList);
            response.setStatus(RespStatus.getSuccessMessage());
            
            
        } catch (Exception e) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public RespStatus addCity(ReqCity reqCity) {
       RespStatus response = null;
        try {
            Long userId = reqCity.getUserId();
            String token = reqCity.getToken();
            
            String name = reqCity.getName();
            Long countryId = reqCity.getCountry().getCountryId();
            String countryName = reqCity.getCountry().getName();
            if(name == null || countryName == null || countryId == null || userId == null || token == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
            }
            City city = new City();
            city.setName(name);
            Country country = new Country();
            country.setID(countryId);
            city.setCountry(country);
            cityDao.addCity(city);
            response = RespStatus.getSuccessMessage();
            
        } catch (Exception e) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public RespCity getCityById(ReqCity reqCity) {
     RespCity response = new RespCity();
     Long cityId = reqCity.getCityId();
     Long userId = reqCity.getUserId();
     String token = reqCity.getToken();
        try {
            if(cityId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }
            if(userId == null || token == null){
            response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data"));
            return response;
            }
            LoginCompany loginCompany = loginCompanyDao.checkToken(userId, token);
            if(loginCompany == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_TOKEN,"Invalid token"));
                return response;
            }
            City city = cityDao.getCitybyId(cityId);
            if(city ==null){
                response.setStatus(new RespStatus(ExceptionConstants.CITY_NOT_FOUND, "City nnot found"));
                return response;
            }
            response.setCityId(city.getID());
            response.setName(city.getName());
            RespCountry respCountry = new RespCountry();
            respCountry.setCountryId(city.getCountry().getID());
            respCountry.setName(city.getCountry().getName());
            response.setCountry(respCountry);
            response.setStatus(RespStatus.getSuccessMessage());
        
            
        } catch (Exception e) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public RespStatus updateCity(ReqCity reqCity) {
         RespStatus response = null;
        try {
             Long userId = reqCity.getUserId();
            String token = reqCity.getToken();
            String name = reqCity.getName();
            Long cityId = reqCity.getCityId();
            Long countryId = reqCity.getCountry().getCountryId();
            if(name == null ||  cityId == null ||countryId == null || userId == null || token == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
            }
          
            City city = cityDao.getCitybyId(cityId);
            if(city == null){
                response = new RespStatus(ExceptionConstants.CITY_NOT_FOUND,"City not found");
                return response;
            }
            city.setName(name);
            Country country = new Country();
            country.setID(countryId);
            city.setCountry(country);
            cityDao.updateCity(city);
            response = RespStatus.getSuccessMessage();
            
        } catch (Exception e) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            e.printStackTrace();
        }
        return response;
        
    }
    
    
}
