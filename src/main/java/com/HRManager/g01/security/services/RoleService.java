package com.HRManager.g01.security.services;

import com.HRManager.g01.security.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public List<Role> getRoles();

}
