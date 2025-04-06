package com.onsdachraoui.client.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String city;
    private String country;

    @Embedded
    private Coordinates coordinates;

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Coordinates {
        private Double latitude;
        private Double longitude;
    }

    @OneToMany(mappedBy = "adress")  // fait référence au champ 'adress' dans Client
    private List<Client> clients = new ArrayList<>();

}
