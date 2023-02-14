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
@Table(name = "tblurun")
public class Urun extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String ad;

    Double fiyat;

    String barkod;
}
