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
@Table(name = "BILL_ITEM")
@SequenceGenerator(name = "ID_BILL_ITEM_SEQUENCE", sequenceName = "ID_SEQ_BILL_ITEM", allocationSize = 1)
public class BillItem extends MBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_BILL_ITEM_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUCT")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "FK_BILL")
    private Bill bill;

    @Column(name = "QTY")
    private Long qty;

    @Column(name = "AMOUNT")
    private Double amount;

}
