package com.HRManager.g01.security.services;

import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role r){

        return roleRepository.save(r);
    }
}
