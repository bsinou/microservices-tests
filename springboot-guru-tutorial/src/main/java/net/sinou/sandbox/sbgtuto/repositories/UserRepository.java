package net.sinou.sandbox.sbgtuto.repositories;

import org.springframework.data.repository.CrudRepository;

import net.sinou.sandbox.sbgtuto.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
}