package com.ms.restaurant.domains;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Getter
@Setter
public class BusinessAddress extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "line_one")
    private String lineOne;

    @Column(name = "line_two")
    private String lineTwo;

	@Column(name = "city")
	private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "landmark")
    private String landmark;

	@Column(name = "pin_code")
	private String pinCode;

    @Column(name = "type")
    private String type;

}
