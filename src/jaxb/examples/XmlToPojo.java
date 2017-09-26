package jaxb.examples;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import jaxb.examples.business.Person;
import jaxb.examples.business.PersonList;

public class XmlToPojo {
	public static void main(String[] args) throws JAXBException {
		
		File xmlfile = new File("./src/resources/person-unmarshall.xml");
		JAXBContext context = JAXBContext.newInstance(Person.class);
		Unmarshaller um = context.createUnmarshaller();
		Person p = (Person) um.unmarshal(xmlfile);
	 	System.out.println(p);
	 	
		File persons_xmlfile = new File("./src/resources/personList.xml");
		context = JAXBContext.newInstance(PersonList.class);
		um = context.createUnmarshaller();
		PersonList pl = (PersonList) um.unmarshal(persons_xmlfile);
		System.out.println(pl);
	}
}
