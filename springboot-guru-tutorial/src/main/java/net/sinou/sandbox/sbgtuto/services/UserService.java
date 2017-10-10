package net.sinou.sandbox.sbgtuto.services;

import net.sinou.sandbox.sbgtuto.domain.User;

public interface UserService extends CRUDService<User> {
	User findByUsername(String username);
}
