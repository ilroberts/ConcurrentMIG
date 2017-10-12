/**
 * This class is generated by jOOQ
 */
package com.kainos.db.tables.records;


import com.kainos.db.tables.CurrencyCode;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


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
public class CurrencyCodeRecord extends UpdatableRecordImpl<CurrencyCodeRecord> implements Record2<String, String> {

	private static final long serialVersionUID = -1568736119;

	/**
	 * Setter for <code>CURRENCY_SCHEMA.CURRENCY_CODE.CODE</code>.
	 */
	public void setCode(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>CURRENCY_SCHEMA.CURRENCY_CODE.CODE</code>.
	 */
	public String getCode() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>CURRENCY_SCHEMA.CURRENCY_CODE.DESCRIPTION</code>.
	 */
	public void setDescription(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>CURRENCY_SCHEMA.CURRENCY_CODE.DESCRIPTION</code>.
	 */
	public String getDescription() {
		return (String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<String> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<String, String> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<String, String> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return CurrencyCode.CURRENCY_CODE.CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return CurrencyCode.CURRENCY_CODE.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CurrencyCodeRecord value1(String value) {
		setCode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CurrencyCodeRecord value2(String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CurrencyCodeRecord values(String value1, String value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached CurrencyCodeRecord
	 */
	public CurrencyCodeRecord() {
		super(CurrencyCode.CURRENCY_CODE);
	}

	/**
	 * Create a detached, initialised CurrencyCodeRecord
	 */
	public CurrencyCodeRecord(String code, String description) {
		super(CurrencyCode.CURRENCY_CODE);

		setValue(0, code);
		setValue(1, description);
	}
}