package com.xerez4change.cream.entities;

import java.util.Objects;

public class GroupEntity {

	protected Integer idGroup;
	protected String name;
	
	public GroupEntity(Integer idGroup, String name) {
		super();
		this.idGroup = idGroup;
		this.name = name;
	}
	
	public GroupEntity() {
		super();
	}

	public Integer getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGroup, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupEntity other = (GroupEntity) obj;
		return Objects.equals(idGroup, other.idGroup) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "GroupEntity [idGroup=" + idGroup + ", name=" + name + "]";
	}
	
	
	
	
	
	
}
