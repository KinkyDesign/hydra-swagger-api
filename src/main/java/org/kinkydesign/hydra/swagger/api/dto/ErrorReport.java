/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.dto;

import org.kinkydesign.hydra.swagger.api.annotations.JsonldProperty;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldType;

/**
 *
 * @author pantelispanka
 */
@JsonldType("http://schema.org/errorreport")
public class ErrorReport {
    
    @JsonldProperty("http://schema.org/httpstatus")
    private Integer status;
    @JsonldProperty("http://schema.org/message")
    private String errorMessage;
    @JsonldProperty("http://schema.org/stacktrace")
    private String devMessage;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

}
