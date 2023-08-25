package com.crbcph.attendance.members.model.domain;

public class Member {

    private String profilePicture;
    private String name;
    private String status;
    private String nickname;
    private String remarks;

    // Default constructor
    public Member() {}

    // Parameterized constructor
    public Member(String profilePicture, String name, String status, String nickname, String remarks) {
        this.profilePicture = profilePicture;
        this.name = name;
        this.status = status;
        this.nickname = nickname;
        this.remarks = remarks;
    }

    // Getters and Setters

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Member{" +
                "profilePicture='" + profilePicture + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", nickname='" + nickname + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

