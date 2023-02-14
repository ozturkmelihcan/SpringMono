package com.melihcan.SpringMono.service;

import com.melihcan.SpringMono.repository.ISatisRepository;
import com.melihcan.SpringMono.repository.entity.Satis;
import com.melihcan.SpringMono.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class SatisService extends ServiceManager<Satis,Long> {

    private final ISatisRepository repository;

    public SatisService(ISatisRepository repository){
        super(repository);
        this.repository = repository;
    }
}
