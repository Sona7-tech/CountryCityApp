/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import world.country.city.dao.CityDao;
import world.country.city.dao.LoginCompanyDao;
import world.country.city.impl.CityDaoImpl;
import world.country.city.impl.LoginCompanyDaoImpl;
import world.country.city.request.ReqCity;
import world.country.city.request.ReqUser;
import world.country.city.response.RespCity;
import world.country.city.response.RespCityList;
import world.country.city.response.RespStatus;
import world.country.city.service.CityService;
import world.country.city.service.CityServiceImpl;
import world.country.city.service.LoginService;
import world.country.city.service.LoginServiceImpl;

@Path("/city")
public class CityWebServices {
    
    LoginCompanyDao loginCompanyDao = new LoginCompanyDaoImpl();
    CityDao cityDao = new CityDaoImpl();
    CityService cityService = new CityServiceImpl(cityDao,loginCompanyDao);
    
    @GET
    @Path("/getCityList")
    @Produces(MediaType.APPLICATION_JSON)
    public RespCityList getCityList(ReqUser reqUser) throws Exception{
          return cityService.getCityList(reqUser);
    
    }
    
    @POST
    @Path("/getCityById")
    @Produces(MediaType.APPLICATION_JSON)
    public RespCity getCityById(ReqCity reqCity){
        return cityService.getCityById(reqCity);
        
    }
    @POST
    @Path("/addCity")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespStatus addCity(ReqCity reqCity) throws Exception{
          return cityService.addCity(reqCity);
    
    }
    
    @PUT
    @Path("/updateCity")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespStatus updateCity(ReqCity reqCity) throws Exception{
          return cityService.updateCity(reqCity);
    
    }
    
    
    
}
