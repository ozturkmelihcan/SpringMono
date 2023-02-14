package com.melihcan.SpringMono.service;


import com.melihcan.SpringMono.repository.IMusteriRepository;
import com.melihcan.SpringMono.repository.entity.Musteri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusteriService {

    /**
     * int sayi = 5;  Değişken tanımlama ve degerı degıskene atama ıslemı yapıyoruz.
     * Urun urun;
     * urun = new Urun(); // yeni nesne olusturulur ve referansı degıskene atanır.
     * heap alanında bellı bır alan ayrılmıs ve adreslenmıs demektır.
     */

    /**
     * Repositorynin enjekte edilme yöntemleri
     * 1- Field Injection -> @Autowired
     */
//    @Autowired
//    private IMusteriRepository repository;

    /**
     * 2- Constructor Injection
     *
     */
    private final IMusteriRepository repository;

    public MusteriService(IMusteriRepository repository){
        this.repository = repository;

    }

    public void save(Musteri entity){
        repository.save(entity);
    }

    public void saveAll(List<Musteri>musteriList){
        repository.saveAll(musteriList);
    }

    /*
    update için özellikle bir update komutu yoktur. Eğer bir Entity içinde ID bilgisi bulunuyor ise, ilgili
    id kaydı için entity içindeki yeni değerlerle değistirilir.
     */
    public void update(Musteri musteri){
        repository.save(musteri);
    }

    public void delete(Musteri musteri){
        repository.delete(musteri);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Musteri> findAll(){
        return repository.findAll();
    }

    public Optional<Musteri> findById(Long id){
        return repository.findById(id);
    }

    public boolean isExist(Long id){
        return repository.existsById(id);
    }

    public List<Musteri> adinaGoreGetir(String musteriadi){
        return repository.findByAd(musteriadi);
    }

    public List<Musteri> adVeAdreseGoreGetir(String ad,String adres){
        return repository.findByAdAndAdres(ad,adres);
    }

    public List<Musteri> findAllByAdLike(String ad){
        return repository.findAllByAdLike(ad);
    }
}
