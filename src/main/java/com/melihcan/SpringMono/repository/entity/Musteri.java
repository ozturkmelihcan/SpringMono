package com.melihcan.SpringMono.repository.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblmusteri")
public class Musteri extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String tckimlik;

    String ad;

    String adres;

    String telefon;

    Integer yas;

    boolean state;

}
