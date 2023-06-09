package org.myoggradio;

import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Verarbeitung extends Application implements EventHandler<ActionEvent>
{
	private Chart chart = null;
	private MenuItem item11 = null;
	public void lauf()
	{
		Verarbeitung.launch();
	}
	@Override
	public void start(Stage stage) throws Exception 
	{
		MenuBar bar = new MenuBar();
		Menu menu1 = new Menu("Export");
		item11 = new MenuItem("ToPNG");
		item11.setOnAction(this);
		menu1.getItems().add(item11);
		bar.getMenus().add(menu1);
		Data data = new Data();
		stage.setTitle("CSVVisualizer");
		Initialisierung ini = new Initialisierung();
		ini.start(stage);
		if (Parameter.typ.equals("piechart"))
		{
			PieChart pie = new PieChart();
			chart = pie;
			pie.setTitle(Parameter.name);
			pie.setPrefSize(Parameter.stagex,Parameter.stagey);
			ObservableList<PieChart.Data> piedata = data.getPieData(); 
			pie.setData(piedata);
			VBox root = new VBox();
			root.getChildren().addAll(bar,pie);
			Scene scene = new Scene(root,Parameter.stagex,Parameter.stagey);
			stage.setScene(scene);
			stage.show();
		}
		if (Parameter.typ.equals("linechart"))
		{
			ArrayList<XYChart.Series<Number,Number>> lineData = data.getLineData(); 
			Number minx = data.getMinX(lineData);
			Number miny = data.getMinY(lineData);
			Number maxx = data.getMaxX(lineData);
			Number maxy = data.getMaxY(lineData);
			double dminx = minx.doubleValue();
			double dmaxx = maxx.doubleValue();
			double dminy = miny.doubleValue();
			double dmaxy = maxy.doubleValue();
			double deltax = (dmaxx - dminx) / 25.0;
			double deltay = (dmaxy - dminy) / 25.0;
			NumberAxis xAxis = new NumberAxis(dminx,dmaxx,deltax);
			NumberAxis yAxis = new NumberAxis(dminy,dmaxy,deltay);
			if (Parameter.automatex.equals("no"))
			{
				xAxis = new NumberAxis();
			}
			else if (Parameter.automatex.equals("value"))
			{
				dminx = Parameter.xmin;
				dmaxx = Parameter.xmax;
				deltax = (dmaxx - dminx) / 25.0;
				xAxis = new NumberAxis(dminx,dmaxx,deltax);
			}
			if (Parameter.automatey.equals("no"))
			{
				yAxis = new NumberAxis();
			}
			else if (Parameter.automatey.equals("value"))
			{
				dminy = Parameter.ymin;
				dmaxy = Parameter.ymax;
				deltay = (dmaxy - dminy) / 25.0;
				yAxis = new NumberAxis(dminy,dmaxy,deltay);
			}
			xAxis.setLabel(Parameter.xAchse);			
			yAxis.setLabel(Parameter.yAchse);
			LineChart<Number,Number> line = new LineChart<Number,Number>(xAxis,yAxis);
			chart = line;
			line.setTitle(Parameter.name);
			line.setPrefSize(Parameter.stagex,Parameter.stagey);
			for (int i=0;i<lineData.size();i++)
			{
				line.getData().add(lineData.get(i));
			}
			VBox root = new VBox();
			root.getChildren().addAll(bar,line);
			Scene scene = new Scene(root,Parameter.stagex,Parameter.stagey);
			stage.setScene(scene);
			stage.show();
		}
	}
	@Override
	public void handle(ActionEvent ae) 
	{
		Object quelle = ae.getSource();
		if (quelle == item11)
		{
		    WritableImage image = chart.snapshot(new SnapshotParameters(), null);
		    File file = new File("chart.png");
		    try 
		    {
		        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		    } 
		    catch (Exception e) 
		    {
		    	System.out.println("Verarbeitung:handle:item11:Exception:");
		        System.out.println(e.toString());
		    }
		}
	}
}

