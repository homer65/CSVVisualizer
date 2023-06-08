package org.myoggradio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class Data 
{
	private ObservableList<PieChart.Data> erg = FXCollections.observableArrayList();
	private ArrayList<XYChart.Series<Number,Number>> series = new ArrayList<XYChart.Series<Number,Number>>(); 
	private ArrayList<ObservableList<XYChart.Data<Number,Number>>> linedatas = new ArrayList<ObservableList<XYChart.Data<Number,Number>>>(); 
	public ObservableList<PieChart.Data> getPieData()
	{
		File file = new File(Parameter.data);
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
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
			System.out.println("Data:getPieData:Exception:");
			System.out.println(e.toString());
		}
		return erg;
	}
	public ArrayList<XYChart.Series<Number,Number>> getLineData()
	{
		File file = new File(Parameter.data);
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			if (Parameter.skip_first_row.equals("true")) br.readLine();
			String satz = br.readLine();
			String[] worte = satz.split(",");
			int n = worte.length - 1;
			for (int i=0;i<n;i++)
			{
				XYChart.Series<Number,Number> serie = new XYChart.Series<Number,Number>();
				series.add(serie);
				try
				{
					serie.setName(Parameter.yLabel.get(i));
				}
				catch (Exception e)
				{
					System.out.println("Data:getLineData:Kein y-Achsenlabel:" + i);
				}
				ObservableList<XYChart.Data<Number,Number>> linedata = FXCollections.observableArrayList();
				linedatas.add(linedata);
			}
			while (satz != null)
			{
				worte = satz.split(",");
				if (worte.length == n + 1)
				{
					double data0 = Double.parseDouble(worte[0]);
					for (int i=0;i<n;i++)
					{
						double datai = Double.parseDouble(worte[i+1]);
						linedatas.get(i).add(new XYChart.Data<Number,Number>(data0,datai));
					}
				}
				satz = br.readLine();
			}
			for (int i=0;i<n;i++)
			{
				series.get(i).setData(linedatas.get(i));
			}
			br.close();
		}
		catch (Exception e)
		{
			System.out.println("Data:getLineData:Exception:");
			System.out.println(e.toString());
		}
		return series;
	}
	public void droesel(String satz)
	{
		String[] worte = satz.split(",");
		if (worte.length > 1)
		{
			String wert1 = worte[0].trim();
			double wert2 = Double.parseDouble(worte[1].trim());
			System.out.println(wert1 + " >>> " + wert2);
			erg.add(new PieChart.Data(wert1,wert2));
		}
	}
}
