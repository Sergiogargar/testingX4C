package com.xerez4change.cream;

import java.util.Objects;

public class ActuatorEntity {

	protected Integer idActuator;
	protected String name;
	protected Integer status;
	protected Integer idDevice;
	
	
	public ActuatorEntity(Integer idActuator, String name, Integer status, Integer idDevice) {
		super();
		this.idActuator = idActuator;
		this.name = name;
		this.status = status;
		this.idDevice = idDevice;
	}


	public ActuatorEntity() {
		super();
	}


	public Integer getIdActuator() {
		return idActuator;
	}


	public void setIdActuator(Integer idActuator) {
		this.idActuator = idActuator;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getIdDevice() {
		return idDevice;
	}


	public void setIdDevice(Integer idDevice) {
		this.idDevice = idDevice;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idActuator, idDevice, name, status);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActuatorEntity other = (ActuatorEntity) obj;
		return Objects.equals(idActuator, other.idActuator) && Objects.equals(idDevice, other.idDevice)
				&& Objects.equals(name, other.name) && Objects.equals(status, other.status);
	}


	@Override
	public String toString() {
		return "ActuatorEntity [idActuator=" + idActuator + ", name=" + name + ", status=" + status + ", idDevice="
				+ idDevice + "]";
	}
	
}
