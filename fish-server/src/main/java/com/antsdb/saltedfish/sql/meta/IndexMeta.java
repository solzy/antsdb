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
import com.antsdb.saltedfish.sql.vdm.KeyMaker;
import com.antsdb.saltedfish.sql.vdm.ObjectName;

import static com.antsdb.saltedfish.sql.OrcaConstant.*;

public class IndexMeta extends RuleMeta<IndexMeta> {
    static final ObjectName SEQ_NAME = new ObjectName(SYSNS, TABLENAME_SYSRULE);

    KeyMaker keyMaker;
    
    public IndexMeta(Orca orca, TableMeta owner) {
        super(orca, Rule.Index, owner.getId());
        setTableId(owner.getId());
    }

    public IndexMeta(SlowRow row) {
        super(row);
    }

    public void setUnique(boolean isUnique) {
        row.set(ColumnId.sysrule_is_unique.getId(), isUnique ? 1 : 0);
    }

    public boolean isUnique() {
        	Object obj = row.get(ColumnId.sysrule_is_unique.getId());
        	if (obj instanceof Integer) {
        		return ((Integer)obj) == 1;
        	}
        	else {
        		return false;
        	}
    }
    
	public KeyMaker getKeyMaker() {
		return this.keyMaker;
	}

	/**
	 * dont be confused with table_id. table_id is the owner of this index. index_table_id is the humpback table used
	 * to store index data 
	 * 
	 * @param value
	 */
	public void setIndexTableId(int value) {
		row.set(ColumnId.sysrule_index_table_id.getId(), value);
	}
	
	public int getIndexTableId() {
		int value = (Integer)row.get(ColumnId.sysrule_index_table_id.getId());
		return value;
	}

	public String getExternalName() {
		String value = (String)row.get(ColumnId.sysrule_index_external_name.getId());
		return value;
	}

	/**
	 * we need an unique external name due to how truncate works. truncate basically creates new set of tables for
	 * base table and indexes before deleting the old ones. we want the new indexes have a different name than the old 
	 * ones. 
	 */
	public void genUniqueExternalName(TableMeta table, String indexName, int globalId) {
	    String externalName = String.format("%s-%s-%x", table.getTableName(), indexName, globalId);
		setExternalName(externalName);
	}
	
	public void setExternalName(String value) {
		row.set(ColumnId.sysrule_index_external_name.getId(), value);
	}
	
	public void setFullText(boolean value) {
		row.set(ColumnId.sysrule_is_fulltext.getId(), value);
	}
	
	public boolean isFullText() {
		Boolean value = (Boolean)row.get(ColumnId.sysrule_is_fulltext.getId());
		return value != null ? value : false;
	}

	@Override
    public IndexMeta clone() {
        SlowRow clone = this.row.clone();
        IndexMeta result = new IndexMeta(clone);
        result.keyMaker = this.keyMaker;
        return result;
    }
}
