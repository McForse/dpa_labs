package ru.mirea.n03pr11;

import java.util.Objects;

public class Product {
	private int code;
	private String name;

	public Product(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return code == product.code;
	}

	@Override
	public int hashCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Product{" +
				"code=" + code +
				", name='" + name + '\'' +
				'}';
	}
}
