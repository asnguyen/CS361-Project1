import java.io.*;
import java.util.*;

public class SecureSubject
{
	String name;
	int temp;
	int security;

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

	public boolean equals(SecureSubject rhs)
	{
		return (name.equals(rhs.name) && security == rhs.security);
	}
}