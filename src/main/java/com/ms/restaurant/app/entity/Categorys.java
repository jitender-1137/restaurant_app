package com.ms.restaurant.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Categorys {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "isActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "categorys", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("category")
    private List<Menus> menus;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnoreProperties("categories")
    private Restaurants restaurant;

}
