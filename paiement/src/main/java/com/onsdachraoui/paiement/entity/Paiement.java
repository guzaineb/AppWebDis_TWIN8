package com.onsdachraoui.paiement.entity;

import jakarta.persistence.*;
import lombok.*;
@ToString
@Entity
@Table(name = "paiements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;

    private String statut;

    private float montant;

}
