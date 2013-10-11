package com.xpeppers.tdd;

import java.util.HashMap;
import java.util.Map;

import com.xpeppers.tdd.Bundle.Denomination;

public class AutomatedTellerMachine {

	private Map<Integer, Integer> bills;
	private Bundle bundle;
	
	public AutomatedTellerMachine() {
		bundle = new Bundle();
	}

	public int billsFor(int denomination) {
		return bundle.get(denomination);
	}

	public void loadBillsFor(int quantity, int denomination) {
		bundle.loadBillsFor(quantity, denomination);
	}

	public Map<Integer, Integer> withdraw(int amount) {
		Map<Integer, Integer> withdrawal = new HashMap<Integer, Integer>();
		int remainder = amount;
		
		for (Denomination denomination : Denomination.values()) {
			
			int quantity = bundle.get(denomination.value);
			if (remainder > denomination.value && quantity > 0 && remainder < quantity * denomination.value) {
				withdrawal.put(denomination.value,  remainder / denomination.value);
				bundle.loadBillsFor(- remainder / denomination.value, denomination.value);
				remainder -= quantity * denomination.value;
			}
		}
		
		if (remainder > 0)
			throw new IllegalStateException("No more money in the ATM");
		
		return withdrawal;
		
	}

}
