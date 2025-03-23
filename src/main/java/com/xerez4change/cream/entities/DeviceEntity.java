package com.xerez4change.cream.entities;

import java.util.Objects;

public class DeviceEntity {
	
	protected Integer idDevice;
	protected Integer idGroup;
	protected String nombre;
	
	public DeviceEntity(Integer idDevice, Integer idGroup, String nombre) {
		super();
		this.idDevice = idDevice;
		this.idGroup = idGroup;
		this.nombre = nombre;
	}
	
	public DeviceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdDevice() {
		return idDevice;
	}
	public void setIdDevice(Integer idDevice) {
		this.idDevice = idDevice;
	}
	public Integer getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idDevice, idGroup, nombre);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceEntity other = (DeviceEntity) obj;
		return Objects.equals(idDevice, other.idDevice) && Objects.equals(idGroup, other.idGroup)
				&& Objects.equals(nombre, other.nombre);
	}
	
	@Override
	public String toString() {
		return "DeviceEntity [idDevice=" + idDevice + ", idGroup=" + idGroup + ", nombre=" + nombre + "]";
	}
	
	
}
