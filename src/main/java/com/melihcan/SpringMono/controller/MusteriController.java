package com.melihcan.SpringMono.controller;

import com.melihcan.SpringMono.repository.IMusteriRepository;
import com.melihcan.SpringMono.repository.entity.Musteri;
import com.melihcan.SpringMono.service.MusteriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*
Uygulama ayaga kalktıgı andan ıtıbaren bır ip ve port üzerinde yayın yapar.
localhost:80 -> localhost
Uygulamanızın alt kırılımlarına yön vermek ve belli Class lara yönlendirme yapmak için
Mapping yaparız. bunun için @RequestMapping anotasyonu kullanılır.
http://localhost/musteri

 */
@RestController
@RequestMapping("/musteri")
public class MusteriController {

    @Autowired
    MusteriService musteriService;

    /*
    http://localhost/musteri/save
    Get -> bir sayfaya erişme ve ondan bilgi alma isteğidir. özel bir gereksinimi yoktur.
    Browser
     */

    @GetMapping("/save")
    public void save(String ad, String adres, String telefon,Integer yas) {
        Musteri musteri = Musteri.builder()
                .ad(ad)
                .adres(adres)
                .telefon(telefon)
                .yas(yas)
                .build();
        musteriService.save(musteri);
    }

    /*
    ResponseEntity -> Pojo JsonObject olarak Return type kullan.
    localhost/musteri/findall
    @return
     */
    @GetMapping("/findall")
    public ResponseEntity<List<Musteri>> findAll() {
        return ResponseEntity.ok(musteriService.findAll());
    }

    @GetMapping("/findbyad")
    public ResponseEntity<List<Musteri>> findByAd(String ad, String adres) {
        if (adres == null)
            return ResponseEntity.ok(musteriService.adinaGoreGetir(ad));
        return ResponseEntity.ok(musteriService.adVeAdreseGoreGetir(ad, adres));
    }

    @GetMapping("/findAllByAdLike")
    public ResponseEntity<List<Musteri>> findAllByAdLike(String ad){
        return ResponseEntity.ok(musteriService.findAllByAdLike(ad+"%"));
    }


}
