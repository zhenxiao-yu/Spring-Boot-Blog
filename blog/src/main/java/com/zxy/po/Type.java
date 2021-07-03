package com.zxy.po;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity //Entity Class
@Table(name = "t_blog") //sql table name
public class Type {

    @Id
    @GeneratedValue
    private Long id; //tag id
    private String name; //tag name

    @OneToMany(mappedBy="type") //establish relationship with type from Blog.java
    private List<Blog> blogs = new ArrayList<>(); // assign one post with many types

    //constructor
    public Type(){

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

    //to string method
    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}