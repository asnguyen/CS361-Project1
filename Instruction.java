import java.io.*;
import java.util.*;

public class Instruction
{
	String type;
	String subject;
	String object;
	int value;

	public Instruction(String line)
	{
		String[] instruction = line.split("\\s");
		if(instruction.length<=2 || instruction.length>=5)
		{
			type = "BAD";
			subject = "";
			object = "";
			value = -1;
		}
		else 
		{
			type = instruction[0];
			subject = instruction[1];
			object=instruction[2];
			if(instruction.length == 3)
			{
				if(type.equalsIgnoreCase("write"))
				{
					type = "BAD";
					subject = "";
					object = "";
					value = -1;
				}
			}
			else
			{
				if(!isNumeric(instruction[3]) || type.equalsIgnoreCase("read"))
				{
					type = "BAD";
					subject = "";
					object = "";
					value = -1;
				}
				else
				{
					value = Integer.parseInt(instruction[3]);
				}

			}
		}

	}

	public Instruction(String t, String s, String o, int v)
	{
		type = t;
		subject = s;
		object = o;
		value = v;
	}

	public Instruction(String t, String s, String o)
	{
		type = t;
		subject = s;
		object = o;
		value = -1;
	}

	public boolean equals(Instruction rhs)
	{
		return type.equalsIgnoreCase(rhs.type);
	}

	public boolean isNumeric(String s)
	{
		int size = s.length();
		for(int i = 0;i<size;++i)
		{
			if(!Character.isDigit(s.charAt(i)))
			{
				return false;
			}
		}
		return true;

	}
}






























