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

		if(i.type.equalsIgnoreCase("run") && !subject.name.equals("SEARCH FAILED") )
		{
			return true;
		}
		if(i.type.equalsIgnoreCase("create") && !subject.name.equals("SEARCH FAILED") && object.name.equals("SEARCH FAILED"))
		{
			//System.out.println(i.type.equalsIgnoreCase("create") && !subject.name.equals("SEARCH FAILED") && object.name.equals("SEARCH FAILED"));
			return true;
		}
		if((i.type.equalsIgnoreCase("read") || i.type.equalsIgnoreCase("write") || i.type.equalsIgnoreCase("destroy")) || i.type.equalsIgnoreCase("create") &&(!subject.name.equals("SEARCH FAILED") && !object.name.equals("SEARCH FAILED")))
		{	
			return true;
		}
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
		if(i.type.equalsIgnoreCase("destroy"))
		{
			if(subject.security<= object.security)
			{
				return true;
			}
		}
		return false;
	}

	public void perform(Instruction i)
	{
		if(safeInstruction(i) && i.type.equalsIgnoreCase("run"))
		{
			subject.run();
		}
		else if(safeInstruction(i) && i.type.equalsIgnoreCase("destroy"))
		{
			manager.destroy(subject, object);
		}
		else if(safeInstruction(i) && i.type.equalsIgnoreCase("create"))
		{
			manager.create(subject,i.object);
		}
		else if(safeInstruction(i) && BLP(i) && i.type.equalsIgnoreCase("read"))
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
		//viewCurrentState(i);
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
		String instruction = i.type;
		if(!safeInstruction(i))
		{
			System.out.println("Bad Instruction\nThe current state is:");

		}
		switch (i.type.toLowerCase())
		{
			case "write":
				System.out.println(i.subject+" writes value of "+i.value+" to "+i.object);
				System.out.println("The current state is:");
				break;
			case "read":
				System.out.println(i.subject+" reads "+i.object);
				System.out.println("The current state is:");
				break;
			case "create":
				System.out.println(i.subject+" creates "+i.object);
				System.out.println("The current state is:");
				break;
			case "destroy":
				System.out.println(i.subject+" destroys "+i.object);
				System.out.println("The current state is:");
				break;
			case "run":
				System.out.println(i.subject + " runs");
				System.out.println("The current state is:");
				break;
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

		public void create(SecureSubject s, String name)
		{
			SecureObject o = new SecureObject();
			o = objectSearch(name,o);
			if(o.name.equalsIgnoreCase("SEARCH FAILED"))
				objectList.add(new SecureObject(name, s.security));					//will this be a problem since the new object was created on the stack?
			//nothing
		}

		public void destroy(SecureSubject s, SecureObject o)
		{
			objectList.remove(o);												//removes it from the list
			o = null; 															//will this "destory" the object?
		}

	}

}


































