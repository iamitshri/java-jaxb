package jaxb.examples.business;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Persons")
public class PersonList {

	List<Person> personList = new ArrayList<>();

	public List<Person> getPersonList() {
		return personList;
	}
	@XmlElement(name="Person")
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
}
