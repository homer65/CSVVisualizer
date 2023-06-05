package org.myoggradio;
public class _Main 
{
	public static void main(String[] args) 
	{
		Initialisierung ini = new Initialisierung();
		ini.start();
		Verarbeitung v = new Verarbeitung();
		v.start();
	}
}
