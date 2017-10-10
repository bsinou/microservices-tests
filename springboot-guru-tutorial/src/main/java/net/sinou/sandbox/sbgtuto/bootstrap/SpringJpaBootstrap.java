package net.sinou.sandbox.sbgtuto.bootstrap;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import net.sinou.sandbox.sbgtuto.domain.Role;
import net.sinou.sandbox.sbgtuto.domain.User;
import net.sinou.sandbox.sbgtuto.services.RoleService;
import net.sinou.sandbox.sbgtuto.services.UserService;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	private Logger log = Logger.getLogger(SpringJpaBootstrap.class);

	private UserService userService;
	private RoleService roleService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadUsers();
		loadRoles();
		assignUsersToUserRole();
		assignUsersToAdminRole();
	}

	private void loadUsers() {
		User user1 = new User();
		user1.setUsername("demo");
		user1.setPassword("demo");
		userService.saveOrUpdate(user1);

		User user2 = new User();
		user2.setUsername("admin");
		user2.setPassword("secret");
		userService.saveOrUpdate(user2);

	}

	private void loadRoles() {
		Role role = new Role();
		role.setRole("USER");
		roleService.saveOrUpdate(role);
		log.info("Saved role " + role.getRole());
		Role adminRole = new Role();
		adminRole.setRole("ADMIN");
		roleService.saveOrUpdate(adminRole);
		log.info("Saved role " + adminRole.getRole());
	}

	private void assignUsersToUserRole() {
		List<Role> roles = (List<Role>) roleService.listAll();
		List<User> users = (List<User>) userService.listAll();

		roles.forEach(role -> {
			if (role.getRole().equalsIgnoreCase("USER")) {
				users.forEach(user -> {
					if (user.getUsername().equals("demo")) {
						user.addRole(role);
						userService.saveOrUpdate(user);
					}
				});
			}
		});
	}

	private void assignUsersToAdminRole() {
		List<Role> roles = (List<Role>) roleService.listAll();
		List<User> users = (List<User>) userService.listAll();

		roles.forEach(role -> {
			if (role.getRole().equalsIgnoreCase("ADMIN")) {
				users.forEach(user -> {
					if (user.getUsername().equals("admin")) {
						user.addRole(role);
						userService.saveOrUpdate(user);
					}
				});
			}
		});
	}
}
