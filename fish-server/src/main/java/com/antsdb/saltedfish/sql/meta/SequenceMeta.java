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
import com.antsdb.saltedfish.sql.vdm.ObjectName;

public class SequenceMeta {
    SlowRow row;

    public SequenceMeta(ObjectName name, int id) {
        this.row = new SlowRow(getKey(name));
        setNamespace(name.getNamespace());
        setSequenceName(name.getTableName());
    }
    
    public SequenceMeta(Orca orca, ObjectName name) {
        this(name, (int)orca.getIdentityService().getNextGlobalId());
    }
    
    public SequenceMeta(SlowRow row) {
        super();
        this.row = row;
    }

    public byte[] getKey() {
        return this.row.getKey();
    }
    
    static String getKey(ObjectName name) {
        return name.getNamespace() + "." + name.getTableName();
    }
    
    public int getId() {
        return (Integer)row.get(ColumnId.syssequence_id.getId());
    }
    
    public void setId(int id) {
        row.set(ColumnId.syssequence_id.getId(), id);
    }
    
    public String getNamespace() {
        return (String)row.get(ColumnId.syssequence_namespace.getId());
    }
    
    SequenceMeta setNamespace(String name) {
        row.set(ColumnId.syssequence_namespace.getId(), name);
        return this;
    }
    
    public String getSequenceName() {
        return (String)row.get(ColumnId.syssequence_sequence_name.getId());
    }
    
    SequenceMeta setSequenceName(String name) {
        row.set(ColumnId.syssequence_sequence_name.getId(), name);
        return this;
    }
    
    public long getLastNumber() {
        return (Long)row.get(ColumnId.syssequence_last_number.getId());
    }
    
    public SequenceMeta setLastNumber(long n) {
        row.set(ColumnId.syssequence_last_number.getId(), n);
        return this;
    }

    public long getSeed() {
    	Long value = (Long)row.get(ColumnId.syssequence_seed.getId());
    	return (value != null) ? value : 0;
    }
    
    public SequenceMeta setSeed(long value) {
        row.set(ColumnId.syssequence_seed.getId(), value);
        return this;
    }
    
    public long getIncrement() {
    	Long value = (Long)row.get(ColumnId.syssequence_increment.getId());
    	return (value != null) ? value : 0;
    }
    
    public SequenceMeta setIncrement(long value) {
        row.set(ColumnId.syssequence_increment.getId(), value);
        return this;
    }
    
    @Override
    public SequenceMeta clone() {
        SlowRow newrow = this.row.clone();
        SequenceMeta seq = new SequenceMeta(newrow);
        return seq;
    }
    
    public long getTransactionTimestamp() {
    	return this.row.getTrxTimestamp();
    }
}
