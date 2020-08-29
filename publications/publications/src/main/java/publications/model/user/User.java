package publications.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
@XmlType(name = "", propOrder = { "first_name", "last_name", "username", "email", "password", "biography", "role",
		"expertise" })
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlAttribute(name = "user_id", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	// @XmlSchemaType(name = "ID")
	private String user_id;
	@XmlElement(name = "first_name", required = true)
	private String first_name;
	@XmlElement(name = "last_name", required = true)
	private String last_name;
	@XmlElement(name = "username", required = true)
	private String username;
	@XmlElement(name = "email", required = true)
	private String email;
	@XmlElement(name = "password", required = true)
	private String password;
	@XmlElement(name = "biography", required = false)
	private String biography;
	@XmlElement(required = true)
	private List<Role> role;
	@XmlElement
	private List<String> expertise;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	

	public List<String> getExpertise() {
		if(this.expertise == null) {
			this.expertise = new ArrayList<>();
		}
		return expertise;
	}

	public void setExpertise(List<String> expertise) {
		this.expertise = expertise;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + ", password=" + password + ", biography=" + biography + ", roles=" + role + ", expertise="
				+ expertise + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		return this.role;
	}

	@Override
	public String getUsername() { 
		return this.username;
	}

	public List<Role> getRole() {
		if(this.role == null) {
			this.role = new ArrayList<>();
		}
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() { 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { 
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { 
		return true;
	}

	@Override
	public boolean isEnabled() { 
		return true;
	}

	

}
