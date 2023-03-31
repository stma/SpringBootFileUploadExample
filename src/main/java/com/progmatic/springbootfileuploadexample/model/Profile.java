package com.progmatic.springbootfileuploadexample.model;

import jakarta.persistence.*;


@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private ProfilePic pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfilePic getPic() {
        return pic;
    }

    public void setPic(ProfilePic pic) {
        this.pic = pic;
    }
}
