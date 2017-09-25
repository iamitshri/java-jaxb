package jaxb.examples;

import java.io.File;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import jaxb.examples.business.Person;

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
		//marsherller.marshal(p, new File("person.xml"));
		
	}
}
