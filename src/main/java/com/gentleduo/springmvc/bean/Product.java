package com.gentleduo.springmvc.bean;

public class Product {

	private String brand;
	private String type;
	private String color;
	private String price;

	public Product() {

	}

	
	public Product(String brand, String type, String color, String price) {
		super();
		this.brand = brand;
		this.type = type;
		this.color = color;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
