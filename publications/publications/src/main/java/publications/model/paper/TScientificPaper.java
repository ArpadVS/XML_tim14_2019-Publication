//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.01 at 01:24:02 AM CEST 
//


package publications.model.paper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import publications.model.shared.TAuthor;


/**
 * <p>Java class for TScientificPaper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TScientificPaper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authors">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="author" type="{https://github.com/ArpadVS/XML_tim14_2019-Publication}TAuthor"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="abstract">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="paragraph">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{https://github.com/ArpadVS/XML_tim14_2019-Publication}keywords"/>
 *         &lt;element name="content">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element ref="{https://github.com/ArpadVS/XML_tim14_2019-Publication}chapter"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="references">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element ref="{https://github.com/ArpadVS/XML_tim14_2019-Publication}reference"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="recieved" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="revised" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="accepted" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="status" type="{https://github.com/ArpadVS/XML_tim14_2019-Publication}TPaperStatus" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TScientificPaper", propOrder = {
    "title",
    "authors",
    "_abstract",
    "keywords",
    "content",
    "references"
})
public class TScientificPaper {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected TScientificPaper.Authors authors;
    @XmlElement(name = "abstract", required = true)
    protected TScientificPaper.Abstract _abstract;
    @XmlElement(required = true)
    protected Keywords keywords;
    @XmlElement(required = true)
    protected TScientificPaper.Content content;
    @XmlElement(required = true)
    protected TScientificPaper.References references;
    @XmlAttribute(name = "recieved")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar recieved;
    @XmlAttribute(name = "revised")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar revised;
    @XmlAttribute(name = "accepted")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar accepted;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "anyURI")
    protected String id;
    @XmlAttribute(name = "language")
    protected String language;
    @XmlAttribute(name = "status")
    protected TPaperStatus status;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the authors property.
     * 
     * @return
     *     possible object is
     *     {@link TScientificPaper.Authors }
     *     
     */
    public TScientificPaper.Authors getAuthors() {
        return authors;
    }

    /**
     * Sets the value of the authors property.
     * 
     * @param value
     *     allowed object is
     *     {@link TScientificPaper.Authors }
     *     
     */
    public void setAuthors(TScientificPaper.Authors value) {
        this.authors = value;
    }

    /**
     * Gets the value of the abstract property.
     * 
     * @return
     *     possible object is
     *     {@link TScientificPaper.Abstract }
     *     
     */
    public TScientificPaper.Abstract getAbstract() {
        return _abstract;
    }

    /**
     * Sets the value of the abstract property.
     * 
     * @param value
     *     allowed object is
     *     {@link TScientificPaper.Abstract }
     *     
     */
    public void setAbstract(TScientificPaper.Abstract value) {
        this._abstract = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * @return
     *     possible object is
     *     {@link Keywords }
     *     
     */
    public Keywords getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of the keywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link Keywords }
     *     
     */
    public void setKeywords(Keywords value) {
        this.keywords = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link TScientificPaper.Content }
     *     
     */
    public TScientificPaper.Content getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link TScientificPaper.Content }
     *     
     */
    public void setContent(TScientificPaper.Content value) {
        this.content = value;
    }

    /**
     * Gets the value of the references property.
     * 
     * @return
     *     possible object is
     *     {@link TScientificPaper.References }
     *     
     */
    public TScientificPaper.References getReferences() {
        return references;
    }

    /**
     * Sets the value of the references property.
     * 
     * @param value
     *     allowed object is
     *     {@link TScientificPaper.References }
     *     
     */
    public void setReferences(TScientificPaper.References value) {
        this.references = value;
    }

    /**
     * Gets the value of the recieved property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecieved() {
        return recieved;
    }

    /**
     * Sets the value of the recieved property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecieved(XMLGregorianCalendar value) {
        this.recieved = value;
    }

    /**
     * Gets the value of the revised property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRevised() {
        return revised;
    }

    /**
     * Sets the value of the revised property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRevised(XMLGregorianCalendar value) {
        this.revised = value;
    }

    /**
     * Gets the value of the accepted property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAccepted() {
        return accepted;
    }

    /**
     * Sets the value of the accepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAccepted(XMLGregorianCalendar value) {
        this.accepted = value;
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
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link TPaperStatus }
     *     
     */
    public TPaperStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPaperStatus }
     *     
     */
    public void setStatus(TPaperStatus value) {
        this.status = value;
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
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="paragraph">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
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
        "paragraph"
    })
    public static class Abstract {

        @XmlElement(required = true)
        protected List<String> paragraph;

        /**
         * Gets the value of the paragraph property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the paragraph property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParagraph().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getParagraph() {
            if (paragraph == null) {
                paragraph = new ArrayList<String>();
            }
            return this.paragraph;
        }

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
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="author" type="{https://github.com/ArpadVS/XML_tim14_2019-Publication}TAuthor"/>
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
        "author"
    })
    public static class Authors {

        @XmlElement(required = true)
        protected List<TAuthor> author;

        /**
         * Gets the value of the author property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the author property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAuthor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TAuthor }
         * 
         * 
         */
        public List<TAuthor> getAuthor() {
            if (author == null) {
                author = new ArrayList<TAuthor>();
            }
            return this.author;
        }

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
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element ref="{https://github.com/ArpadVS/XML_tim14_2019-Publication}chapter"/>
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
        "chapter"
    })
    public static class Content {

        @XmlElement(required = true)
        protected List<Chapter> chapter;

        /**
         * Gets the value of the chapter property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chapter property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChapter().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Chapter }
         * 
         * 
         */
        public List<Chapter> getChapter() {
            if (chapter == null) {
                chapter = new ArrayList<Chapter>();
            }
            return this.chapter;
        }

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
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element ref="{https://github.com/ArpadVS/XML_tim14_2019-Publication}reference"/>
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
        "reference"
    })
    public static class References {

        @XmlElement(required = true)
        protected List<Reference> reference;

        /**
         * Gets the value of the reference property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reference property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReference().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Reference }
         * 
         * 
         */
        public List<Reference> getReference() {
            if (reference == null) {
                reference = new ArrayList<Reference>();
            }
            return this.reference;
        }

    }

}