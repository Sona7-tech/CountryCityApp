/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.dao;

import world.country.city.model.LoginCompany;

public interface LoginCompanyDao {

    LoginCompany login(String username, String password) throws Exception;

    void updateToken(LoginCompany loginCompany) throws Exception;

    LoginCompany checkToken(Long userID, String token)throws Exception;
}
