package com.zxy.entity;
//dependencies
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*Entity class*/
@Entity
@Table(name = "t_user") //sql table name
public class User {

    @Id
    @GeneratedValue
    private Long id; //user id
    private String nickname; //user nickname
    private String username; //username
    private String password; //account password
    private String email; //user email
    private String avatar; //user picture
    private Integer type; //user type (average or admin)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; //date when user profile was created
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; //date when user profile was updated

    //list of blogs posted by each user
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

    //class constructor
    public User() {
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
