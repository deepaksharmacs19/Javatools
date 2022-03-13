package com.thinking.machines.util;

public class TMArrayList<T> implements TMList<T>
{
private Object collection[];
private int size;

public class TMArrayListIterator<T> implements TMIterator<T>
{
private int index;
TMArrayListIterator()
{
this.index=0;
}
public T next()
{
if(index==size)throw new InvalidIteratorException("Iterator has no more items");
T data=(T)get(index);
index++;
return data;
}
public boolean hasNext()
{
return index!=size;
}
}

public TMIterator<T> iterator()
{
return new TMArrayListIterator();
}

public TMArrayList()
{
this.collection=new Object[10];
this.size=0;
}

public void add(T data)
{
if(this.size==this.collection.length)
{
Object tmp[]=new Object[size+10];
for(int i=0;i<this.collection.length;i++) tmp[i]=this.collection[i];
this.collection=tmp;
}
this.collection[size]=data;
this.size++;
}

public void add(int index,T data){
if(index<0 || index>this.size)throw new IndexOutOfBoundsException("Invalid Index : "+index);
if(index==this.collection.length)add((T)data);
if(this.size==this.collection.length)
{
Object []tmp=new Object[this.size+10];
for(int i=0;i<this.collection.length;i++) tmp[i]=(T)this.collection[i];
this.collection=tmp;
}
for(int i=this.size-1;i>=index;i++)this.collection[i+1]=(T)this.collection[i];
this.collection[index]=data;
this.size++;
}

public void insert(int index,T data){
add(index,(T)data);
}

public int size(){
return this.size;
}

public void removeAll(){
this.size=0;
}

public void clear(){
this.size=0;
}

public T get(int index){
if(index<0 || index>this.size) throw new IndexOutOfBoundsException("Invalid Index : "+ index);
return (T)this.collection[index];
}

public void update(T data,int index){
if(index<0 || index>this.size) throw new IndexOutOfBoundsException("Invalid Index : "+index);
this.collection[index]=data;
}


public T removeAt(int index)
{
throw new IndexOutOfBoundsException();
}

public void copyTo(TMList other){
other.clear();
if(this.size==0)
{
System.out.println("There is nothing to copy");
return;
}
for(int i=0;i<this.size();i++) other.add((T)this.get(i));
}

public void copyFrom(TMList<T> other){
this.clear();
if(other.size()==0)
{
System.out.println("There is nothing to copy");
return;
}
for(int i=0;i<other.size();i++) this.add((T)other.get(i));
}

public void appendTo(TMList other){
for(int i=0;i<this.size();i++) other.add((T)this.get(i));
}

public void appendFrom(TMList other){
for(int i=0;i<other.size();i++) this.add((T)other.get(i));
}

public void forEach(TMListItemAcceptor<T> i)
{
if(i==null) return;
for(int e=0;e<this.size();e++) i.accept((T)collection[e]);
}
}