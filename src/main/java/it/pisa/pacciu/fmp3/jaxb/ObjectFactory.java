/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 *
 * @author m.luly
 */
@XmlRegistry
public class ObjectFactory {
    private final static QName _FMp3History_QNAME = new QName("urn:it:pisa:pacciu:fmp3:history", "FMp3History");
    
    public ObjectFactory() {
    }
    public History createHistory(){
        return new History();
    }
    public History.FileLoaded createHistoryFileLoaded(){
        return new History.FileLoaded();
    }
    
    @XmlElementDecl(namespace = "urn:it:pisa:pacciu:fmp3:history", name = "FMp3History")
    public JAXBElement<History> createFMp3History(History value) {
        return new JAXBElement<History>(_FMp3History_QNAME, History.class, null, value);
    }
}
