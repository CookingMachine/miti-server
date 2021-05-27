package com.miti.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.data.enums.Kitchen;
import com.miti.data.enums.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RESTAURANT")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"metroStation"})
public class Restaurant {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Size(max = 100)
  @Column(name = "TITLE")
  private String title;

  @Column(name = "KITCHEN")
  @Enumerated(EnumType.STRING)
  @ElementCollection(targetClass = Kitchen.class)
  @JoinTable(
      name = "RESTAURANT_KITCHENS",
      joinColumns = @JoinColumn(name = "RESTAURANT_ID")
  )
  private List<Kitchen> kitchen;

  @Column(name = "DESTINATION")
  private String destination;

  // TODO: обсудить реализацию
  @Column(name = "METRO_STATION")
  private String metroStation;

  @Size(max = 50)
  @Column(name = "AVERAGE_BILL")
  private String averageBill;

  @Column(name = "TAG")
  @Enumerated(EnumType.STRING)
  @ElementCollection(targetClass = Tag.class)
  @JoinTable(
      name = "RESTAURANT_TAGS",
      joinColumns = @JoinColumn(name = "RESTAURANT_ID")
  )
  private List<Tag> tags;

  @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
  private List<RestaurantRating> rating;

  @ManyToMany
  @JoinTable(
      name = "restaurant_recipe",
      joinColumns = @JoinColumn(name = "restaurant_id"),
      inverseJoinColumns = @JoinColumn(name = "recipe_id"))
  private List<Recipe> recipes;
}