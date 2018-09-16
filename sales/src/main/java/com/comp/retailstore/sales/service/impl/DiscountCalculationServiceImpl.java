package com.comp.retailstore.sales.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comp.retailstore.sales.config.PropertiesResource;
import com.comp.retailstore.sales.constants.CustomerTypesEnum;
import com.comp.retailstore.sales.dto.CustomerDto;
import com.comp.retailstore.sales.dto.SaleDiscountResponseDto;
import com.comp.retailstore.sales.dto.SaleItemDto;
import com.comp.retailstore.sales.service.DiscountCalculationService;

/**
 * TODO
 *
 */
@Component
public class DiscountCalculationServiceImpl implements DiscountCalculationService {

	@Autowired
	PropertiesResource propertiesResource;

	@Override
	public SaleDiscountResponseDto calculateDiscount(CustomerDto customerDto, List<SaleItemDto> saleItems) {

		if (customerDto == null || saleItems == null || saleItems.isEmpty()) {
			/*
			 * Returning dummy discount response.
			 */
			return new SaleDiscountResponseDto();
		}

		/*
		 * totalBillForUserTypeDisc = to hold total bill value for applying customer
		 * type specific discount.
		 * 
		 * actualTotalBill = to hold total bill vlaue for applying store specific
		 * discount (eg: 5$ for 100$ bill)
		 */
		BigDecimal totalBillForUserTypeDisc = BigDecimal.valueOf(0);
		BigDecimal actualTotalBill = BigDecimal.valueOf(0);

		for (SaleItemDto dto : saleItems) {

			actualTotalBill = actualTotalBill.add(BigDecimal.valueOf(dto.getPerItemPrice() * dto.getQtyOfItems()));
			/*
			 * Requirement: 5. The percentage based discounts do not apply on groceries.
			 * 
			 * "propertiesResource.getPercDiscountIgnoreList()" comma separated key-value
			 * configured in "application.properties" file against key =
			 * "config.percDiscountIgnoreItems"
			 */
			if (propertiesResource.getPercDiscountIgnoreList().contains(dto.getItemType().toString().toUpperCase())) {
				continue;
			}

			totalBillForUserTypeDisc = totalBillForUserTypeDisc
					.add(BigDecimal.valueOf(dto.getPerItemPrice() * dto.getQtyOfItems()));

		}

		/*
		 * 3. If the user has been a customer for over 2 years, he gets a 5% discount.
		 * 
		 * Check and update "CustomerTypesEnum.LOYALTY" flag to customer type. Reads key
		 * = "config.loyaltyEligibiltyMonths" value from application.properties file.
		 * 
		 */
		int customerSinceMonths = checkMonthsBetween(customerDto.getCustomerSince(), Calendar.getInstance());
		if (propertiesResource.getLoyaltyEligibiltyMonths() != null
				&& propertiesResource.getLoyaltyEligibiltyMonths().intValue() > 0
				&& customerSinceMonths >= propertiesResource.getLoyaltyEligibiltyMonths().intValue()) {
			customerDto.setCustomerType(CustomerTypesEnum.LOYALTY);
		}

		double discountToBeApplied = 0d;
		if (CustomerTypesEnum.EMPLOYEE.toString().equals(customerDto.getCustomerType().toString())) {
			/*
			 * 1. If the user is an employee of the store, he gets a 30% discount
			 */
			discountToBeApplied = propertiesResource.getDiscountEmployee();

		} else if (CustomerTypesEnum.AFFILIATE.toString().equals(customerDto.getCustomerType().toString())) {
			/*
			 * 2. If the user is an affiliate of the store, he gets a 10% discount
			 */
			discountToBeApplied = propertiesResource.getDiscountAffiliate();

		} else if (CustomerTypesEnum.LOYALTY.toString().equals(customerDto.getCustomerType().toString())) {
			/*
			 * 3. If the user has been a customer for over 2 years, he gets a 5% discount.
			 */

			discountToBeApplied = propertiesResource.getLoyaltyDiscountPercent();
		}

		/*
		 * 6. A user can get only one of the percentage based discounts on a bill.
		 */
		double discountedAmountByCustType = totalBillForUserTypeDisc.doubleValue() * (discountToBeApplied / 100);

		/*
		 * Requirement, 4. For every $100 on the bill, there would be a $ 5 discount
		 * (e.g. for $ 990, you get $ 45 as a discount)..
		 * 
		 * since we need to round-off decimals, considering the integer type
		 */
		int amountToConsiderForDefaultdisc = Integer.valueOf(0);
		if (propertiesResource.getDefaultDiscValue() > 0 && propertiesResource.getDefaultDiscPurchaseCap() > 0
				&& actualTotalBill.doubleValue() > propertiesResource.getDefaultDiscPurchaseCap()) {
			amountToConsiderForDefaultdisc = Double
					.valueOf(actualTotalBill.doubleValue() / propertiesResource.getDefaultDiscPurchaseCap()).intValue();
			amountToConsiderForDefaultdisc = amountToConsiderForDefaultdisc
					* propertiesResource.getDefaultDiscValue().intValue();
		}

		BigDecimal groceriesBill = actualTotalBill.subtract(totalBillForUserTypeDisc);

		String customerType = customerDto.getCustomerType().name();
		Double customerTypeDiscAmount = discountedAmountByCustType;
		Double storeDiscount = Double.valueOf(amountToConsiderForDefaultdisc);

		Double totalDiscount = customerTypeDiscAmount + storeDiscount;
		BigDecimal finalBillToCustomer = actualTotalBill.subtract(BigDecimal.valueOf(totalDiscount));

		SaleDiscountResponseDto responseDto = new SaleDiscountResponseDto(actualTotalBill, groceriesBill, customerType,
				customerTypeDiscAmount, storeDiscount, totalDiscount, finalBillToCustomer);

		return responseDto;
	}

	private int checkMonthsBetween(Calendar a, Calendar b) {
		Calendar cal = Calendar.getInstance();
		if (a.before(b)) {
			cal = a;
		} else {
			cal = b;
			b = a;
		}
		int c = 0;
		while (cal.before(b)) {
			cal.add(Calendar.MONTH, 1);
			c++;
		}
		return c - 1;

	}

}
