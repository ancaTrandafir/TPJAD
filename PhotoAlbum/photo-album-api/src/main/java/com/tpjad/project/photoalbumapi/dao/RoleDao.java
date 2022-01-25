package com.tpjad.project.photoalbumapi.dao;

import com.tpjad.project.photoalbumapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
