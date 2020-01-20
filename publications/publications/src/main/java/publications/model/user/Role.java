package publications.model.user;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "TRole")
public enum Role {

	@XmlEnumValue("author")
	AUTHOR,
	@XmlEnumValue("reviewer")
	REVIEWER,
	@XmlEnumValue("editor")
	EDITOR;
	
	
}
