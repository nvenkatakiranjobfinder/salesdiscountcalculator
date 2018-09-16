package com.comp.retailstore.sales.dto;

import java.io.Serializable;
import java.util.Calendar;

import com.comp.retailstore.sales.constants.CustomerTypesEnum;

/**
 * 
 */
public class CustomerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long customerId;
	private String customerName;
	private CustomerTypesEnum customerType;
	private Calendar customerSince;

	public Long getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public CustomerTypesEnum getCustomerType() {
		if (customerType == null) {
			customerType = CustomerTypesEnum.DEFAULT;
		}
		return customerType;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerType(CustomerTypesEnum customerType) {
		this.customerType = customerType;
	}

	public Calendar getCustomerSince() {
		if (customerSince == null) {
			customerSince = Calendar.getInstance();
		}
		return customerSince;
	}

	public void setCustomerSince(Calendar customerSince) {
		this.customerSince = customerSince;
	}

}
