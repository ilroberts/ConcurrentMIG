/**
 * This class is generated by jOOQ
 */
package com.kainos.db.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CurrencyCode implements Serializable {

	private static final long serialVersionUID = 1953759477;

	private String code;
	private String description;

	public CurrencyCode() {}

	public CurrencyCode(CurrencyCode value) {
		this.code = value.code;
		this.description = value.description;
	}

	public CurrencyCode(
		String code,
		String description
	) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CurrencyCode (");

		sb.append(code);
		sb.append(", ").append(description);

		sb.append(")");
		return sb.toString();
	}
}
