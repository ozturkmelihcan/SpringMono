package com.melihcan.SpringMono.repository.view;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewMusteri {

    Long id;

    String ad;


}
