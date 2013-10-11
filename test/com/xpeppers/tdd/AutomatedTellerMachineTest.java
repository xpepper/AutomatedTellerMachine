package com.xpeppers.tdd;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AutomatedTellerMachineTest {

	private AutomatedTellerMachine atm;

	@Before
	public void setup() throws Exception {
		atm = new AutomatedTellerMachine();
	}

	@Test
	public void loadBillsForSetsNumberOfBillsForDenomination() {
		assertEquals(0, atm.billsFor(5));
		assertEquals(0, atm.billsFor(10));

		atm.loadBillsFor(3, 5);

		assertEquals(3, atm.billsFor(5));
		assertEquals(0, atm.billsFor(10));
	}

	@Test
	public void loadBillsForUpdatesNumerOfBillsIfSomeIsAlreadyPresent()
			throws Exception {

		atm.loadBillsFor(1, 5);
		atm.loadBillsFor(2, 5);
		assertEquals(3, atm.billsFor(5));

	}

	@Test(expected = IllegalStateException.class)
	public void withdrawFailsIfThereIsNotEnoughMoney() throws Exception {
		atm.withdraw(25);
	}
	
	@Test
	public void withdrawalReturnsBundleForDesiredAmount() throws Exception {
		atm.loadBillsFor(12, 5);
		
		int amount = 0;
		Map<Integer, Integer> bundle = atm.withdraw(25);
		for (Integer denomination : bundle.keySet()) {
			int quantity = bundle.get(denomination);
			amount += quantity * denomination;
		}
		
		assertEquals(25, amount);
		assertEquals(7, atm.billsFor(5));
	}

}
