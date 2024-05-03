package com.HRManager.g01.security.repositories;

import com.HRManager.g01.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
}
