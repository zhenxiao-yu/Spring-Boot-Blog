package com.zxy.entity;
//dependencies
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*Entity class*/
@Entity
@Table(name = "t_tag") //sql table name
public class Tag {

    @Id
    @GeneratedValue
    private Long id; //tag id
    private String name; //tag name

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();

    //class constructor
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

    //toString method
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
