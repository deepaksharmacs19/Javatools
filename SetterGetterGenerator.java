package com.thinking.machines.util;
import java.lang.reflect.*;
import java.io.*;

public class SetterGetterGenerator
{
public static void main(String ff[])
{
if(ff.length==2)
{
if(ff[1].equalsIgnoreCase("constructor=true")==false && ff[1].equalsIgnoreCase("constructor=false")==false)
{
System.out.println("Usage :  java classpath path_to_jar_file com.thinking.machines.util.SetterGetterGenerator class_name constructor=true/false");
return;
}
}

String className=ff[0];
try
{
Class c=Class.forName(className);
Field fields[]=c.getDeclaredFields();
Field field;
Class fieldType;
String fieldName;
String line,setterName,getterName;
TMList<String> list=new TMArrayList<String>();

if(ff.length==1 || (ff.length==2 && ff[1].equalsIgnoreCase("constructor=true")))
{
line="public "+c.getSimpleName()+"()";
list.add(line);
list.add("{");
for(int i=0;i<fields.length;i++)
{
field=fields[i];
line="this."+field.getName()+"="+getDefaultValue(field.getType())+";";
list.add(line);
}
list.add("}");
}

for(int i=0;i<fields.length;i++)
{
field=fields[i];
fieldType=field.getType();
fieldName=field.getName();
String tmp;
tmp=fieldName;
if(tmp.charAt(0)>=97 && tmp.charAt(0)<=122) tmp=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
else tmp=fieldName;
setterName="set"+tmp;
getterName="get"+tmp;
line="public void "+setterName+"("+fieldType.getName()+" "+fieldName+")";
list.add(line);
list.add("{");
line="this."+fieldName+"="+fieldName+";";
list.add(line);
list.add("}");

line="public "+fieldType.getName()+" "+getterName+"()";
list.add(line);
list.add("{");
line="return this."+fieldName+";";
list.add(line);
list.add("}");
}
File file=new File("tmp.tmp");
if(file.exists()) file.delete();
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
TMIterator<String> iterator=list.iterator();
while(iterator.hasNext())
{
line=iterator.next();
randomAccessFile.writeBytes(line+"\r\n");
}
randomAccessFile.close();
System.out.println("Setter/Getter for : "+c.getName()+" generated in file named tmp.tmp");
}catch(ClassNotFoundException classNotFoundException)
{
System.out.println("Invalid classpath");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}

}

public static String getDefaultValue(Class c)
{
String className=c.getName();
if(className.equals("java.lang.Long") || className.equals("long"))return "0";

if(className.equals("java.lang.Float") || className.equals("float"))return "0.0f";

if(className.equals("java.lang.Integer") || className.equals("int"))return "0";

if(className.equals("java.lang.Double") || className.equals("double"))return "0.0";

if(className.equals("java.lang.String") || className.equals("String"))return "\"\"";

if(className.equals("java.lang.Byte") || className.equals("byte"))return "0";

if(className.equals("java.lang.Character") || className.equals("char"))return "' '";

if(className.equals("java.lang.Boolean") || className.equals("boolean"))return "false";
return null;
}
}