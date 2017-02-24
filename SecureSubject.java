import java.io.*;
import java.util.*;

public class SecureSubject
{
	String name;
	int temp;
	int security;
	String sbyte="";
	String output="";

	public SecureSubject()
	{
		name = "Subject";
		temp = 0;
		security = 0;
	}

	public SecureSubject(String s, int lvl)
	{
		name = s;
		temp = 0;
		security = lvl;
	}

	public SecureSubject(String s)
	{
		name = s;
		temp = 0;
		security = 0;
	}

	public void run()
	{
		if(security == 0)
		{
			if(sbyte.length()<8)
			{
				sbyte+=(""+temp);
				if(sbyte.length() == 8)
				{
					char c = (char)Integer.parseInt(sbyte,2);
					output+=c;
					sbyte = "";
				}
			}
		}
		else
		{
			//do nothing
		}
		temp=0;
	}
	public void clear()
	{
		output = "";
		sbyte = "";
		temp=0;

	}
}













