package com.jbossatwork.dto;

import java.io.*;

public class CreditCheckRequestDTO implements Serializable
{
	private String name;
	private String ssn;
	private String email;

	public CreditCheckRequestDTO() {}

	public CreditCheckRequestDTO(String name, String ssn, String email)
	{
		this.name = name;
		this.ssn = ssn;
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSsn()
	{
		return ssn;
	}

	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}