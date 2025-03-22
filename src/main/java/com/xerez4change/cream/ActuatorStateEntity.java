package com.xerez4change.cream;

import java.util.Objects;

public class ActuatorStateEntity {
	
	protected Integer idState;
	protected Integer idActuator;
	protected Float value;
	protected Long timestamp;
	
	public ActuatorStateEntity(Integer idState, Integer idActuator, Float value, Long timestamp) {
		super();
		this.idState = idState;
		this.idActuator = idActuator;
		this.value = value;
		this.timestamp = timestamp;
	}
	
	public ActuatorStateEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getIdState() {
		return idState;
	}
	public void setIdState(Integer idState) {
		this.idState = idState;
	}
	public Integer getIdActuator() {
		return idActuator;
	}
	public void setIdActuator(Integer idActuator) {
		this.idActuator = idActuator;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idActuator, idState, timestamp, value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActuatorStateEntity other = (ActuatorStateEntity) obj;
		return Objects.equals(idActuator, other.idActuator) && Objects.equals(idState, other.idState)
				&& Objects.equals(timestamp, other.timestamp) && Objects.equals(value, other.value);
	}
	
	@Override
	public String toString() {
		return "ActuatorStateEntity [idState=" + idState + ", idActuator=" + idActuator + ", value=" + value
				+ ", timestamp=" + timestamp + "]";
	}
	
	

}
