package com.example.xpns;

public class UserGroups {

    private String userID, groupName;
    private String[] groupMembers= new String[10];

    public UserGroups(){};

    public UserGroups(String userID, String groupName, String[] groupMembers) {

        this.userID = userID;
        this.groupName = groupName;
        this.groupMembers = groupMembers;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String splitID) {
        this.groupName = groupName;
    }

    public String[] getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(String[] groupMembers) {
        this.groupMembers = groupMembers;
    }

}
