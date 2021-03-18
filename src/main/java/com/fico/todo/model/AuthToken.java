package com.fico.todo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AuthToken {
    @ApiModelProperty(notes = "Token's string", position = 1)
    private String token;
    private long tokenValidity;

    public long getTokenValidity() {
        return tokenValidity;
    }

    public void setTokenValidity(long tokenValidity) {
        this.tokenValidity = tokenValidity;
    }

    public AuthToken(){

    }

    public AuthToken(String token, long tokenValidity){
        this.token = token;
        this.tokenValidity = tokenValidity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
