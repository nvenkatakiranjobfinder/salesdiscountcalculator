package com.comp.retailstore.sales.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Enum object to hold Sale Item Type values.
 *
 */
public enum SaleItemTypesEnum {

	GROCERIES, CLOTHES, ELECTRONICS, PERFUMES, OTHERS;

	public static List<String> getSaleItemTypes() {
		List<String> itemTypes = new ArrayList<String>();
		for (SaleItemTypesEnum itemType : SaleItemTypesEnum.values()) {
			itemTypes.add(itemType.toString());
		}

		return itemTypes;
	}
}
