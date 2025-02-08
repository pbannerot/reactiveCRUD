package com.esolution.reactive.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("locations")
public class Location {

	@Id
	@Column("id")
	private UUID id;
	@Column("city")
	private String city;
	@Column("country")
	private Country country;
	
	public Location() {
	}

	private Location(Builder builder) {
		this.city = builder.city;
		this.country = builder.country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public UUID getId() {
		return id;
	}

	public static class Builder {
//		private UUID id;
		private String city;
		private Country country;

		public Builder() {
//			this.id = UUID.randomUUID();
		}

//		public Builder id(UUID id) {
//			this.id = id;
//			return this;
//		}

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder country(Country country) {
			this.country = country;
			return this;
		}

		public Location build() {
			return new Location(this);
		}
	}

}
