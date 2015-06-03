package fi.iotteam.fakexmlgenerator.controllers;

import fi.iotteam.fakexmlgenerator.convertors.XMLConvertor;
import fi.iotteam.fakexmlgenerator.data.DataObject;
import fi.iotteam.fakexmlgenerator.data.DataObjectFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ooppa
 */
@RestController
public class IndexController {

    @RequestMapping(value="/", produces = "text/xml; charset=utf-8")
    @ResponseBody
    public String index( Model model ) {
        try {
            DataObject data = DataObjectFactory.getRandomDataObject();

            return XMLConvertor.dataObjectToXML(data);
        } catch (JAXBException ex) {
            return "ERROR:\n"+ ex.toString();
        }
    }
}
