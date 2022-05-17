/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.country.city.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import world.country.city.model.Country;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class RespCity {
    private Long cityId;
    private String name;
    private RespCountry country;
    private RespStatus status;

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
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
    
}
