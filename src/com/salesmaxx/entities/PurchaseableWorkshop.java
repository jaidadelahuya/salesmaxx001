package com.salesmaxx.entities;

import java.io.Serializable;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;

public class PurchaseableWorkshop implements Serializable, Purchasable {
	
	ScheduleWorkshopDisplay swd;
	
	
	
	public PurchaseableWorkshop(ScheduleWorkshopDisplay swd) {

		this.swd = swd;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int qtyLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String imageUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String location() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String date() {
		// TODO Auto-generated method stub
		return null;
	}

}
