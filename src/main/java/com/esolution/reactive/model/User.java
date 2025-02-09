package com.esolution.reactive.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {
	@Id
	@Column("id")
	private UUID id;

	@Column("first_name")
	private String firstName;

	@Column("last_name")
	private String lastName;

	@Column("location_id")
	private UUID locationId;

	@Transient
	private Location location;
	
	public User() {
//		this.locationId = location != null ? location.getId() : null;
	}
	
	private User(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.location = builder.location;
		this.locationId = location != null ? location.getId() : null; // builder.locationId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public UUID getId() {
		return id;
	}

	public void setLocation(Location location) {
		this.location = location;
		this.locationId = location != null ? location.getId() : null;
	}

	public Location getLocation() {
		return location;
	}
	
	public static class Builder {
//		private UUID id;
		private String firstName;
		private String lastName;
		private Location location;
		private UUID locationId;

		public Builder() {
//			this.id = UUID.randomUUID();
		}

//		public Builder id(UUID id) {
//			this.id = id;
//			return this;
//		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder location(Location location) {
			this.location = location;
			this.locationId = location != null ? location.getId() : null;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}