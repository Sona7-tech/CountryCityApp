/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.service;

import world.country.city.request.ReqLogin;
import world.country.city.request.ReqUser;
import world.country.city.response.RespLogin;
import world.country.city.response.RespStatus;

/**
 *
 * @author Lenovo
 */
public interface LoginService {

    RespLogin loginUser(ReqLogin reqLogin);

    RespStatus logOut(ReqUser reqUser);
}
