package com.techelevator;

public class Television {

	
	private boolean isOn = false;
	private int currentChannel = 3;
	private int currentVolume = 2;
	
	
	// copy and paste the methods
	
	public void turnOff() {
		this.isOn = false;
	}
	
	public void turnOn() {
		this.isOn = true;
		this.currentVolume = 2;
		this.currentChannel = 3;
	}
	
	// takes an input of newChannel!
	// determines if the channel can be changed
	public void changeChannel(int newChannel) {
		if (this.isOn && newChannel >= 3 && newChannel <= 18) {
			this.currentChannel = newChannel;
		}
	}
	
	public void channelUp() {
		if (this.isOn) {
			this.currentChannel++;
			if (this.currentChannel > 18) {
				this.currentChannel = 3;
			}
		}
	}
	
	public void channelDown() {
		if (this.isOn) {
			this.currentChannel--;
			if(this.currentChannel < 3) {
				this.currentChannel = 18;
			}
		}
	}
	
	public void raiseVolume() {
		if (this.isOn && this.currentVolume < 10) {
			this.currentVolume++;
		}
	}
	
	public void lowerVolume() {
		if (this.isOn && this.currentVolume > 0) {
			this.currentVolume--;
		}
	}
	
	public boolean isOn() {
		return isOn;
	}
	public int getCurrentChannel() {
		return currentChannel;
	}
	public int getCurrentVolume() {
		return currentVolume;
	}
	
	
	
}
