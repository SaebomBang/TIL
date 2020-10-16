package com.example.practice13;

public class Member {
    String name, phone;
    String birth;

    public Member(String name, String birth, String phone) {
        this.name = name;
        this.birth = birth;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
