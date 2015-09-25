package com.guillaume.dbbackuptool.bo;

import java.sql.DatabaseMetaData;

public class MySqlTableField extends TableField {

	public MySqlTableField(String name, DatabaseMetaData metaData) {
		super(name, metaData);
	}

	@Override
	public String getCreateStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
