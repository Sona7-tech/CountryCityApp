/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import world.country.city.dao.LoginCompanyDao;
import world.country.city.impl.LoginCompanyDaoImpl;
import world.country.city.model.LoginCompany;
import world.country.city.request.ReqLogin;
import world.country.city.request.ReqUser;
import world.country.city.response.RespCity;
import world.country.city.response.RespLogin;
import world.country.city.response.RespStatus;
import world.country.city.service.LoginService;
import world.country.city.service.LoginServiceImpl;

/**
 *
 * @author Lenovo
 */
@Path("/register")
public class LoginWebServices {
    
    LoginCompanyDao loginCompanyDao = new LoginCompanyDaoImpl();
    LoginService  loginService = new LoginServiceImpl(loginCompanyDao);
    
    @POST
    @Path("/loginUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespLogin LoginUser(ReqLogin reqLogin){
        return loginService.loginUser(reqLogin);
        
    }
    
    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespStatus logout(ReqUser reqUser){
        return loginService.logOut(reqUser);
    }
    
}
