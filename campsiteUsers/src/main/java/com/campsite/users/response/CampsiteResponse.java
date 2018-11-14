package com.campsite.users.response;

import java.util.HashMap;
import java.util.Map;

public class CampsiteResponse {
    private Boolean error;
    private String description;
    private Map<String, Object> campsiteBody;

    public CampsiteResponse(Boolean error, String description, Object campsiteBody) {
        this.error = error;
        this.description = description;
        this.campsiteBody = new HashMap<>();
        if(campsiteBody != null)
            this.campsiteBody.put(campsiteBody.getClass().getSimpleName(),campsiteBody);
    }

    public CampsiteResponse() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getCampsiteBody() {
        return campsiteBody;
    }

    public void setCampsiteBody(Map<String, Object> campsiteBody) {
        this.campsiteBody = campsiteBody;
    }
}
