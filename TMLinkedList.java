package com.thinking.machines.util;

public class TMLinkedList<T> implements TMList<T>
{
class Node<T>
{
public T data;
public Node next;
public Node()
{
this.next=null;
}
}

public class TMLinkedListIterator<T> implements TMIterator<T>
{
private Node<T> ptr;
TMLinkedListIterator(Node<T> ptr)
{
this.ptr=ptr;
}
public boolean hasNext()
{
return this.ptr!=null;
}
public T next()
{
if(ptr==null) throw new InvalidIteratorException("Iterator has no more elements");
T data=this.ptr.data;
this.ptr=this.ptr.next;
return data;
}
}

public TMIterator<T> iterator()
{
return new TMLinkedListIterator(this.start);
}

private Node<T> start;
private Node<T> end;
private int size;

public TMLinkedList()
{
this.start=null;
this.end=null;
this.size=0;
}

public void add(T data)
{
Node<T> t=new Node();
t.data=data;
if(this.start==null)
{
this.start=t;
this.end=t;
}
else{
this.end.next=t;
end=t;
}
this.size++;
}

public void add(int index,T data)
{
Node<T> p1,p2,t;
t=new Node();
t.data=data;
if(index<0) throw new IndexOutOfBoundsException();
if(index>=this.size) this.add(data);

if(index==0)
{
t.next=start;
start=t;
}
else 
{
p1=this.start;
p2=null;
for(int i=0;i<index;i++)
{
p2=p1;
p1=p1.next;
}
t.next=p1;
p2.next=t;
}
this.size++;
}

public void insert(int index,T data)
{
add(index,data);
}

public int size()
{
return this.size;
}

public void removeAll(){
clear();
}

public T removeAt(int index){
Node<T> p1,p2,t;
p1=this.start;
p2=null;
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid Index : "+index);
if(start==end)
{
this.clear();
return start.data;
}
if(index==0)
{
start=start.next;
size--;
return start.data;
}

for(int i=0;i<index;i++)
{
p2=p1;
p1=p1.next;
}
t=p1;
p2.next=p1.next;
if(this.end==p1) this.end=p2;
this.size--;
return t.data;
}

public void clear(){
this.size=0;
this.start=null;
this.end=null;
}

public T get(int index){
if(index<0 || index>this.size) throw new IndexOutOfBoundsException("Invalid Index : "+index);
Node<T> t,p1;
p1=start;
for(int i=0;i<index;i++,p1=p1.next) if(index==i) break;
return p1.data;
}

public void update(T data,int index){
if(index<0 || index>=this.size()) throw new IndexOutOfBoundsException("Invalid Index : "+index);
if(index==0)
{
this.start.data=data;
return;
}
if(index==this.size()-1)
{
this.end.data=data;
return;
}

Node<T> p1=this.start;
for(int i=0;i<index;i++)
{
p1=p1.next;
}
p1.data=data;
}

public void copyTo(TMList<T> other){
other.clear();
for(int i=0;i<this.size();i++) other.add(this.get(i));
}

public void copyFrom(TMList<T> other){
this.clear();
for(int i=0;i<other.size();i++) this.add(other.get(i));
}

public void appendTo(TMList<T> other){
for(int i=0;i<this.size();i++) other.add(this.get(i));
}

public void appendFrom(TMList<T> other){
for(int i=0;i<other.size();i++) this.add(other.get(i));
}


public void forEach(TMListItemAcceptor<T> i)
{
if(i==null) return;
Node<T> t;
for(t=this.start;t!=null;t=t.next) i.accept(t.data);
}

}