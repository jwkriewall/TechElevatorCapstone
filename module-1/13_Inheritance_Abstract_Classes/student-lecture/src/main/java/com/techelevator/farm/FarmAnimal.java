package com.techelevator.farm;

public class FarmAnimal implements Singable  {
	private String name;
	private String sound;
	private boolean isSleeping = false;
	
	public FarmAnimal(String name, String sound) {
		this.name = name;
		this.sound = sound;
	}

	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSound() {
		if (isSleeping) {
			return "Zzzzzzz..........";
		}
		return sound;
	}

	@Override
	public int doSomething(double x) {
		// TODO Auto-generated method stub
		return (int) x * 34;
	}
	
	public boolean isSleeping() {
		return this.isSleeping;
	}
	
	public void sleep() {
		this.isSleeping = true;
	}
	
	public void wake() {
		this.isSleeping = false;
	}
	

}