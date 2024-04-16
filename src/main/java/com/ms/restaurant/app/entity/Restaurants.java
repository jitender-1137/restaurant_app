package com.ms.restaurant.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "isActive")
    private boolean isActive = true;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnoreProperties("restaurants")
//    private Users user;

    @JsonIgnoreProperties("restaurant")
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Categorys> categories;
}
