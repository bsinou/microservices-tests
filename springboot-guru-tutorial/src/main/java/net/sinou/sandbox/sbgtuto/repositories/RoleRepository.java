package net.sinou.sandbox.sbgtuto.repositories;

import org.springframework.data.repository.CrudRepository;

import net.sinou.sandbox.sbgtuto.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}