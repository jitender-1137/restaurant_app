package com.ms.restaurant.domains;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "business_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDetails extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = -2378033850673491228L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_owner_name")
    private String restaurantOwnerName;

    @Column(name = "restaurant_mobile_number")
    private String restaurantMobileNumber;

    @Column(name = "restaurant_email_id")
    private String restaurantEmailId;

    @Column(name = "gst_no")
    private String gstNo;

    @Column(name = "enabled")
    private Boolean enabled = true;

}
