package com.comp.retailstore.sales.dto;

import java.io.Serializable;

import com.comp.retailstore.sales.constants.SaleItemTypesEnum;

/**
 *
 */
public class SaleItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long itemId;
	private String itemDesc;
	private SaleItemTypesEnum itemType;
	private Double perItemPrice;
	private int qtyOfItems;

	public SaleItemDto() {
		super();
	}

	public SaleItemDto(Long itemId, String itemDesc, SaleItemTypesEnum itemType, Double perItemPrice, int qtyOfItems) {
		super();
		this.itemId = itemId;
		this.itemDesc = itemDesc;
		this.itemType = itemType;
		this.perItemPrice = perItemPrice;
		this.qtyOfItems = qtyOfItems;
	}

	public Long getItemId() {
		return itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public SaleItemTypesEnum getItemType() {
		if (itemType == null) {
			itemType = SaleItemTypesEnum.OTHERS;
		}
		return itemType;
	}

	public Double getPerItemPrice() {
		if (perItemPrice == null) {
			perItemPrice = Double.valueOf(0);
		}
		return perItemPrice;
	}

	public int getQtyOfItems() {
		return qtyOfItems;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public void setItemType(SaleItemTypesEnum itemType) {
		this.itemType = itemType;
	}

	public void setPerItemPrice(Double perItemPrice) {
		this.perItemPrice = perItemPrice;
	}

	public void setQtyOfItems(int qtyOfItems) {
		this.qtyOfItems = qtyOfItems;
	}

}
