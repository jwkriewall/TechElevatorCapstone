package com.techelevator;

import org.junit.*;

public class CigarPartyTest {
	
	private CigarParty cigarParty;
	
	@Before
	public void setup() {
		cigarParty = new CigarParty();
	}
	
	@Test
	public void cigars_under_40_weekend() {
		boolean result = cigarParty.haveParty(39, true);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void cigars_over_40_under_60_weekend() {
		boolean result = cigarParty.haveParty(45, true);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void cigars_over_40_under_60_NOTweekend() {
		boolean result = cigarParty.haveParty(45, false);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void cigars_over_60_NOTweekend() {
		boolean result = cigarParty.haveParty(70, false);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void cigars_under_40_NOTweekend() {
		boolean result = cigarParty.haveParty(39, false);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void cigars_under_0_weekend() {
		boolean result = cigarParty.haveParty(-1, true);
		Assert.assertEquals(false, result);
	}
}
