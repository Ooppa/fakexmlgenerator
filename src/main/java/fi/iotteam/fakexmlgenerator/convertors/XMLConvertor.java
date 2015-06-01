/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iotteam.fakexmlgenerator.convertors;

import fi.iotteam.fakexmlgenerator.data.DataObject;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author ooppa
 */
public class XMLConvertor {

    public static String dataObjectToXML(DataObject obj) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(DataObject.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out for debugging
        StringWriter sb = new StringWriter();

        m.marshal(obj, sb);

        return sb.toString();
    }
}
