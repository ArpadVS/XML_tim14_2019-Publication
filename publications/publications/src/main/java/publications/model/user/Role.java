package publications.model.user;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.springframework.security.core.GrantedAuthority;

@XmlEnum
@XmlType(name = "TRole")
public enum Role implements GrantedAuthority{

	@XmlEnumValue("ROLE_AUTHOR")
	ROLE_AUTHOR,
	@XmlEnumValue("ROLE_REVIEWER")
	ROLE_REVIEWER,
	@XmlEnumValue("ROLE_EDITOR")
	ROLE_EDITOR;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.name();
	}
	
	
}
