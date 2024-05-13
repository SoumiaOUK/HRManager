package com.HRManager.g01.repositories;

import com.HRManager.g01.entities.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BonusRepository extends JpaRepository<Bonus,Long> {

//bonusRepository.getMyBonus(p.getIdPerson());
    //bonusRepository.getBonusByManager(p.getIdPerson());

    @Query(
            value = "select * from bonus where id_manager=?1",
            nativeQuery = true
    )
    List<Bonus> getBonusByManager(Long id);
    @Query(
            value = "select * from bonus where id_person=?1",
            nativeQuery = true
    )
    List<Bonus> getMyBonus(Long id);

}
