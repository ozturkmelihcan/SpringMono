package com.melihcan.SpringMono.repository;

/*
Spring BeanFactory ile olusturacagı nesnelerı belırlemek ıcın bellı anotasyonları arar.
Bunlardan 1.si veritabanı ıslemlerı ıcın
@Repository dir.
 */

import com.melihcan.SpringMono.repository.entity.Musteri;
import com.melihcan.SpringMono.repository.view.ViewMusteri;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMusteriRepository extends JpaRepository<Musteri,Long> {

    /**
     * !! Dikkat !!
     * Spring in devraldığı repository interface lerinde, method tanımlamaları için özel
     * keywordler kullanılmaktadır. bunlar üzerinden sorgular oluşturulur.
     * 1- ReturnType -> Musteri, List<Musteri>, Boolean, Integer vs.
     * 2- find, kelimesini kullanıyoruz.
     * 3- By, ne için arama yapılacağının belirlenmesi işaretlenmesi için kullanılır.
     * 4- Entity Property[entity-> değişkenin adı], üzerinde çalıştığınız repositorynın kullanmakta
     * oldugu entıty sınıfından bır degıskenın bırebır adını yazmalısınız.
     * ÇOOOOOOK DİKKAT !! burada varlık adı yazılırken buyuk harf ile baslanır. yine dikkat edilmesi
     * gereken bir husus eğer değişken adı camelcase seklinde yazılmıs ıse buna dıkkat ederek yazılmalıdır.
     * 5- Mehtod için gereklı parametreler degısken turune gore eklenır.
     * DİKKAT !!! Değişken adı önemli değil değişken türü önemlidir.
     */
    List<Musteri> findByAd(String ad);
    List<Musteri> findByAdAndAdres(String ad,String adres);


    /**
     * Hangi yas grubunun hangı urunlerı daha fazla satın aldıgını merak edıyorsunuz.
     * örn: 40 yaş üzeri müşterilerin listesi.
     */

    List<Musteri> findAllByYasGreaterThan(Integer yas); // yas > ?

    List<Musteri> findAllByYasGreaterThanEqual(Integer yas); // yas >= ?

    /**
     * Belli bir harfın ya da kelımenın aranması LIKE, ILIKE
     *
     */

    List<Musteri> findAllByAdLike(String ad);

    List<Musteri> findAllByAdStartingWith(String ad);

    /**
     * select * from tblmusteri where ad=? and adres=? and telefon=?
     * @param ad
     * @param adres
     * @param telefon
     * @return
     */

    Musteri findByAdAndAdresAndTelefon(String ad,String adres,String telefon);

    /**
     * select * from tblmusteri where yas = 5000
     * Eğer sonuc yok ise, null dönebilir böyle durumlarda null kontrolü yapmak çokta doğru değildir.
     * Bunun yerine Optional kullanmak doğru olacaktır.
     */
    Optional<Musteri> findOptionalByTelefon(String telefon);  // Eğer null ise empty

    Optional<List<Musteri>> findAllOptionalByAdres(String adres);

    /**
     * Order ->
     * -> ASC A...Z, 0...9
     * -> DESC Z...A, 9...0
     * En yaslı müsteri kim?
     * select * from tblmusteri order by yas desc limit 1
     */
    List<Musteri> findByOrderByYas(); // yasına göre kucukten buyuge dogry sıralı liste verir.

    List<Musteri> findByOrderByYasDesc(); // yaşına göre büyükten küçüğe doğru sıralı liste verir.

    Musteri findTopByOrderByYasDesc();

    /**
     * LIMIT kullanımı
     */
    List<Musteri> findTopOptionalByOrderByYasDesc(); // En yaşlı müşteri optional olarak

    /**
     * select * from tblmusteri where yas>18 and yas<40 -- genclerde satıs oranı
     * // başlangıc ve bıtıs degerlerını de dahıl ettıgını unutmayın.
     */
    List<Musteri> findAllByYasBetween(Integer start,Integer end);  // yas>=? and yas<=? [bas,bit]

    List<Musteri> findAllByAdresAndYasBetween(String adres,Integer start,Integer end);

    /**
     *  true-false
     */
    List<Musteri> findAllByState(boolean state); // aktif pasif kayıtları verir.

    List<Musteri> findAllByStateTrue();  // true - aktif olan kayıtlar

    List<Musteri> findAllByStateFalse();

    List<Musteri> findAllByAdIgnoreCaseAndAdresIgnoreCase(String ad,String adres);

    /**
     * Belli isimlerin listesini çekelim.
     * List<String> adListesi = {"Ahmet","Ayşe","Canan"}
     */
    List<Musteri> findAllByAdIn(List<String> adListesi);

    /**
     * HQL KULLANIMI
     * JPQL KULLANIMI
     * @param adresOlabilir
     * @return
     */
    @Query("select m from Musteri m where m.adres= ?1")
    List<Musteri> musterilerinTumunuAdreslerineGoreBul(String adresOlabilir);

    @Query("select m from Musteri m where m.ad = ?3 and m.adres= ?1 and m.telefon = ?2")
    Musteri musteriyiBul(String adres,String telefon,String ad);

    /**
     * NATIVESQL KULLANIMI
     * @param ad
     * @return
     */
    @Query(value = "select * from Musteri where ad =?1",nativeQuery = true)
    List<Musteri> bulAdinaGore(String ad);

    /**
     * SORGU PARAMETRELERININ KULLANILARAK TANIMLANMASI VE METHOD ICINDEN CEKILMESI.
     */

    @Query("select m from Musteri m where m.ad = :ad and m.adres = :adres")
    List<Musteri> findAdAndAdres(
            @Param("ad") String musteriadi,
            @Param("adres") String musteriadresi);

    @Query("select count (m)>0 from Musteri m where m.tckimlik=?1")
    Boolean musteriVarmi(String tckimlik);

    @Query("select new com.melihcan.SpringMono.repository.view.ViewMusteri(m.id,m.ad) from Musteri m")
    List<ViewMusteri> findAllViewMusteri();


}
