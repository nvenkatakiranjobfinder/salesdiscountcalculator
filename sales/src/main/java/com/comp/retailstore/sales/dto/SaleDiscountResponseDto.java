package com.comp.retailstore.sales.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 */
public class SaleDiscountResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal totalBill = BigDecimal.valueOf(0);
	private BigDecimal groceriesBill = BigDecimal.valueOf(0);

	private String customerType = "NA";

	/*
	 * Employee/Affiliate/OldCustomer
	 */
	private Double customerTypeDiscAmount = 0d;

	/*
	 * Eg: 5$ discount for every 100$
	 */
	private Double storeDiscount = 0d;

	private Double totalDiscount = 0d;
	private BigDecimal finalBillToCustomer = BigDecimal.valueOf(0);

	public SaleDiscountResponseDto() {
		super();
	}

	public SaleDiscountResponseDto(BigDecimal totalBill, BigDecimal groceriesBill, String customerType,
			Double customerTypeDiscAmount, Double storeDiscount, Double totalDiscount, BigDecimal finalBillToCustomer) {
		super();
		this.totalBill = totalBill;
		this.groceriesBill = groceriesBill;
		this.customerType = customerType;
		this.customerTypeDiscAmount = customerTypeDiscAmount;
		this.storeDiscount = storeDiscount;
		this.totalDiscount = totalDiscount;
		this.finalBillToCustomer = finalBillToCustomer;
	}



	public BigDecimal getTotalBill() {
		return totalBill;
	}

	public BigDecimal getGroceriesBill() {
		return groceriesBill;
	}

	public String getCustomerType() {
		return customerType;
	}

	public Double getCustomerTypeDiscAmount() {
		return customerTypeDiscAmount;
	}

	public Double getStoreDiscount() {
		return storeDiscount;
	}

	public void setTotalBill(BigDecimal totalBill) {
		this.totalBill = totalBill;
	}

	public void setGroceriesBill(BigDecimal groceriesBill) {
		this.groceriesBill = groceriesBill;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public void setCustomerTypeDiscAmount(Double customerTypeDiscAmount) {
		this.customerTypeDiscAmount = customerTypeDiscAmount;
	}

	public void setStoreDiscount(Double storeDiscount) {
		this.storeDiscount = storeDiscount;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public BigDecimal getFinalBillToCustomer() {
		return finalBillToCustomer;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public void setFinalBillToCustomer(BigDecimal finalBillToCustomer) {
		this.finalBillToCustomer = finalBillToCustomer;
	}

	@Override
	public String toString() {
		return "SaleDiscountResponseDto [totalBill=" + totalBill + ", groceriesBill=" + groceriesBill
				+ ", customerType=" + customerType + ", customerTypeDiscAmount=" + customerTypeDiscAmount
				+ ", storeDiscount=" + storeDiscount + ", totalDiscount=" + totalDiscount + ", finalBillToCustomer="
				+ finalBillToCustomer + "]";
	}

}
