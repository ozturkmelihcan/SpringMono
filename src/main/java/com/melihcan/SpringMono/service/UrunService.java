package com.melihcan.SpringMono.service;

import com.melihcan.SpringMono.repository.IUrunRepository;
import com.melihcan.SpringMono.repository.entity.Urun;
import com.melihcan.SpringMono.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UrunService extends ServiceManager<Urun,Long> {



    public UrunService(IUrunRepository repository){
        super(repository);
    }




}
