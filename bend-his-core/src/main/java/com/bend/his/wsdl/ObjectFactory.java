//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.3.0 生成的
// 请访问 <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.09.05 时间 08:01:00 AM CST 
//


package com.bend.his.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bend.his.wsdl package. 
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

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bend.his.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HISInterface }
     * 
     */
    public HISInterface createHISInterface() {
        return new HISInterface();
    }

    /**
     * Create an instance of {@link HISInterfaceResponse }
     * 
     */
    public HISInterfaceResponse createHISInterfaceResponse() {
        return new HISInterfaceResponse();
    }

    /**
     * Create an instance of {@link HIPMessageServer }
     * 
     */
    public HIPMessageServer createHIPMessageServer() {
        return new HIPMessageServer();
    }

    /**
     * Create an instance of {@link HIPMessageServerResponse }
     * 
     */
    public HIPMessageServerResponse createHIPMessageServerResponse() {
        return new HIPMessageServerResponse();
    }

    /**
     * Create an instance of {@link TCMHIS05 }
     * 
     */
    public TCMHIS05 createTCMHIS05() {
        return new TCMHIS05();
    }

    /**
     * Create an instance of {@link TCMHIS05Response }
     * 
     */
    public TCMHIS05Response createTCMHIS05Response() {
        return new TCMHIS05Response();
    }

    /**
     * Create an instance of {@link TCMHIS06 }
     * 
     */
    public TCMHIS06 createTCMHIS06() {
        return new TCMHIS06();
    }

    /**
     * Create an instance of {@link TCMHIS06Response }
     * 
     */
    public TCMHIS06Response createTCMHIS06Response() {
        return new TCMHIS06Response();
    }

    /**
     * Create an instance of {@link TCMAE04 }
     * 
     */
    public TCMAE04 createTCMAE04() {
        return new TCMAE04();
    }

    /**
     * Create an instance of {@link TCMAE04Response }
     * 
     */
    public TCMAE04Response createTCMAE04Response() {
        return new TCMAE04Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
