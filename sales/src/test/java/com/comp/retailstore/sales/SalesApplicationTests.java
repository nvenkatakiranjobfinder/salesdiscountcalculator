package com.comp.retailstore.sales;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.comp.retailstore.sales.constants.CustomerTypesEnum;
import com.comp.retailstore.sales.constants.SaleItemTypesEnum;
import com.comp.retailstore.sales.dto.CustomerDto;
import com.comp.retailstore.sales.dto.SaleDiscountResponseDto;
import com.comp.retailstore.sales.dto.SaleItemDto;
import com.comp.retailstore.sales.service.DiscountCalculationService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesApplicationTests {

	@Autowired
	DiscountCalculationService discountService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void calculateDiscount_Employee() {
		SaleDiscountResponseDto discountResponse = discountService.calculateDiscount(
				getDummyCustomerDto(CustomerTypesEnum.EMPLOYEE), getDummySalesMoreThan100DollarsWithGroceries());

		printLogMessage("SalesMoreThan100DollarsWithGroceries()", discountResponse.toString());

		assertEquals(BigDecimal.valueOf(175d), discountResponse.getTotalBill());
		assertEquals(BigDecimal.valueOf(51.5d), BigDecimal.valueOf(discountResponse.getTotalDiscount()));

		discountResponse = discountService.calculateDiscount(getDummyCustomerDto(CustomerTypesEnum.EMPLOYEE),
				getDummySalesMoreThan100Dollars());

		printLogMessage("SalesMoreThan100Dollars()", discountResponse.toString());

		assertEquals(BigDecimal.valueOf(490d), discountResponse.getTotalBill());
		assertEquals(BigDecimal.valueOf(167.0d), BigDecimal.valueOf(discountResponse.getTotalDiscount()));

	}

	@Test
	public void calculateDiscount_Affiliate() {
		SaleDiscountResponseDto discountResponse = discountService.calculateDiscount(
				getDummyCustomerDto(CustomerTypesEnum.AFFILIATE), getDummySalesMoreThan100DollarsWithGroceries());

		assertEquals(CustomerTypesEnum.AFFILIATE.name(), discountResponse.getCustomerType());
		assertEquals(BigDecimal.valueOf(175d), discountResponse.getTotalBill());
		assertEquals(BigDecimal.valueOf(20.5d), BigDecimal.valueOf(discountResponse.getTotalDiscount()));

		printLogMessage("SalesMoreThan100DollarsWithGroceries()", discountResponse.toString());

		discountResponse = discountService.calculateDiscount(getDummyCustomerDto(CustomerTypesEnum.AFFILIATE),
				getDummySalesMoreThan100Dollars());

		assertEquals(BigDecimal.valueOf(490d), discountResponse.getTotalBill());
		assertEquals(BigDecimal.valueOf(69d), BigDecimal.valueOf(discountResponse.getTotalDiscount()));

		printLogMessage("SalesMoreThan100Dollars()", discountResponse.toString());

	}

	@Test
	public void calculateDiscount_Loyal() {

		SaleDiscountResponseDto discountResponse = discountService.calculateDiscount(
				getDummyCustomerDto(CustomerTypesEnum.LOYALTY), getDummySalesMoreThan100DollarsWithGroceries());

		assertEquals(CustomerTypesEnum.LOYALTY.name(), discountResponse.getCustomerType());
		assertEquals(BigDecimal.valueOf(175d), discountResponse.getTotalBill());
		assertEquals(BigDecimal.valueOf(12.75d), BigDecimal.valueOf(discountResponse.getTotalDiscount()));

		printLogMessage("SalesMoreThan100DollarsWithGroceries()", discountResponse);

		discountResponse = discountService.calculateDiscount(getDummyCustomerDto(CustomerTypesEnum.LOYALTY),
				getDummySalesMoreThan100Dollars());

		assertEquals(BigDecimal.valueOf(490d), discountResponse.getTotalBill());
		assertEquals(BigDecimal.valueOf(44.5d), BigDecimal.valueOf(discountResponse.getTotalDiscount()));

		printLogMessage("SalesMoreThan100Dollars()", discountResponse);

	}

	@Test
	public void calculateDiscount_InStoreDefault() {

		SaleDiscountResponseDto discountResponse = discountService
				.calculateDiscount(getDummyCustomerDto(CustomerTypesEnum.DEFAULT), getDummySalesMoreThan100Dollars());

		assertEquals(CustomerTypesEnum.DEFAULT.name(), discountResponse.getCustomerType());
		assertEquals(BigDecimal.valueOf(490d), discountResponse.getTotalBill());
		assertEquals(BigDecimal.valueOf(20d), BigDecimal.valueOf(discountResponse.getTotalDiscount()));

		printLogMessage("getDummySalesMoreThan100Dollars()", discountResponse);

		discountResponse = discountService.calculateDiscount(getDummyCustomerDto(CustomerTypesEnum.DEFAULT),
				getDummySalesLessThan100Dollars());

		assertEquals(BigDecimal.valueOf(55d), discountResponse.getTotalBill());
		assertEquals(BigDecimal.valueOf(0d), BigDecimal.valueOf(discountResponse.getTotalDiscount()));

		printLogMessage("getDummySalesLessThan100Dollars()", discountResponse);

	}

	@Test
	public void calculateDiscount_EmptyBill() {

		SaleDiscountResponseDto discountResponse = discountService.calculateDiscount(null, null);

		printLogMessage("calculateDiscount_EmptyBill", discountResponse);

	}

	private CustomerDto getDummyCustomerDto(CustomerTypesEnum customerType) {
		Random randomno = new Random();
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(randomno.nextLong());
		customerDto.setCustomerType(customerType);

		if (customerType.name().equalsIgnoreCase(CustomerTypesEnum.LOYALTY.name())) {
			Calendar twoYrsOld = Calendar.getInstance();
			twoYrsOld.set(Calendar.YEAR, 2015);
			customerDto.setCustomerSince(twoYrsOld);
		} else {
			customerDto.setCustomerSince(Calendar.getInstance());
		}

		return customerDto;
	}

	/**
	 * total bill = 490 (300+40+100+50), groceries = 0
	 */
	private List<SaleItemDto> getDummySalesMoreThan100Dollars() {
		Random randomno = new Random();

		List<SaleItemDto> items = new ArrayList<SaleItemDto>();
		items.add(
				new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.ELECTRONICS, 300d, 1));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.PERFUMES, 20d, 2));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.CLOTHES, 50d, 2));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.OTHERS, 10d, 5));

		return items;
	}

	/**
	 * total bill = 175 (100+20+50+5), groceries = 20
	 */
	private List<SaleItemDto> getDummySalesMoreThan100DollarsWithGroceries() {
		Random randomno = new Random();

		List<SaleItemDto> items = new ArrayList<SaleItemDto>();
		items.add(
				new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.ELECTRONICS, 100d, 1));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.GROCERIES, 10d, 2));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.CLOTHES, 50d, 1));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.OTHERS, 5d, 1));

		return items;
	}

	/**
	 * total bill = 55 (50+5), groceries = 0
	 */
	private List<SaleItemDto> getDummySalesLessThan100Dollars() {
		Random randomno = new Random();

		List<SaleItemDto> items = new ArrayList<SaleItemDto>();
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.CLOTHES, 50d, 1));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.OTHERS, 5d, 1));

		return items;

	}

	/**
	 * total bill = 75 (20+50+5), groceries = 0
	 */
	private List<SaleItemDto> getDummySalesLessThan100DollarsWithGroceries() {
		Random randomno = new Random();

		List<SaleItemDto> items = new ArrayList<SaleItemDto>();
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.GROCERIES, 10d, 2));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.CLOTHES, 50d, 1));
		items.add(new SaleItemDto(randomno.nextLong(), randomno.nextLong() + "", SaleItemTypesEnum.OTHERS, 5d, 1));

		return items;
	}

	private void printLogMessage(String msg1, Object msg2) {
		System.out.println(msg1 + " = " + msg2);
	}

}
