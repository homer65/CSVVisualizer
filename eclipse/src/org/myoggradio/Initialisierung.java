package org.myoggradio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Initialisierung 
{
	public void start(Stage stage)
	{
		ExtensionFilter filter = new FileChooser.ExtensionFilter("ini Dateien","*.ini");
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(filter);
		fc.setInitialDirectory(new File("."));
		File iniDatei = fc.showOpenDialog(stage);
		if (iniDatei != null)
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(iniDatei));
				String satz = br.readLine();
				while (satz != null)
				{
					droesel(satz);
					satz = br.readLine();
				}
				br.close();
			}
			catch (Exception e)
			{
				System.out.println("Initialisierung:start:Exception:");
				System.out.println(e.toString());
			}
		}
	}
	public void droesel(String satz)
	{
		String[] worte = satz.split("=");
		if (worte.length > 1)
		{
			String parm = worte[0];
			String wert = worte[1];
			if (parm.equals("typ")) Parameter.typ = wert;
			if (parm.equals("data")) Parameter.data = wert;
			if (parm.equals("name")) Parameter.name = wert;
			if (parm.equals("stagex")) Parameter.stagex = Integer.parseInt(wert);
			if (parm.equals("stagey")) Parameter.stagey = Integer.parseInt(wert);
			if (parm.equals("xAchse")) Parameter.xAchse = wert;
			if (parm.equals("yAchse")) Parameter.yAchse = wert;
			if (parm.equals("yLabel")) Parameter.yLabel.add(wert);
			if (parm.equals("skip_first_row")) Parameter.skip_first_row = wert;
		}
	}
}
