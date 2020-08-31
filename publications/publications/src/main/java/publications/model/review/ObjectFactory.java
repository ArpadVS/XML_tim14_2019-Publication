//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.01 at 01:05:27 AM CEST 
//


package publications.model.review;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import publications.model.shared.TAuthor;
import publications.model.shared.TDecorator;
import publications.model.shared.TLocation;
import publications.model.shared.TParagraph;
import publications.model.shared.TParagraphs;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the https.github_com.arpadvs.xml_tim14_2019_publication package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TDecoratorUnderline_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "underline");
    private final static QName _TDecoratorBold_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "bold");
    private final static QName _TDecoratorItalic_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "italic");
    private final static QName _TParagraphsParagraph_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "Paragraph");
    private final static QName _TParagraphRef_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "ref");
    private final static QName _TParagraphQuote_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "quote");
    private final static QName _TParagraphFormule_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "formule");
    private final static QName _TParagraphInternalRef_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "internal-ref");
    private final static QName _TParagraphList_QNAME = new QName("https://github.com/ArpadVS/XML_tim14_2019-Publication", "list");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.github_com.arpadvs.xml_tim14_2019_publication
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Review }
     * 
     */
    public Review createReview() {
        return new Review();
    }

    /**
     * Create an instance of {@link TParagraph }
     * 
     */
    public TParagraph createTParagraph() {
        return new TParagraph();
    }

    /**
     * Create an instance of {@link TAuthor }
     * 
     */
    public TAuthor createTAuthor() {
        return new TAuthor();
    }

    /**
     * Create an instance of {@link Review.Comments }
     * 
     */
    public Review.Comments createReviewComments() {
        return new Review.Comments();
    }

    /**
     * Create an instance of {@link TDecorator }
     * 
     */
    public TDecorator createTDecorator() {
        return new TDecorator();
    }

    /**
     * Create an instance of {@link TLocation }
     * 
     */
    public TLocation createTLocation() {
        return new TLocation();
    }

    /**
     * Create an instance of {@link TComment }
     * 
     */
    public TComment createTComment() {
        return new TComment();
    }

    /**
     * Create an instance of {@link TParagraphs }
     * 
     */
    public TParagraphs createTParagraphs() {
        return new TParagraphs();
    }

    /**
     * Create an instance of {@link TParagraph.List }
     * 
     */
    public TParagraph.List createTParagraphList() {
        return new TParagraph.List();
    }

    /**
     * Create an instance of {@link TParagraph.Quote }
     * 
     */
    public TParagraph.Quote createTParagraphQuote() {
        return new TParagraph.Quote();
    }

    /**
     * Create an instance of {@link TParagraph.Ref }
     * 
     */
    public TParagraph.Ref createTParagraphRef() {
        return new TParagraph.Ref();
    }

    /**
     * Create an instance of {@link TParagraph.InternalRef }
     * 
     */
    public TParagraph.InternalRef createTParagraphInternalRef() {
        return new TParagraph.InternalRef();
    }

    /**
     * Create an instance of {@link TAuthor.Institution }
     * 
     */
    public TAuthor.Institution createTAuthorInstitution() {
        return new TAuthor.Institution();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDecorator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "underline", scope = TDecorator.class)
    public JAXBElement<TDecorator> createTDecoratorUnderline(TDecorator value) {
        return new JAXBElement<TDecorator>(_TDecoratorUnderline_QNAME, TDecorator.class, TDecorator.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDecorator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "bold", scope = TDecorator.class)
    public JAXBElement<TDecorator> createTDecoratorBold(TDecorator value) {
        return new JAXBElement<TDecorator>(_TDecoratorBold_QNAME, TDecorator.class, TDecorator.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDecorator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "italic", scope = TDecorator.class)
    public JAXBElement<TDecorator> createTDecoratorItalic(TDecorator value) {
        return new JAXBElement<TDecorator>(_TDecoratorItalic_QNAME, TDecorator.class, TDecorator.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TParagraph }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "Paragraph", scope = TParagraphs.class)
    public JAXBElement<TParagraph> createTParagraphsParagraph(TParagraph value) {
        return new JAXBElement<TParagraph>(_TParagraphsParagraph_QNAME, TParagraph.class, TParagraphs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TParagraph.Ref }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "ref", scope = TParagraph.class)
    public JAXBElement<TParagraph.Ref> createTParagraphRef(TParagraph.Ref value) {
        return new JAXBElement<TParagraph.Ref>(_TParagraphRef_QNAME, TParagraph.Ref.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TParagraph.Quote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "quote", scope = TParagraph.class)
    public JAXBElement<TParagraph.Quote> createTParagraphQuote(TParagraph.Quote value) {
        return new JAXBElement<TParagraph.Quote>(_TParagraphQuote_QNAME, TParagraph.Quote.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDecorator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "underline", scope = TParagraph.class)
    public JAXBElement<TDecorator> createTParagraphUnderline(TDecorator value) {
        return new JAXBElement<TDecorator>(_TDecoratorUnderline_QNAME, TDecorator.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "formule", scope = TParagraph.class)
    public JAXBElement<String> createTParagraphFormule(String value) {
        return new JAXBElement<String>(_TParagraphFormule_QNAME, String.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDecorator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "bold", scope = TParagraph.class)
    public JAXBElement<TDecorator> createTParagraphBold(TDecorator value) {
        return new JAXBElement<TDecorator>(_TDecoratorBold_QNAME, TDecorator.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TParagraph.InternalRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "internal-ref", scope = TParagraph.class)
    public JAXBElement<TParagraph.InternalRef> createTParagraphInternalRef(TParagraph.InternalRef value) {
        return new JAXBElement<TParagraph.InternalRef>(_TParagraphInternalRef_QNAME, TParagraph.InternalRef.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TParagraph.List }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "list", scope = TParagraph.class)
    public JAXBElement<TParagraph.List> createTParagraphList(TParagraph.List value) {
        return new JAXBElement<TParagraph.List>(_TParagraphList_QNAME, TParagraph.List.class, TParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDecorator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/ArpadVS/XML_tim14_2019-Publication", name = "italic", scope = TParagraph.class)
    public JAXBElement<TDecorator> createTParagraphItalic(TDecorator value) {
        return new JAXBElement<TDecorator>(_TDecoratorItalic_QNAME, TDecorator.class, TParagraph.class, value);
    }

}
