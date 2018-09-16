package com.comp.retailstore.sales.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Load the configuration from "application.properties" and initialize to
 * instance variables. All keys starting with "config.xxxxxx" will be
 * initialized here.
 * 
 * 'xxxxxx' refers to instance variable inside the 'PropertiesResource.java'
 * object.
 * 
 *
 */
@Component
@ConfigurationProperties("config")
public class PropertiesResource {

	private Double discountEmployee;
	private Double discountAffiliate;

	private Long loyaltyEligibiltyMonths;
	private Double loyaltyDiscountPercent;

	private Double defaultDiscPurchaseCap;
	private Double defaultDiscValue;

	private String percDiscountIgnoreItems;

	private List<String> percDiscountIgnoreList;

	public Double getDiscountEmployee() {
		if (discountEmployee == null) {
			discountEmployee = Double.valueOf(0);
		}
		return discountEmployee;
	}

	public Double getDiscountAffiliate() {
		if (discountAffiliate == null) {
			discountAffiliate = Double.valueOf(0);
		}
		return discountAffiliate;
	}

	public Long getLoyaltyEligibiltyMonths() {
		if (loyaltyEligibiltyMonths == null) {
			loyaltyEligibiltyMonths = Long.valueOf(0);
		}
		return loyaltyEligibiltyMonths;
	}

	public Double getLoyaltyDiscountPercent() {
		if (loyaltyDiscountPercent == null) {
			loyaltyDiscountPercent = Double.valueOf(0);
		}
		return loyaltyDiscountPercent;
	}

	public void setDiscountEmployee(Double discountEmployee) {
		this.discountEmployee = discountEmployee;
	}

	public void setDiscountAffiliate(Double discountAffiliate) {
		this.discountAffiliate = discountAffiliate;
	}

	public void setLoyaltyEligibiltyMonths(Long loyaltyEligibiltyMonths) {
		this.loyaltyEligibiltyMonths = loyaltyEligibiltyMonths;
	}

	public void setLoyaltyDiscountPercent(Double loyaltyDiscountPercent) {
		this.loyaltyDiscountPercent = loyaltyDiscountPercent;
	}

	public Double getDefaultDiscPurchaseCap() {
		if (defaultDiscPurchaseCap == null) {
			defaultDiscPurchaseCap = Double.valueOf(0);
		}
		return defaultDiscPurchaseCap;
	}

	public Double getDefaultDiscValue() {
		if (defaultDiscValue == null) {
			defaultDiscValue = Double.valueOf(0);
		}
		return defaultDiscValue;
	}

	public void setDefaultDiscPurchaseCap(Double defaultDiscPurchaseCap) {
		this.defaultDiscPurchaseCap = defaultDiscPurchaseCap;
	}

	public void setDefaultDiscValue(Double defaultDiscValue) {
		this.defaultDiscValue = defaultDiscValue;
	}

	public String getPercDiscountIgnoreItems() {
		return percDiscountIgnoreItems;
	}

	public List<String> getPercDiscountIgnoreList() {
		if (percDiscountIgnoreItems == null || percDiscountIgnoreItems.trim().isEmpty()) {
			percDiscountIgnoreList = new ArrayList<String>();
		}

		if (percDiscountIgnoreList == null) {
			percDiscountIgnoreList = Arrays.asList(percDiscountIgnoreItems.split(","));
		}

		return percDiscountIgnoreList;
	}

	public void setPercDiscountIgnoreItems(String percDiscountIgnoreItems) {
		this.percDiscountIgnoreItems = percDiscountIgnoreItems;
	}

	public void setPercDiscountIgnoreList(List<String> percDiscountIgnoreList) {
		this.percDiscountIgnoreList = percDiscountIgnoreList;
	}

	@Override
	public String toString() {
		return "PropertiesResource [discountEmployee=" + discountEmployee + ", discountAffiliate=" + discountAffiliate
				+ ", loyaltyEligibiltyMonths=" + loyaltyEligibiltyMonths + ", loyaltyDiscountPercent="
				+ loyaltyDiscountPercent + "]";
	}

}