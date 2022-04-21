package domein;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
})
@Table(name= "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String user_id;
	
	@Column
	private String role;
	
//	@Column
//	private JSONArray orderCat;
	
	private String email;
	
	public User(String email, String role/*, JSONArray cat*/) {
		this.email = email;
		this.role = role;
		//this.orderCat = cat;
	}

	public User(String email) {
		this.email = email;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getEmail() {
		return email;
	}
}
