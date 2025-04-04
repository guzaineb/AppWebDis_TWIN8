package com.onsdachraoui.client.entity;

import jakarta.persistence.*;
import lombok.*;
@ToString
@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    @Column(unique = true, nullable = false)
    private String email; // Ce champ doit être présent

    @Column(nullable = false)
    private String phone;
}