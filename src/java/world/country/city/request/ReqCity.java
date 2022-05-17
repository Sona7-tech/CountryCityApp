/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import world.country.city.response.RespCountry;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReqCity {
   private Long cityId;
   private String name;
   private RespCountry country;
   private Long userId;
   private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RespCountry getCountry() {
        return country;
    }

    public void setCountry(RespCountry country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "ReqCity{" + "cityId=" + cityId + ", name=" + name + ", country=" + country + '}';
    }
    
}
