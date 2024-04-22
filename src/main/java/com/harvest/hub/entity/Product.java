package com.harvest.hub.entity;

import com.harvest.hub.constant.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(name = "ID_PRODUCT_SEQUENCE", sequenceName = "ID_SEQ_PRODUCT", allocationSize = 1)
public class Product extends MBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PRODUCT_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "DISCOUNT_PERCENTAGE")
    private Double discount;

    @Column(name = "PHOTO", columnDefinition = "LONGTEXT")
    private String photo;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private ProductType productType;

}
