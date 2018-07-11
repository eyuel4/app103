package com.fenast.app.ibextube.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by taddesee on 6/5/2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailResponse extends ResponseMessageBase {
    @ApiModelProperty(value = "UserId email or Phone")
    private int userId;

    @ApiModelProperty(value = "First Name")
    private String firstName;

    @ApiModelProperty(value = "Last Name")
    private String lastName;

    @ApiModelProperty(value = "Middle Name")
    private String middleName;

    @ApiModelProperty(value = "Photo url")
    private String photoUrl;

    @ApiModelProperty(value = "mysubscription")
    private ChannelsResponse mySubscription;

    @ApiModelProperty(value = "isAcctActivated")
    private boolean isAcctActivated;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ChannelsResponse getMySubscription() {
        return mySubscription;
    }

    public void setMySubscription(ChannelsResponse mySubscription) {
        this.mySubscription = mySubscription;
    }

    public boolean isAcctActivated() {
        return isAcctActivated;
    }

    public void setAcctActivated(boolean acctActivated) {
        isAcctActivated = acctActivated;
    }

    @Override
    public String toString() {
        return "UserDetailResponse{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", mySubscription=" + mySubscription +
                ", isAcctActivated=" + isAcctActivated +
                '}';
    }
}
