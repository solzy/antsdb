/*-------------------------------------------------------------------------------------------------
 _______ __   _ _______ _______ ______  ______
 |_____| | \  |    |    |______ |     \ |_____]
 |     | |  \_|    |    ______| |_____/ |_____]

 Copyright (c) 2016, antsdb.com and/or its affiliates. All rights reserved. *-xguo0<@

 This program is free software: you can redistribute it and/or modify it under the terms of the
 GNU GNU Lesser General Public License, version 3, as published by the Free Software Foundation.

 You should have received a copy of the GNU Affero General Public License along with this program.
 If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html>
-------------------------------------------------------------------------------------------------*/
package com.antsdb.saltedfish.sql.meta;

import com.antsdb.saltedfish.nosql.SlowRow;
import com.antsdb.saltedfish.sql.Orca;

/**
 * 
 * @author *-xguo0<@
 */
public class ForeignKeyMeta extends RuleMeta<ForeignKeyMeta> {

	public ForeignKeyMeta(Orca orca, TableMeta table) {
		super(orca, Rule.ForeignKey, table.getId());
		setTableId(table.getId());
	}

	public ForeignKeyMeta(SlowRow row) {
		super(row);
	}

	public int getParentTable() {
        return (int)row.get(ColumnId.sysrule_parent_table_id.getId());
	}
	
	public void setParentTable(int tableId) {
        row.set(ColumnId.sysrule_parent_table_id.getId(), tableId);
	}
	
    @Override
    public ForeignKeyMeta clone() {
        ForeignKeyMeta result = new ForeignKeyMeta(this.row.clone());
        return result;
    }

}
