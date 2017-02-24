import java.util.Iterator;
import java.util.*;
import java.io.FileInputStream;
import java.io.*;

public class CovertChannel
{
	public static void main(String [] args) throws java.io.IOException
	{
		FileInputStream fstream;
		Scanner sc;
		boolean verbose = false;
		FileWriter fw1 = new FileWriter(".log.txt");
		FileWriter fw2 = new FileWriter(".file.txt");

		if(args[0].equalsIgnoreCase("v"))
		{
			verbose = true;
			fw1 = new FileWriter("log.txt");
			fw2 = new FileWriter(args[1].replace(".txt",".out"));
			try	
			{
				fstream = new FileInputStream(args[1]);
				sc = new Scanner(fstream);
			}
			catch(FileNotFoundException e)
			{
				System.out.println("NO FILE FOUND");
				sc = new Scanner(System.in);
			}
		}
		else
		{
			fw2 = new FileWriter(args[0].replace(".txt",".out"));
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
		}
		ResourceMonitor monitor = new ResourceMonitor();

		//in creation 1 = high security 0 = low security
		SecureSubject subject1 = new SecureSubject("hal", 1);			//high
		SecureSubject subject2 = new SecureSubject("lyle",0);			//low

		monitor.subjectList.add(subject2);
		monitor.subjectList.add(subject1);

		Instruction instruct;
		long startTime = System.currentTimeMillis();
		while(sc.hasNextLine())
		{
			String s = sc.nextLine();
			String binaryString = toBinary(s).toString();
			for(int i = 0; i<binaryString.length(); ++i)
			{
				if(binaryString.charAt(i) == '0')	//to transmit 0
				{
					instruct = new Instruction("run hal");
					monitor.perform(instruct);
					if(verbose)
						fw1.write("RUN HAL\n");
					instruct = new Instruction("create hal obj");
					monitor.perform(instruct);
					if(verbose)
						fw1.write("CREATE HAL OBJ\n");
				}
				else								//to transmit 1
				{
					instruct = new Instruction("run hal");
					monitor.perform(instruct);
					if(verbose)
						fw1.write("RUN HAL\n");
				}
				instruct = new Instruction("create lyle obj");
				monitor.perform(instruct);
				if(verbose)
					fw1.write("CREATE LYLE OBJ\n");
				instruct = new Instruction("write lyle obj 1");
				monitor.perform(instruct);
				if(verbose)
					fw1.write("WRITE LYLE OBJ 1\n");
				instruct = new Instruction("read lyle obj");
				monitor.perform(instruct);
				if(verbose)
					fw1.write("READ LYLE OBJ\n");
				instruct = new Instruction("destroy lyle obj");
				monitor.perform(instruct);
				if(verbose)
					fw1.write("DESTROY LYLE OBJ\n");
				instruct = new Instruction("run lyle");
				monitor.perform(instruct);
				if(verbose)
					fw1.write("RUN LYLE\n");

			}
			fw2.write(subject2.output+"\n");
			subject2.clear();
		}
		long estimateTime = System.currentTimeMillis() - startTime;
		if(verbose)
		{
			long size = (new File(args[1])).length();
			double rate = ((double)size*8)/(estimateTime);
			System.out.println(args[1]+" \t "+size+"bytes \t "+ String.format("%.2f bits/ms",rate));
		}
		else
		{
			long size = (new File(args[0])).length();
			double rate = ((double)size*8)/(estimateTime);
			System.out.println(args[0]+" \t "+size+" bytes \t "+String.format("%.2f bits/ms",rate));
		}
		fw1.close();
		fw2.close();
		

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