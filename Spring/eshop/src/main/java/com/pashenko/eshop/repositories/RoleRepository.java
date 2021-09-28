package com.pashenko.eshop.repositories;

import com.pashenko.eshop.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends CrudRepository<Role, Long> {
}
