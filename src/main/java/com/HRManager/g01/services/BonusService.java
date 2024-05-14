package com.HRManager.g01.services;

import com.HRManager.g01.entities.Bonus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BonusService {
    List<Bonus> getAllBonusesByMan();
    List<Bonus> getMyBonuses();
    List<Bonus> getBonusByEmp(Long id);
    Bonus save(Bonus bonus);
    void cancelBonus(Long id);

}
