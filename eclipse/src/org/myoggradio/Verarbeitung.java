package org.myoggradio;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Verarbeitung extends Application
{
	public void lauf()
	{
		Verarbeitung.launch();
	}
	@Override
	public void start(Stage stage) throws Exception 
	{
		Data data = new Data();
		stage.setTitle("CSVVisualizer");
		Initialisierung ini = new Initialisierung();
		ini.start(stage);
		if (Parameter.typ.equals("piechart"))
		{
			PieChart pie = new PieChart();
			ObservableList<PieChart.Data> piedata = data.getPieData(); 
			pie.setData(piedata);
			Group root = new Group(pie);
			Scene scene = new Scene(root,Parameter.stagex,Parameter.stagey);
			stage.setScene(scene);
			stage.show();
		}
		if (Parameter.typ.equals("linechart"))
		{
			NumberAxis xAxis = new NumberAxis();
			NumberAxis yAxis = new NumberAxis();
			LineChart<Number,Number> line = new LineChart<Number,Number>(xAxis,yAxis);
			ArrayList<XYChart.Series<Number,Number>> lineData = data.getLineData(); 
			for (int i=0;i<lineData.size();i++)
			{
				line.getData().add(lineData.get(i));
			}
			Group root = new Group(line);
			Scene scene = new Scene(root,Parameter.stagex,Parameter.stagey);
			stage.setScene(scene);
			stage.show();
		}
	}
}
