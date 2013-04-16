package com.eucsoft.beeper.model;

public class User {

	private String deviceInfo;
	
	private Room room;
	
	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceInfo == null) ? 0 : deviceInfo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (deviceInfo == null) {
			if (other.deviceInfo != null)
				return false;
		} else if (!deviceInfo.equals(other.deviceInfo))
			return false;
		return true;
	}
	
}
