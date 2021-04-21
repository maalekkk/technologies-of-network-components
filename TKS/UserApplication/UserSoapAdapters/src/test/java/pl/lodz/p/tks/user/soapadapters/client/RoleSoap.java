
package pl.lodz.p.tks.user.soapadapters.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for roleSoap.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="roleSoap"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="Client"/&amp;gt;
 *     &amp;lt;enumeration value="Admin"/&amp;gt;
 *     &amp;lt;enumeration value="Owner"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "roleSoap")
@XmlEnum
public enum RoleSoap {

    @XmlEnumValue("Client")
    CLIENT("Client"),
    @XmlEnumValue("Admin")
    ADMIN("Admin"),
    @XmlEnumValue("Owner")
    OWNER("Owner");
    private final String value;

    RoleSoap(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RoleSoap fromValue(String v) {
        for (RoleSoap c: RoleSoap.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
