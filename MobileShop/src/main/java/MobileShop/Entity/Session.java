package MobileShop.Entity;



import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sessions")
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String session_id;
	
	String ip;
	
	String user_agent;
	
	@JsonIgnore
	@OneToMany(mappedBy = "session")
	List<Cart> carts;

	public Session() {
		super();
	}

	public Session(String session_id, String ip, String user_agent) {
		super();
		this.session_id = session_id;
		this.ip = ip;
		this.user_agent = user_agent;
	}
}
