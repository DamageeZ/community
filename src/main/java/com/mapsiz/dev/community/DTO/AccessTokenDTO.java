package com.mapsiz.dev.community.DTO;

/**
 * @Author: DamageeZ
 * @Create: 05-31-2021 17:32
 */
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String state;
    private String Redirect_uri;

    public String getRedirect_uri() {
        return Redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        Redirect_uri = redirect_uri;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
