package ca.ubc.cs.cpsc210.resourcefinder.parser;

import ca.ubc.cs.cpsc210.resourcefinder.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.registry.Registry;
import java.util.HashSet;
import java.util.Set;

// Handler for XML resource parsing
public class ResourceHandler extends DefaultHandler {
    private ResourceRegistry registry;
    private StringBuilder accumulator;

    Resource tempResource;
    String tempName;
    ContactInfo tempContactInfo;
    GeoPoint tempGeoPoint;
    Set<Service> tempServices;
    Service tempService;
    String tempAddress;
    double tempLat;
    double tempLon;
    URL tempWebaddress;
    String tempPhone;

    // EFFECTS: constructs resource handler for XML parser
    public ResourceHandler(ResourceRegistry registry) {
        this.registry = registry;
        accumulator = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (qName.toLowerCase().equals("resource")){
            tempName = null;
            tempContactInfo = null;
            tempGeoPoint = null;
            tempService = null;
            tempAddress = null;
            tempLat = 0.0;
            tempLon = 0.0;
            tempWebaddress = null;
            tempPhone = null;
            tempResource = null;
            tempServices = new HashSet<Service>();

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        String data = accumulator.toString().trim();   // trim leading and trailing whitespace

        if (qName.toLowerCase().equals("name")) {
            tempName = data;
        } else if (qName.toLowerCase().equals("address")) {
            tempAddress = data;
        } else if (qName.toLowerCase().equals("lat")) {
            tempLat = Double.parseDouble(data);
        } else if (qName.toLowerCase().equals("lon")) {
            tempLon = Double.parseDouble(data);
        } else if (qName.toLowerCase().equals("webaddress")) {
            try {
                tempWebaddress = new URL(data);
            } catch (MalformedURLException e) {
                tempWebaddress = null;
            }
        } else if (qName.toLowerCase().equals("phone")) {
            tempPhone = data;
        } else if (qName.toLowerCase().equals("service")) {
            switch (data) {
                case "Food":
                    tempServices.add(Service.FOOD);
                    break;
                case "Counselling":
                    tempServices.add(Service.COUNSELLING);
                    break;
                case "Legal advice":
                    tempServices.add(Service.LEGAL);
                    break;
                case "Senior services":
                    tempServices.add(Service.SENIOR);
                    break;
                case "Shelter":
                    tempServices.add(Service.SHELTER);
                    break;
                case "Youth services":
                    tempServices.add(Service.YOUTH);
                    break;
                default:
                    break;
            }
        }
        if (qName.toLowerCase().equals("resources") && registry.getResources().size() ==0) {
            throw new SAXException();
        } else if (qName.toLowerCase().equals("resource")) {
            if (tempName != (null) && tempGeoPoint != (null)
                    && tempAddress != (null)
                    && tempPhone != (null)
                    && tempWebaddress != (null)
                    && tempServices.size() > 0){
                tempContactInfo = new ContactInfo(tempAddress,tempGeoPoint,tempWebaddress,tempPhone);
                tempResource = new Resource(tempName,tempContactInfo);
                for (Service next: tempServices){
                    tempResource.addService(next);
                }
                registry.addResource(tempResource);

            }
        } else if (qName.toLowerCase().equals("location")) {
            tempGeoPoint = new GeoPoint(tempLat, tempLon);
        }
        accumulator.setLength(0);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        accumulator.append(ch, start, length);
    }
}
