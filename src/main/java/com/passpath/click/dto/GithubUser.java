package com.passpath.click.dto;

public class GithubUser {
    private String name;
    private Long id;   //怕將來github用戶爆增，id長度不夠用
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
