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
package com.antsdb.saltedfish.sql.vdm;

import java.util.function.Consumer;

import com.antsdb.saltedfish.cpp.Heap;
import com.antsdb.saltedfish.sql.DataType;
import com.antsdb.saltedfish.sql.planner.PlannerField;

/**
 * 
 * @author wgu0
 *
 */
public class FieldValue extends Operator {
    PlannerField field;
    
    public FieldValue(PlannerField field) {
    	this.field = field;
    }
    
    @Override
    public long eval(VdmContext ctx, Heap heap, Parameters params, long pRecord) {
        long pValue = Record.get(pRecord, this.field.getIndex());
    	    return pValue;
    }

    @Override
    public DataType getReturnType() {
        return this.field.getType();
    }

    @Override
    public void visit(Consumer<Operator> visitor) {
        visitor.accept(this);
    }

	@Override
	public String toString() {
		return this.field.toString();
	}

	public String getName() {
		return this.field.getName();
	}
	
	public PlannerField getField() {
		return this.field;
	}
}
