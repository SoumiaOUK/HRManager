package com.HRManager.g01.services;
import com.HRManager.g01.entities.Bonus;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.BonusRepository;
import com.HRManager.g01.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BonusServiceImp implements BonusService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    BonusRepository bonusRepository;
    @Override
    public List<Bonus> getAllBonusesByMan() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("\n LEAVEREQSERVICEIMP    connected personne id"+p.getIdPerson());
        List<Bonus> bonuses = bonusRepository.getBonusByManager(p.getIdPerson());
        System.out.println("\n BonusServiceImp   :   getAllBonusesByMan\n ");
        bonuses.forEach(System.out::println);
        return bonuses;
    }

    @Override
    public List<Bonus> getMyBonuses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
        List<Bonus> bonuses = bonusRepository.getMyBonus(p.getIdPerson());
        System.out.println("\n BonusServiceImp   :   getMyBonuses\n ");
        bonuses.forEach(System.out::println);
        return bonuses;
    }

    @Override
    public List<Bonus> getBonusByEmp(Long id) {
        return bonusRepository.getMyBonus(id);
    }

    @Override
    public void cancelBonus(Long id) {}
}
