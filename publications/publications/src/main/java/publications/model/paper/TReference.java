//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.04 at 03:46:07 AM CEST 
//


package publications.model.paper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="refAuthors" type="{https://github.com/ArpadVS/XML_tim14_2019-Publication}TRefAuthors"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="title">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="publisher">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="institution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="webUrl" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TReference", propOrder = {
    "refAuthorsAndYearAndTitle"
})
public class TReference {

    @XmlElementRefs({
        @XmlElementRef(name = "year", namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", type = JAXBElement.class),
        @XmlElementRef(name = "title", namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", type = JAXBElement.class),
        @XmlElementRef(name = "publisher", namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", type = JAXBElement.class),
        @XmlElementRef(name = "webUrl", namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", type = JAXBElement.class),
        @XmlElementRef(name = "refAuthors", namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> refAuthorsAndYearAndTitle;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "anyURI")
    protected String id;

    /**
     * Gets the value of the refAuthorsAndYearAndTitle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refAuthorsAndYearAndTitle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefAuthorsAndYearAndTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TReference.Publisher }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TRefAuthors }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getRefAuthorsAndYearAndTitle() {
        if (refAuthorsAndYearAndTitle == null) {
            refAuthorsAndYearAndTitle = new ArrayList<JAXBElement<?>>();
        }
        return this.refAuthorsAndYearAndTitle;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="institution" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "institution",
        "city"
    })
    public static class Publisher {

        @XmlElement(required = true)
        protected String institution;
        @XmlElement(required = true)
        protected String city;

        /**
         * Gets the value of the institution property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInstitution() {
            return institution;
        }

        /**
         * Sets the value of the institution property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInstitution(String value) {
            this.institution = value;
        }

        /**
         * Gets the value of the city property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCity() {
            return city;
        }

        /**
         * Sets the value of the city property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCity(String value) {
            this.city = value;
        }

    }

}
