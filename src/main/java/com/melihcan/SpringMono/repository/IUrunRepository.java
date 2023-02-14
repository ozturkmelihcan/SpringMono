package com.melihcan.SpringMono.repository;

import com.melihcan.SpringMono.repository.entity.Urun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUrunRepository extends JpaRepository<Urun,Long> {

}
