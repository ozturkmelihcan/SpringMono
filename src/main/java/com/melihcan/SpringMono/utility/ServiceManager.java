package com.melihcan.SpringMono.utility;

import com.melihcan.SpringMono.repository.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Tüm servisler için Spring DataJPA kullanımı için kalıp servis yapısı
 *
 * @param <T> Entity
 * @param <ID> Entity ye ait @id ile işaretlenmiş değerin datatype ı
 */
public class ServiceManager<T extends BaseEntity,ID> implements IService<T,ID>{

    private final JpaRepository<T,ID> repository;

    public ServiceManager(JpaRepository<T,ID>repository){
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        t.setCreatedate(System.currentTimeMillis());
        t.setUpdatedate(System.currentTimeMillis());
        t.setStage(true);
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(x->{
            x.setCreatedate(System.currentTimeMillis());
            x.setUpdatedate(System.currentTimeMillis());
            x.setStage(true);
        });
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {
        t.setUpdatedate(System.currentTimeMillis());
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
}
