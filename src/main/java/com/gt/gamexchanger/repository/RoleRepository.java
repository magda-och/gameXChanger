package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.enums.RoleEnum;
import com.gt.gamexchanger.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
