package zw.co.connectus.service.model;

import zw.co.connectus.util.BaseDto;

public class ProfileDto extends BaseDto {

	private String msisdn;
	private String email;
	private String firstName;
	private String lastName;
	private String password;

	public String getMsisdn() {

		return msisdn;
	}

	public void setMsisdn(String msisdn) {

		this.msisdn = msisdn;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getFirstName() {

		return firstName;
	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	public String getLastName() {

		return lastName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}
}
