package com.harvest.hub.entity;

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
@Table(name = "STOCK")
@SequenceGenerator(name = "ID_STOCK_SEQUENCE", sequenceName = "ID_SEQ_STOCK", allocationSize = 1)
public class Stock extends MBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_STOCK_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUCT", nullable = false)
    private Product product;

    @Column(name = "STOCK_LIMIT")
    private Double stock;

    @Column(name = "ALERT_LIMIT")
    private Double limit;

}
