package domein;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name = "User.findRole", query = "SELECT u FROM User u WHERE u.user_id = :id")
})
@Table(name= "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String user_id;
	
	@Column
	private String role;
		
	@Transient
	private String email;
	@Transient
	private String name;
	
	protected User()
	{}
	
	public User(String email, String id, String name) {
		this.email = email;
		this.user_id = id;
		this.name = name;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getEmail() {
		return email;
	}
	
	public String getRole() {
		return role;
	}

	public final void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

}
