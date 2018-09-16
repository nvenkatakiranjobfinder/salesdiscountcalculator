package com.comp.retailstore.sales.service;

import java.util.List;

import com.comp.retailstore.sales.dto.CustomerDto;
import com.comp.retailstore.sales.dto.SaleDiscountResponseDto;
import com.comp.retailstore.sales.dto.SaleItemDto;

public interface DiscountCalculationService {

	SaleDiscountResponseDto calculateDiscount(CustomerDto customerDto, List<SaleItemDto> saleItems);
}
