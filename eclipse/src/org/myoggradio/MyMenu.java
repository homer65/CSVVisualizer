package org.myoggradio;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MyMenu extends MenuBar
{
	public MyMenu()
	{
		Menu menu1 = new Menu("Export");
		MenuItem item11 = new MenuItem("ToPNG");
		menu1.getItems().add(item11);
		this.getMenus().add(menu1);
	}
}
