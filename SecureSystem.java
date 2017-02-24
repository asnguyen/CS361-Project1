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
		//SecureObject  object1  = new SecureObject("hobj", 1);			//high
		//SecureObject  object2  = new SecureObject("lobj", 0);			//low
		SecureSubject subject1 = new SecureSubject("hal", 1);			//high
		SecureSubject subject2 = new SecureSubject("lyle",0);			//low

		//monitor.objectList.add(object2);
		//monitor.objectList.add(object1);
		monitor.subjectList.add(subject2);
		monitor.subjectList.add(subject1);

		Instruction instruct;
		while(sc.hasNextLine())
		{
			String s = sc.nextLine();
			String binaryString = toBinary(s).toString();
			for(int i = 0; i<binaryString.length(); ++i)
			{
				//how Hal will transmit the bit
				if(binaryString.charAt(i) == '1')	//to transmit 1
				{
					instruct = new Instruction("run hal");
					monitor.perform(instruct);
					instruct = new Instruction("create hal obj");
					monitor.perform(instruct);
				}
				else								//to transmit 0
				{
					instruct = new Instruction("run hal");
					monitor.perform(instruct);
				}
				//*************************

				//how Lyle will get the info from hal
				instruct = new Instruction("create lyle obj");
				monitor.perform(instruct);
				instruct = new Instruction("write lyle obj");
				monitor.perform(instruct);
				instruct = new Instruction("read lyle obj");
				monitor.perform(instruct);
				instruct = new Instruction("destroy lyle obj");
				monitor.perform(instruct);
				instruct = new Instruction("run lyle");
				monitor.perform(instruct);
				//*************************

			}
		}
		System.out.println(subject2.output);

	}

	public static StringBuilder toBinary(String s)
	{
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes)
  		{
     		int val = b;
     		for (int i = 0; i < 8; i++)
     		{
        		binary.append((val & 128) == 0 ? 0 : 1);
        		val <<= 1;
     		}
  		}
  		return binary;
	}

}










































/**/