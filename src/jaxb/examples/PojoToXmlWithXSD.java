package jaxb.examples;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import jaxb.examples.business.Person;

public class PojoToXmlWithXSD {

	public static void main(String[] args) throws JAXBException, SAXException {
		errorHandlerForValidation();
	}

	public static void validationEventHandler() throws JAXBException, SAXException, PropertyException {
		Person p = new Person();
		
		p.setFirstName("John");
		// last name is required
		//p.setLastName("Pandit");  
		
		p.setAddress("123 St, Highland");
		p.setAge(121);
		p.setDob(LocalDate.now());
		JAXBContext context = JAXBContext.newInstance(Person.class);
		Marshaller marshaller = context.createMarshaller();
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File("src/resources/Persons-validation-required.xsd"));
		marshaller.setSchema(schema);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		ValidationEventHandler handler = new ValidationEventHandler() {
			@Override
			public boolean handleEvent(ValidationEvent event) {
				System.out.println(event.getMessage());
				System.out.println();
				return false;
			}
		};
		marshaller.setEventHandler(handler );
		marshaller.marshal(p, System.out);
	}

	public static void errorHandlerForValidation() throws SAXException, JAXBException {
		Person p = new Person();
		p.setFirstName("John");
		p.setLastName("Pandit");
		p.setAddress("123 St, Highland");
		p.setAge(112);
		p.setDob(LocalDate.now());

		// this will fail because: Age can be 12 or 13 
		runValidation(p);
		//this will succeed
		p.setAge(13);
		runValidation(p);
	}

	public static void runValidation(Person p) throws SAXException, JAXBException {
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File("src/resources/Persons-validation-required.xsd"));
		Validator validator = schema.newValidator();
		JAXBContext context = JAXBContext.newInstance(Person.class);
		Source source = new JAXBSource(context, p);
		try {
			ErrorHandler errorHandler = new ErrorHandler() {

				@Override
				public void warning(SAXParseException exception) throws SAXException {
					System.out.println("warning:" + exception);

				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					System.out.println("fatalError:" + exception);

				}

				@Override
				public void error(SAXParseException exception) throws SAXException {
					System.out.println("error:" + exception);

				}
			};
			validator.setErrorHandler(errorHandler);
			validator.validate(source);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void marshallWithXsd() throws JAXBException, SAXException, PropertyException {
		Person p = new Person();
		p.setFirstName("John");
		p.setLastName("Pandit");
		p.setAddress("123 St, Highland");
		p.setAge(12);
		p.setDob(LocalDate.now());

		JAXBContext context = JAXBContext.newInstance(Person.class);
		Marshaller marsherller = context.createMarshaller();
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("./src/resources/Persons.xsd"));
		marsherller.setSchema(schema);
		marsherller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marsherller.marshal(p, System.out);
		marsherller.marshal(p, new File("./src/resources/person-with-schema.xml"));
	}
}
