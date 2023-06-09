
Visualisiert CSV Dateien als Diagramm.

Bisher können PieChart und LineChart gezeichnet werden.

Hierzu wird nach dem Start des Programm eine *.ini Datei ausgesucht, die eine Beschreibung der CSV Datei und des Diagramms enthält.

Für alle unterstützten Chart gibt es eine Beispiel *.ini und *.csv Datei.

Folgende Parameter können für den jeweiligen Typ angegeben werden.

PieChart:

(1) typ    => Muss piechart sein

(2) data   => Pfad zur csv Datei

(3) name   => Überschrift des Diagramms

(4) stagex => Größe des Diagramms - Width

(5) stagey => Größe des Diagramms - Height

LineChart:

(1) typ    => Muss linechart sein

(2) data   => Pfad zur csv Datei

(3) name   => Überschrift des Diagramms

(4) stagex => Größe des Diagramms - Width

(5) stagey => Größe des Diagramms - Height

(6) xAchse => Beschriftung der x-Achse

(7) yAchse => Beschriftung der y-Achse

(8) yLabel => Label für eine Linie - kann mehrfach angegeben werden falls mehr als eine Linie 

(9) skip_first_row => true oder false - Bei true wird die erste Zeile der CSV Datei ignoriert

(10) xmin  => Double Wert - Untere Grenze für xAchse falls automatex=value

(11) xmax  => Double Wert - Obere Grenze für xAchse falls automatex=value

(12) ymin  => Double Wert - Untere Grenze für yAchse falls automatey=value 

(13) ymax  => Double Wert - Obere Grenze für yAchse fallse automatey=value
 
(14) automatx => no | value | automate : no bedeutet Keine Angabe : value bedeutet xmin und xmax ziehen : automate bedeutet Grenzen werden automatisch gesetzt

(15) automaty => no | value | automate : no bedeutet Keine Angabe : value bedeutet ymin und ymax ziehen : automate bedeutet Grenzen werden automatisch gesetzt
