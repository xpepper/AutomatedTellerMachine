package com.xpeppers.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Bundle {
	
	public static enum Denomination {
		FIVE(5), TEN(10), TWENTY(20), FIFTY(50);
		public final int value;
		Denomination(int aValue) {
			value = aValue;
		}
	}
	
	private Map<Integer, Integer> bills;
	
	public Bundle() {
		
		bills = new HashMap<Integer, Integer>();
		for (Denomination denomination : Denomination.values()) {
			bills.put(denomination.value, 0);
		}
	}

	public int get(int denomination) {
		return bills.get(denomination);
	}
	
	public void loadBillsFor(int quantity, int denomination) {
		int actualQuantity = get(denomination);
		bills.put(denomination, actualQuantity + quantity);
	}

}
