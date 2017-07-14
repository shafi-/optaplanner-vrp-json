package com.mash;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by shafi on 7/11/17.
 */
public class Utility {
    public static String filePath = "/home/shafi/Projects/Java/optaplanner-distribution-7.0.0.Final/examples/sources/data/vehiclerouting/unsolved/";
    public static void jsonToXML(JSONObject json){
        JSONArray drivers = json.getJSONArray("driver");
        int num_drivers = drivers.length();
        JSONArray orders = json.getJSONArray("order");
        int num_order = orders.length();

        //Create Document
        int idx = 1;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            org.w3c.dom.Document document = docBuilder.newDocument();
            Element rootElement = document.createElement("VrpVehicleRoutingSolution");
            document.appendChild(rootElement);
            Attr attr = document.createAttribute("id");
            attr.setValue(String.valueOf(idx)); idx++;
            rootElement.setAttributeNode(attr);
            //create 1st order childs
            Element id = document.createElement("id");
            Element name = document.createElement("name");
            Element distanceType = document.createElement("distanceType");
            Element distanceUnit = document.createElement("distanceUnitOfMeasurement");

            Element locationList = document.createElement("locationList");
            Element depotList = document.createElement("depotList");
            Element vehicleList = document.createElement("vehicleList");
            Element customerList = document.createElement("customerList");

            //setValue
            id.appendChild(document.createTextNode(String.valueOf(0)));
            name.appendChild(document.createTextNode("A-n33-k6"));
            distanceType.appendChild(document.createTextNode("AIR_DISTANCE"));
            distanceUnit.appendChild(document.createTextNode("distance"));
            Attr rootId = document.createAttribute("id");
            rootId.setValue(String.valueOf(idx++));
            Attr locationListId = document.createAttribute("id");
            locationListId.setValue(String.valueOf(idx++));
            Attr depotListId = document.createAttribute("id");
            depotListId.setValue(String.valueOf(idx++));
            Attr vehicleListId = document.createAttribute("id");
            vehicleListId.setValue(String.valueOf(idx++));
            Attr customerListId = document.createAttribute("id");
            customerListId.setValue(String.valueOf(idx++));

            //add to root
            rootElement.appendChild(id);
            rootElement.appendChild(name);
            rootElement.appendChild(distanceType);
            rootElement.appendChild(distanceUnit);

            rootElement.appendChild(locationList);
            rootElement.appendChild(depotList);
            rootElement.appendChild(vehicleList);
            rootElement.appendChild(customerList);

            //add child to driverList
            for(int i=0; i<num_drivers; i++)
            {
                JSONObject driver = drivers.getJSONObject(i);
                //LocationList
                //create
                Element location = document.createElement("VrpAirLocation");
                Element locationId = document.createElement("id");
                Element locLatitude = document.createElement("latitude");
                Element locLongitude = document.createElement("longitude");
                //append
                locationList.appendChild(location);
                location.appendChild(locationId);
                location.appendChild(locLatitude);
                location.appendChild(locLongitude);
                //setValue
                locationId.appendChild(document.createTextNode(String.valueOf(driver.get("id"))));
                locLatitude.appendChild(document.createTextNode(String.valueOf(driver.get("lat"))));
                locLongitude.appendChild(document.createTextNode(String.valueOf(driver.get("lon"))));
                //set attribute
                Attr locationIdAttr = document.createAttribute("id");
                int thisLocationId = idx;
                locationIdAttr.setValue(String.valueOf(idx++));
                location.setAttributeNode(locationIdAttr);

                //DepotList
                //create
                Element depot = document.createElement("VrpDepot");
                Element depot_id = document.createElement("id");
                Element depotLocation = document.createElement("location");

                //setValue
                depot_id.appendChild(document.createTextNode(String.valueOf(i+1)));

                //set attribute
                Attr depotIdAttr = document.createAttribute("id");
                Attr depotClassAttr = document.createAttribute("class");
                Attr depotRefAttr = document.createAttribute("reference");

                int thisDepotId = idx;
                depotIdAttr.setValue(String.valueOf(idx++));
                depotClassAttr.setValue("VrpAirLocation");
                depotRefAttr.setValue(String.valueOf(thisLocationId));

                depot.setAttributeNode(depotIdAttr);
                depotLocation.setAttributeNode(depotClassAttr);
                depotLocation.setAttributeNode(depotRefAttr);

                //append
                depotList.appendChild(depot);
                depot.appendChild(depot_id);
                depot.appendChild(depotLocation);

                //VehicleList
                //create
                Element vehicleElem = document.createElement("VrpVehicle");
                Element driver_id = document.createElement("id");
                Element vehicleCap = document.createElement("capacity");
                Element vehicleDepot = document.createElement("depot");

                Attr vehicleElemId = document.createAttribute("id");
                Attr vehicleDepotRef = document.createAttribute("reference");

                //setValue
                driver_id.appendChild(document.createTextNode(String.valueOf(i+1)));
                vehicleCap.appendChild(document.createTextNode(String.valueOf(num_order+10)));
                //set attribute

                vehicleElemId.setValue(String.valueOf(idx++));
                vehicleDepotRef.setValue(String.valueOf(thisDepotId));
                vehicleDepot.setAttributeNode(vehicleDepotRef);

                //append
                vehicleList.appendChild(vehicleElem);
                vehicleElem.appendChild(driver_id);
                vehicleElem.appendChild(vehicleCap);
                vehicleElem.appendChild(vehicleDepot);

            }

            //add child to customerList
            for(int i=0; i<num_order; i++)
            {
                JSONObject order = orders.getJSONObject(i);
                //LocationList
                //create
                Element location = document.createElement("VrpAirLocation");
                Element locationId = document.createElement("id");
                Element locLatitude = document.createElement("latitude");
                Element locLongitude = document.createElement("longitude");
                //append
                locationList.appendChild(location);
                location.appendChild(locationId);
                location.appendChild(locLatitude);
                location.appendChild(locLongitude);
                //setValue
                int thisId = idx;
                locationId.appendChild(document.createTextNode(String.valueOf(idx++)));
                locLatitude.appendChild(document.createTextNode(String.valueOf(order.get("lat"))));
                locLongitude.appendChild(document.createTextNode(String.valueOf(order.get("lon"))));
                //set attribute
                Attr locationIdAttr = document.createAttribute("id");
                int thisLocationId = idx;
                locationIdAttr.setValue(String.valueOf(idx++));
                location.setAttributeNode(locationIdAttr);

                //customerList
                //create
                Element customer = document.createElement("VrpCustomer");
                Element customerId = document.createElement("id");
                Element customerLocation = document.createElement("location");
                Element demand = document.createElement("demand");

                //append
                customerList.appendChild(customer);
                customer.appendChild(customerId);
                customer.appendChild(customerLocation);
                customer.appendChild(demand);

                //setValue
                customerId.appendChild(document.createTextNode(String.valueOf(thisId)));
                demand.appendChild(document.createTextNode("1"));
                //set attribute
                Attr customerNodeId = document.createAttribute("id");
                customerNodeId.setValue(String.valueOf(idx++));
                customer.setAttributeNode(customerNodeId);

                Attr customerLocationClass = document.createAttribute("class");
                Attr customerLocationRef = document.createAttribute("reference");
                customerLocationClass.setValue("VrpAirLocation");
                customerLocationRef.setValue(String.valueOf(thisLocationId));
                customerLocation.setAttributeNode(customerLocationClass);
                customerLocation.setAttributeNode(customerLocationRef);

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath+"file.xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    public static JSONObject jsonFromXML(String xmlString){
        //String xmlString = xml.toString();
        return XML.toJSONObject(xmlString);
    }
    public static JSONObject readJSONFile() throws FileNotFoundException {
        InputStream is =
                new FileInputStream("/home/shafi/Projects/Java/optaplanner-distribution-7.0.0.Final/examples/sources/src/main/resources/input1.json");
        String jsonTxt = null;
        try {
            jsonTxt = IOUtils.toString( is );
            System.out.println(jsonTxt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject("");
    }
    public static String readFile(String fileName)
    {
        String fileString = "";

        FileInputStream fis;
        try {
            fis = new FileInputStream( "/home/shafi/Projects/Java/optaplanner-distribution-7.0.0.Final/examples/sources/src/main/resources/input1.json");

            while(fis.available() > 0) {
                char ch = (char) fis.read();
                fileString += String.valueOf(ch);
            }

//            System.out.println(fileString);
            return fileString;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    public static void main(String[] args)
    {
        String file = readFile("abc");
        JSONObject json = new JSONObject(file);
//        JSONArray drivers = json.getJSONArray("driver");
//        JSONArray orders = json.getJSONArray("order");
//        for (int i = 0; i < drivers.length(); i++) {
////            JSONObject driver = new JSONObject(driver_);
//            System.out.println(drivers.getJSONObject(i).toString());
//            System.out.println(drivers.getJSONObject(i).get("id") + ",\n" +
//                    drivers.getJSONObject(i).get("lat") + ",\n" +
//                    drivers.getJSONObject(i).get("lon")
//            );
//        }
//        System.out.println("orders: "+ orders.toString());
        //System.out.println("JSON object: " + json.toString());

        jsonToXML(json);
    }
}
