import java.util.Iterator;
import java.util.*;
import java.io.FileInputStream;
import java.io.*;

public class SecureSystem
{
	public static void main(String [] args)
	{
		FileInputStream fstream;
		Scanner sc;
		try	
		{
			fstream = new FileInputStream(args[0]);
			sc = new Scanner(fstream);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("NO FILE FOUND");
			sc = new Scanner(System.in);
		}

		ResourceMonitor monitor = new ResourceMonitor();

		//in creation 1 = high security 0 = low security
		SecureObject  object1  = new SecureObject("hobj", 1);			//high
		SecureObject  object2  = new SecureObject("lobj", 0);			//low
		SecureSubject subject1 = new SecureSubject("hal", 1);			//high
		SecureSubject subject2 = new SecureSubject("lyle",0);			//low

		monitor.objectList.add(object2);
		monitor.objectList.add(object1);
		monitor.subjectList.add(subject2);
		monitor.subjectList.add(subject1);

		Instruction instruct;
		while(sc.hasNextLine())
		{
			String s = sc.nextLine();
			instruct = new Instruction(s);
			monitor.perform(instruct);
		}

	}
}










































/**/