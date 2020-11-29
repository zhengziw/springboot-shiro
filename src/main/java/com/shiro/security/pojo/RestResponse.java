package com.shiro.security.pojo;

import lombok.Data;

/**
 * @author zzw
 * @version 1.0
 * @date 2020-11-29 15:20
 */
@Data
public class RestResponse {
    private String msg;
    private boolean success;
    private Object data;
    private Integer code;

    public RestResponse(String msg, boolean success, Object data, Integer code) {
        this.msg = msg;
        this.success = success;
        this.data = data;
        this.code = code;
    }

    public RestResponse(String msg, boolean success, Integer code) {
        this.msg = msg;
        this.success = success;
        this.code = code;
    }
}
