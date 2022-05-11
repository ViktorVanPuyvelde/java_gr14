package repository;

import domein.User;

public class UserDaoJpa extends GenericDaoJpa<User> {

	public UserDaoJpa() {
		super(User.class);
	}
	
	
	
}
