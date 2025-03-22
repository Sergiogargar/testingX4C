package com.xerez4change.cream;

import java.util.Objects;

public class SensorEntity {
	
	protected Integer idSensor;
	protected Integer idDevice;
	protected Integer status;
	protected String name;
	
	public SensorEntity(Integer idSensor, Integer idDevice, Integer status, String name) {
		super();
		this.idSensor = idSensor;
		this.idDevice = idDevice;
		this.status = status;
		this.name = name;
	}
	
	public SensorEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(Integer idSensor) {
		this.idSensor = idSensor;
	}
	public Integer getIdDevice() {
		return idDevice;
	}
	public void setIdDevice(Integer idDevice) {
		this.idDevice = idDevice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idDevice, idSensor, name, status);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorEntity other = (SensorEntity) obj;
		return Objects.equals(idDevice, other.idDevice) && Objects.equals(idSensor, other.idSensor)
				&& Objects.equals(name, other.name) && Objects.equals(status, other.status);
	}
	
	@Override
	public String toString() {
		return "SensorEntity [idSensor=" + idSensor + ", idDevice=" + idDevice + ", status=" + status + ", name=" + name
				+ "]";
	}
	
	

}
