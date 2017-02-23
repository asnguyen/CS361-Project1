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
		if(sbyte.length()>=8)
		{
			output.concat(sbyte);
			sbyte = "";
		}
		sbyte.concat(""+temp);
	}
}