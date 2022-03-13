package com.thinking.machines.util;

public interface TMList<T>
{
public void add(T data);
public void add(int index,T data);
public void insert(int index,T data);
public int size();
public void removeAll();
public T removeAt(int index);
public void clear();
public T get(int index);
public void update(T data,int index);
public void copyTo(TMList<T> other);
public void copyFrom(TMList<T> other);
public void appendTo(TMList<T> other);
public void appendFrom(TMList<T> other);
public TMIterator<T> iterator();
public void forEach(TMListItemAcceptor<T> i);
}