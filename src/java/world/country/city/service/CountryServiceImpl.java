/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.service;

import java.util.ArrayList;
import java.util.List;
import world.country.city.dao.CountryDao;
import world.country.city.dao.LoginCompanyDao;
import world.country.city.model.Country;
import world.country.city.model.LoginCompany;
import world.country.city.request.ReqUser;
import world.country.city.response.RespCountry;
import world.country.city.response.RespCountryList;
import world.country.city.response.RespStatus;
import world.country.city.util.ExceptionConstants;

/**
 *
 * @author Lenovo
 */
public class CountryServiceImpl implements CountryService{

    private CountryDao countryDao;
    private LoginCompanyDao loginCompanyDao;
    public CountryServiceImpl(CountryDao countryDao) {
      this.countryDao = countryDao;
    }
    public CountryServiceImpl(CountryDao countryDao, LoginCompanyDao loginCompanyDao) {
      this.countryDao = countryDao;
      this.loginCompanyDao = loginCompanyDao;
    }
    

    @Override
    public RespCountryList getCountryList(ReqUser reqUser) {
        RespCountryList response = new RespCountryList();
       List<RespCountry> respCountryList = new ArrayList<RespCountry>();
       Long userID = reqUser.getUserId();
       String token = reqUser.getToken();
        try {
            if(userID == null || token == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data"));
                return response;
            }
            LoginCompany loginCompany = loginCompanyDao.checkToken(userID,token);
            if(loginCompany == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_TOKEN,"Invalid token"));
                return response;
            }
            
          List<Country> countryList = countryDao.getCountryList();
          for(Country country: countryList){
              RespCountry respCountry = new RespCountry();
              respCountry.setCountryId(country.getID());
              respCountry.setName(country.getName());
              respCountryList.add(respCountry);
          }
          
          response.setCountryList(respCountryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
}
