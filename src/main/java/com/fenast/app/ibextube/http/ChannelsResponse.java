package com.fenast.app.ibextube.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.spring.web.json.Json;

/**
 * Created by taddesee on 6/5/2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelsResponse {

    @ApiModelProperty(value = "Channel Name")
    private String channelName;
    
    @ApiModelProperty(value = "Description")
    private String description;

    public ChannelsResponse() {
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
