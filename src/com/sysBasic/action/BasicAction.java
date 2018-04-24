package com.sysBasic.action;

import com.opensymphony.xwork2.ActionSupport;
import com.utils.Response;

/**
 * Created by pxc on 2018/4/24.
 */
public class BasicAction extends ActionSupport {

    public Response response;   //响应体

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
