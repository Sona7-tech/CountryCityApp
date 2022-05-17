/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.service;

import java.util.UUID;
import world.country.city.dao.LoginCompanyDao;
import world.country.city.model.LoginCompany;
import world.country.city.request.ReqLogin;
import world.country.city.request.ReqUser;
import world.country.city.response.RespLogin;
import world.country.city.response.RespStatus;
import world.country.city.util.ExceptionConstants;

/**
 *
 * @author Lenovo
 */
public class LoginServiceImpl implements LoginService{

   private  LoginCompanyDao loginCompanyDao;
   
    public LoginServiceImpl(LoginCompanyDao loginCompanyDao) {
       this.loginCompanyDao = loginCompanyDao;
    }

    @Override
    public RespLogin loginUser(ReqLogin reqLogin) {
        RespLogin response = new RespLogin();
        try {
            String username = reqLogin.getUsername();
            String password = reqLogin.getPassword();
            if(username == null || password == null){
                response.setStatus(new RespStatus(ExceptionConstants.USERNAME_OR_PASSWORD_IS_EMPTY, "Username or password is empty"));
                return response;
            }
            LoginCompany loginCompany = loginCompanyDao.login(username, password);
            if(loginCompany == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_LOGIN,"Invalid login"));
                return response;
            }
            if(loginCompany.getToken() != null){
                response.setStatus(new RespStatus(ExceptionConstants.SESSION_IS_ALREADY_EXIST,"Session is already exist"));
                return response;
            }
            String token = UUID.randomUUID().toString();
            loginCompany.setToken(token);
            loginCompanyDao.updateToken(loginCompany);
            response.setToken(token);
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
       return null;
       
    }

    @Override
    public RespStatus logOut(ReqUser reqUser) {
        
        RespStatus response = null;
        Long userId = reqUser.getUserId();
        String token = reqUser.getToken();
        try {
            if(userId == null || token == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
            }
            LoginCompany loginCompany = loginCompanyDao.checkToken(userId, token);
            if(loginCompany == null){
                return new RespStatus(ExceptionConstants.INVALID_TOKEN,"Invalid Token");
            }
            loginCompany.setToken(null);
            loginCompanyDao.updateToken(loginCompany);
            response = RespStatus.getSuccessMessage();
        } catch (Exception e) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal exception");
            e.printStackTrace();
        }
       return response;
    }
    
}
