package com.miti.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.miti.data.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USR")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"recipeList", "commentList", "favouriteList", "rating"})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME", unique = true)
  private String username;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PASSWORD")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "STATUS")
  private Boolean status;

  @Enumerated
  @Column(name = "ROLE")
  private Role role;

  @Column(name = "REGISTRATION_DATE")
  private Date registrationDate;

  @Column(name = "LAST_AUTH_DATE")
  private Date lastAuthDate;

  @OneToMany(mappedBy = "author")
  private List<Recipe> recipeList;

  @OneToMany(mappedBy = "commentator")
  private List<Comment> commentList;

  @ManyToMany(mappedBy = "favouriteUsers")
  private List<Recipe> favouriteList;

  @OneToMany(mappedBy = "user")
  private List<Rating> rating;

  public User(String username, String name, String password, String email, Role role) {
    this.username = username;
    this.name = name;
    this.password = password;
    this.email = email;
    this.status = true;
    this.role = role;

    this.registrationDate = new Date();
    this.lastAuthDate = new Date();
  }
}
