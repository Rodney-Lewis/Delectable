package com.delectable.shared.crud;

public interface MarkDEntity<T> extends CRUDEntity<T>{
    boolean isDeleted();
    void setDeleted(boolean deleted);
}
