//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.01 at 03:08:58 PM CET 
//

package publications.model.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the rs.ac.uns.ftn.jaxb.example2 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _User_QNAME = new QName("http://www.ftn.uns.ac.rs/tim14", "user");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema
	 * derived classes for package: model.user
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link User }
	 * 
	 */
	public User createUser() {
		return new User();
	}

}
