package com.onsdachraoui.client.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Entity
@Table(name = "adresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String houseNumber;

    @Column(nullable = false)
    private String zipCode;
}
