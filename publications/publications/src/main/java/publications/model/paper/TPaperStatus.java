//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.01 at 01:24:02 AM CEST 
//


package publications.model.paper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPaperStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TPaperStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="toBeReviewed"/>
 *     &lt;enumeration value="inReviewProcess"/>
 *     &lt;enumeration value="reviewed"/>
 *     &lt;enumeration value="accepted"/>
 *     &lt;enumeration value="rejected"/>
 *     &lt;enumeration value="revisionNeeded"/>
 *     &lt;enumeration value="revisionDone"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TPaperStatus")
@XmlEnum
public enum TPaperStatus {

    @XmlEnumValue("toBeReviewed")
    TO_BE_REVIEWED("toBeReviewed"),
    @XmlEnumValue("inReviewProcess")
    IN_REVIEW_PROCESS("inReviewProcess"),
    @XmlEnumValue("reviewed")
    REVIEWED("reviewed"),
    @XmlEnumValue("accepted")
    ACCEPTED("accepted"),
    @XmlEnumValue("rejected")
    REJECTED("rejected"),
    @XmlEnumValue("revisionNeeded")
    REVISION_NEEDED("revisionNeeded"),
    @XmlEnumValue("revisionDone")
    REVISION_DONE("revisionDone");
    private final String value;

    TPaperStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TPaperStatus fromValue(String v) {
        for (TPaperStatus c: TPaperStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
