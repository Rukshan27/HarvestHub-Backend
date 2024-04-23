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
@Table(name = "BILL")
@SequenceGenerator(name = "ID_BILL_SEQUENCE", sequenceName = "ID_SEQ_BILL", allocationSize = 1)
public class Bill extends MBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_BILL_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_USER")
    private User user;

    @Column(name = "AMOUNT")
    private Double amount;

}
