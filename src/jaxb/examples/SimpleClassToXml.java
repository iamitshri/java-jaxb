package jaxb.examples;

import java.io.File;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import jaxb.examples.business.Person;
import jaxb.examples.business.PersonList;

public class SimpleClassToXml {

	/**
	 * Here are the objectives of this project
	 * 
	 * 1. Given any class with xml annotations, convert it to XML file or output
	 * it to console
	 * 
	 * 2. Given an XSD, create xml files that complies with the xsd
	 * 
	 * 3. Given an XML file read, all of the properties or get desired property
	 * @throws JAXBException 
	 * 
	 */
	
	public static void main(String[] args) throws JAXBException {
		createPersonList();
		createXmlFromPOJO();
	}

	private static void createPersonList() throws JAXBException, PropertyException {
		Person p1 = new Person();
		p1.setFirstName("Narendra");
		p1.setLastName("Modi");
		p1.setAddress("Delhi");
		p1.setAge(65);
		p1.setDob(LocalDate.now());
		
		Person p2 = new Person();
		p2.setFirstName("John");
		p2.setLastName("Pandit");
		p2.setAddress("123 St, Highland");
		p2.setAge(12);
		p2.setDob(LocalDate.now());
		
		PersonList pl = new PersonList();
		pl.getPersonList().add(p1);
		pl.getPersonList().add(p2);
		
		JAXBContext context = JAXBContext.newInstance(PersonList.class);
		Marshaller marsheller = context.createMarshaller();
		marsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marsheller.marshal(pl, System.out);
		marsheller.marshal(pl, new File("./src/resources/personList.xml"));
	}

	private static void createXmlFromPOJO() throws JAXBException, PropertyException {
		Person p = new Person();
		p.setFirstName("John");
		p.setLastName("Pandit");
		p.setAddress("123 St, Highland");
		p.setAge(12);
		p.setDob(LocalDate.now());
		
		JAXBContext context = JAXBContext.newInstance(Person.class);
		Marshaller marsherller = context.createMarshaller();
		marsherller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marsherller.marshal(p, System.out);
		 marsherller.marshal(p, new File("./src/resources/person.xml"));
	}
	
	
	
}
