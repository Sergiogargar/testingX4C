package com.xerez4change.cream.entities;

import java.util.Objects;

public class SensorValueEntity {
	
	protected Integer idSensorValue;
	protected Integer idSensor;
	protected Long timestamp;
	protected Float value;
	
	public SensorValueEntity(Integer idSensorValue, Integer idSensor, Long timestamp, Float value) {
		super();
		this.idSensorValue = idSensorValue;
		this.idSensor = idSensor;
		this.timestamp = timestamp;
		this.value = value;
	}
	
	public SensorValueEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdSensorValue() {
		return idSensorValue;
	}
	public void setIdSensorValue(Integer idSensorValue) {
		this.idSensorValue = idSensorValue;
	}
	public Integer getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(Integer idSensor) {
		this.idSensor = idSensor;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idSensor, idSensorValue, timestamp, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorValueEntity other = (SensorValueEntity) obj;
		return Objects.equals(idSensor, other.idSensor) && Objects.equals(idSensorValue, other.idSensorValue)
				&& Objects.equals(timestamp, other.timestamp) && Objects.equals(value, other.value);
	}
	
	@Override
	public String toString() {
		return "SensorValueEntity [idSensorValue=" + idSensorValue + ", idSensor=" + idSensor + ", timestamp="
				+ timestamp + ", value=" + value + "]";
	}
	
	

}
