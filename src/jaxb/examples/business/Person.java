package jaxb.examples.business;

import java.time.LocalDate;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jaxb.examples.adapter.DateAdapter;

@XmlRootElement(name = "Person")
@XmlType(propOrder = { "firstName", "lastName", "age", "dob", "address" })
public class Person {
	private String firstName;
	private String lastName;
	private int age;
	private LocalDate dob;
	private String address;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public LocalDate getDob() {
		return dob;
	}

	public String getAddress() {
		return address;
	}

	@XmlElement(name="firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@XmlElement(name="lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlElement(name="age")
	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement(name="dateOfBirth")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", dob=" + dob
				+ ", address=" + address + "]";
	}
}
