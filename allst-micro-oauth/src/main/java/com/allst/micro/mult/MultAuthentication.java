package com.allst.micro.mult;

import lombok.Data;

import java.util.Map;

/**
 * @author Hutu
 * @since 2022-08-30 下午 09:30
 */
@Data
public class MultAuthentication {

    private String authType;
    private String username;
    private Map<String, String[]> authParameters;

    public String getAuthParameter(String paramter) {
        String[] values = this.authParameters.get(paramter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }
}
