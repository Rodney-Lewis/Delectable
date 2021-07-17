package com.delectable.shared.crud;

public interface CRUSoftDeleteEntity<T> extends CRUDEntity<T>{
    boolean isDeleted();
    void setDeleted(boolean deleted);
}
