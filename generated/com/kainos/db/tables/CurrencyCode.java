/*
 * This file is generated by jOOQ.
*/
package com.kainos.db.tables;


import com.kainos.db.CurrencySchema;
import com.kainos.db.Keys;
import com.kainos.db.tables.records.CurrencyCodeRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
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
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CurrencyCode extends TableImpl<CurrencyCodeRecord> {

    private static final long serialVersionUID = -839508831;

    /**
     * The reference instance of <code>currency_schema.currency_code</code>
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
     * The column <code>currency_schema.currency_code.code</code>.
     */
    public final TableField<CurrencyCodeRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), this, "");

    /**
     * The column <code>currency_schema.currency_code.description</code>.
     */
    public final TableField<CurrencyCodeRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * Create a <code>currency_schema.currency_code</code> table reference
     */
    public CurrencyCode() {
        this("currency_code", null);
    }

    /**
     * Create an aliased <code>currency_schema.currency_code</code> table reference
     */
    public CurrencyCode(String alias) {
        this(alias, CURRENCY_CODE);
    }

    private CurrencyCode(String alias, Table<CurrencyCodeRecord> aliased) {
        this(alias, aliased, null);
    }

    private CurrencyCode(String alias, Table<CurrencyCodeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return CurrencySchema.CURRENCY_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CurrencyCodeRecord> getPrimaryKey() {
        return Keys.CURRENCY_CODE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CurrencyCodeRecord>> getKeys() {
        return Arrays.<UniqueKey<CurrencyCodeRecord>>asList(Keys.CURRENCY_CODE_PKEY);
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
    @Override
    public CurrencyCode rename(String name) {
        return new CurrencyCode(name, null);
    }
}
