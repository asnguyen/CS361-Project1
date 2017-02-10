import java.io.*;
import java.util.*;

public class SecureObject
{
	String name;
	int value;
	int security;

	public SecureObject()
	{
		name = "Object";
		value = 0;
		security = 0;
	}

	public SecureObject(String s, int lvl)
	{
		name  = s;
		value = 0;
		security = lvl;
	}

	public boolean equals(SecureObject rhs)
	{
		return (name.equals(rhs.name) && value == rhs.value && security == rhs.value);
	}
}