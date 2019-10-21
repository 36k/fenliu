package com.hlau.fenliu.repository;
import com.hlau.fenliu.entity.Aspiration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AspirationRepository extends JpaRepository<Aspiration,Integer> {
}
