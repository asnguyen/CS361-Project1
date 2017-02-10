import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class ResourceMonitor
{
	ArrayList objectList;
	ArrayList subjectList;
	ObjectManage manager = new ObjectManage();
	final Instruction bad = new Instruction("BAD","BAD","BAD",-1);
	SecureSubject subject = new SecureSubject();
	SecureObject object = new SecureObject();
	SecureObject zero = new SecureObject("zero",0);

	public ResourceMonitor()
	{
		objectList  = new ArrayList();
		subjectList = new ArrayList();

	}

	public ResourceMonitor(ArrayList o, ArrayList s)
	{
		objectList  = o;
		subjectList = s;
	}

	public boolean safeInstruction(Instruction i)
	{
		String sSubject = i.subject;
		String sObject = i.object;

		object = objectSearch(sObject,object);
		subject = subjectSearch(sSubject,subject);

		if((i.type.equalsIgnoreCase("read")|| i.type.equalsIgnoreCase("write"))&&(!subject.name.equals("SEARCH FAILED") && !object.name.equals("SEARCH FAILED")))
			return true;

		return false;
	}

	public boolean BLP(Instruction i)
	{
		if(i.type.equalsIgnoreCase("read"))
		{
			if(subject.security >= object.security)
			{
				return true;
			}
		}
		if(i.type.equalsIgnoreCase("write"))
		{
			if(subject.security <= object.security)
			{
				return true;
			}
		}
		return false;
	}

	public void perform(Instruction i)
	{
		if(safeInstruction(i) && BLP(i) && i.type.equalsIgnoreCase("read"))
		{
			manager.read(subject,object);
		}
		else if(safeInstruction(i) && BLP(i) && i.type.equalsIgnoreCase("write"))
		{
			manager.write(subject,object,i.value);
		}
		else if(safeInstruction(i) && !BLP(i) && i.type.equalsIgnoreCase("read"))
		{
			manager.read(subject,zero);
		}
		viewCurrentState(i);
	}

	private SecureObject objectSearch(String s, SecureObject object)
	{
		SecureObject fail = new SecureObject("SEARCH FAILED",0);

		for(int i = 0; i<objectList.size();++i)
		{
			object = (SecureObject)objectList.get(i);
			if(object.name.equals(s))
			{
				return object;
			}
		}
		return fail;
	}	

	private SecureSubject subjectSearch(String s, SecureSubject subject)
	{
		SecureSubject fail = new SecureSubject("SEARCH FAILED");
		for(int i = 0; i<subjectList.size();++i)
		{
			subject = (SecureSubject)subjectList.get(i);
			if(subject.name.equals(s))
			{
				return subject;
			}
		}
		return fail;
	}

	public void viewCurrentState(Instruction i)
	{
		if(!safeInstruction(i))
		{
			System.out.println("Bad Instruction\nThe current state is:");

		}
		else if(i.type.equalsIgnoreCase("write"))
		{
			System.out.println(i.subject+" writes value of "+i.value+" to "+i.object);
			System.out.println("The current state is:");
		}
		else if(i.type.equalsIgnoreCase("read"))
		{
			System.out.println(i.subject+" reads "+i.object);
			System.out.println("The current state is:");
		}
		for(int j = 0; j<objectList.size();++j)
		{
			object = (SecureObject)objectList.get(j);
			System.out.println("\t"+object.name+" has value: "+object.value);
		}
		for(int j = 0; j<subjectList.size();++j)
		{
			subject = (SecureSubject)subjectList.get(j);
			System.out.println("\t"+subject.name+" has recently read: "+subject.temp);
		}
		System.out.print("\n");
	} 

	class ObjectManage
	{

		public void write(SecureSubject s, SecureObject o, int newValue)
		{
			o.value = newValue;
		}

		public void read(SecureSubject s, SecureObject o)
		{
			s.temp = o.value;
		}

	}

}


































