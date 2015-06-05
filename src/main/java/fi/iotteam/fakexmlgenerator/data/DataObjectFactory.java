/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iotteam.fakexmlgenerator.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author ooppa
 */
public class DataObjectFactory {

    private static final String datasourceid = "source-";
    private static final String deviceid = "dev-";
    private static final String sensorid = "sensor-";
    private static final long startTime = System.currentTimeMillis();

    /**
     * Returns a randomly generated DataObject
     *
     * @return DataObject
     */
    public static DataObject getRandomDataObject() {
        DataObject obj = new DataObject();

        obj.setId(datasourceid+ UUID.randomUUID().toString());
        obj.setHeader(getHeader());
        obj.setDevices(new ArrayList<Device>());

        for(int i = 0; i<3; i++) {
            obj.getDevices().add(getDevice());
        }

        return obj;
    }

    private static Header getHeader() {
        Header header = new Header();

        header.setResponse("success");
        header.setUptime(startTime-System.currentTimeMillis());

        return header;
    }

    private static int deviceindex = 0;

    private static Device getDevice() {
        Device device = new Device();

        device.setId(deviceid+deviceindex++);
        device.setStatus(true);
        device.setSensors(new ArrayList<Sensor>());

        for(int i = 0; i<10; i++) {
            device.getSensors().add(getSensor());
        }

        return device;
    }

    private static int sensorindex = 0;

    private static Sensor getSensor() {
        Sensor sensor = new Sensor();
        sensor.setId(sensorid+sensorindex++);
        sensor.setReadouts(new ArrayList<Readout>());

        long currtime = System.currentTimeMillis();

        double previousValue = 22.2;
        
        // 864000 is one day, one readout each 10s
        for(int i = 0; i<8640; i++) {
            double value = randDouble(previousValue);
            previousValue = value;
            sensor.getReadouts().add(getReadout(currtime-(i*10), value));
        }

        return sensor;
    }

    private static Readout getReadout(long currentTime, double value) {
        Readout readout = new Readout();
        readout.setTime(currentTime);
        readout.setQuantity("Temperature");
        readout.setUnit("C");
        readout.setValue(round(value, 2));

        return readout;
    }

    private static double randDouble(double previous) {
        Random random = new Random();

        if(random.nextBoolean()) {
            return previous-random.nextDouble();
        } else {
            return previous+random.nextDouble();
        }
    }

    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
