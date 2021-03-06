/**
 * This class is generated by jOOQ
 */
package com.kainos.db.tables;


import com.kainos.db.CurrencySchema;
import com.kainos.db.Keys;
import com.kainos.db.tables.records.CurrencyCodeRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class CurrencyCode extends TableImpl<CurrencyCodeRecord> {

	private static final long serialVersionUID = 1644976434;

	/**
	 * The reference instance of <code>CURRENCY_SCHEMA.CURRENCY_CODE</code>
	 */
	public static final CurrencyCode CURRENCY_CODE = new CurrencyCode();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<CurrencyCodeRecord> getRecordType() {
		return CurrencyCodeRecord.class;
	}

	/**
	 * The column <code>CURRENCY_SCHEMA.CURRENCY_CODE.CODE</code>.
	 */
	public final TableField<CurrencyCodeRecord, String> CODE = createField("CODE", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), this, "");

	/**
	 * The column <code>CURRENCY_SCHEMA.CURRENCY_CODE.DESCRIPTION</code>.
	 */
	public final TableField<CurrencyCodeRecord, String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * Create a <code>CURRENCY_SCHEMA.CURRENCY_CODE</code> table reference
	 */
	public CurrencyCode() {
		this("CURRENCY_CODE", null);
	}

	/**
	 * Create an aliased <code>CURRENCY_SCHEMA.CURRENCY_CODE</code> table reference
	 */
	public CurrencyCode(String alias) {
		this(alias, CURRENCY_CODE);
	}

	private CurrencyCode(String alias, Table<CurrencyCodeRecord> aliased) {
		this(alias, aliased, null);
	}

	private CurrencyCode(String alias, Table<CurrencyCodeRecord> aliased, Field<?>[] parameters) {
		super(alias, CurrencySchema.CURRENCY_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<CurrencyCodeRecord> getPrimaryKey() {
		return Keys.CONSTRAINT_F;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<CurrencyCodeRecord>> getKeys() {
		return Arrays.<UniqueKey<CurrencyCodeRecord>>asList(Keys.CONSTRAINT_F);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CurrencyCode as(String alias) {
		return new CurrencyCode(alias, this);
	}

	/**
	 * Rename this table
	 */
	public CurrencyCode rename(String name) {
		return new CurrencyCode(name, null);
	}
}
