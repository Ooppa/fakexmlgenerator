/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iotteam.fakexmlgenerator.data;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author ooppa
 */
public class DataObjectFactory {
    public static DataObject getRandomDataObject(){
        DataObject obj = new DataObject();
        Header header = new Header();
        
        header.setResponse("success");
        header.setUptime(randInt(1000, 10000));
        
        obj.setHeader(header);
        obj.setDevices(new ArrayList<Device>());
        Device device = new Device();
        
        device.setId(uuid());
        device.setStatus(true);
        device.setSensors(new ArrayList<Sensor>());
        
        obj.getDevices().add(device);
        
        Sensor sensor = new Sensor();
        sensor.setId(uuid());
        sensor.setReadouts(new ArrayList<Readout>());
        
        long currtime = System.currentTimeMillis();
        
        
        Readout first = new Readout();
        first.setTime(""+ (currtime-1));
        first.setType("C");
        first.setValue(22.2);
        
        Readout second = new Readout();
        second.setTime(""+ (currtime-2));
        second.setType("C");
        second.setValue(23.2);
        
        sensor.getReadouts().add(first);
        sensor.getReadouts().add(second);
        device.getSensors().add(sensor);
        
        return obj;
    }
    
    private static double randDouble(double min, double max){
        Random random = new Random();
        
        return 0.0;
    }
    
    private static int randInt(int min, int max){
        Random random = new Random();
        
        return random.nextInt(max) + min;
    }
    
    private static String uuid(){
        return UUID.randomUUID().toString();
    }
}
