package com.zxy.po;
//dependencies
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*Entity class*/
@Entity
@Table(name = "t_type") //sql table name
public class Type {

    @Id
    @GeneratedValue
    private Long id; //category id
    @NotBlank(message = "Category cannot be empty!")
    private String name; //category name

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    //class constructor
    public Type() {
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

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
