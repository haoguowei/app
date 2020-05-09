package com.hao.app.commons.entity.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString
public class TableKey implements Serializable {

    private int projectId;

    private int type3;

    public TableKey() {

    }

    public TableKey(int projectId, int type3) {
        this.projectId = projectId;
        this.type3 = type3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableKey tableKey = (TableKey) o;
        return projectId == tableKey.projectId &&
                type3 == tableKey.type3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, type3);
    }
}
