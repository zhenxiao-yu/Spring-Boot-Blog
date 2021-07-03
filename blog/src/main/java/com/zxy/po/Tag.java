package com.zxy.po;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_tag")//sql table name
public class Tag {

    @Id
    @GeneratedValue
    private Long id; //tag id
    private String name; //tage name

    @ManyToMany(mappedBy = "tags") //establish relationship with tags from Blog.java
    private List<Blog> blogs = new ArrayList<>(); // assign posts with many tags

    //constructors
    public Tag() {
    }

    //getters and setters
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

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    //to string method
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
