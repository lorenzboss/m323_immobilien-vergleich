# Immobilien Vergleich

## Beschreibung

In diesem Projekt werden die Daten von 335 verkauften Immobilien aus dem Kanton Basel-Landschaft zwischen den Jahren 2011 und 2023 analysiert und
verglichen.

Es ist ein Java-Projekt, erstellt mit OpenJDK 22 und der Gson-Library zur JSON-Verarbeitung von Google.

Es wurde dabei kein Maven benutzt, die Gson-Library wurde direkt über IntelliJ hinzugefügt und befindet sich im Ordner `/lib`.

## Voraussetzungen

* Java Development Kit (JDK) 22
* IntelliJ IDEA (zum Ausführen des Projekts erforderlich)

## Ausführen des Projekts

1. Laden Sie das Projekt herunter oder klonen Sie das Projekt von GitHub.
2. Öffnen Sie das Projekt in IntelliJ IDEA.
3. Gson-Library überprüfen:
    4. Die Gson-Library sollte bereits über IntelliJ als Projekt-Library erkannt werden.
    5. Wenn nicht, fügen Sie die Library hinzu, indem Sie auf `Project Structure` gehen und dann auf `Libraries`. Klicken Sie auf das "+"-Symbol und
       wählen Sie die Library in dem `/lib` Ordner aus.
6. Wählen Sie die Main-Klasse `Main.java` in dem `/src` Ordner aus.
7. Klicken Sie auf das grüne Play-Symbol neben der Main-Klasse, um das Projekt auszuführen.

**Hinweis: Dieses Projekt ist speziell für IntelliJ konfiguriert. Die Abhängigkeiten werden möglicherweise nicht korrekt erkannt, wenn das Projekt in
einer anderen Entwicklungsumgebung ausgeführt wird.**

## TestFunctions

Die Klasse TestFunctions führt alle Methoden einmal imperativ und einmal funktional aus.
Diese Klasse ist hilfreich, wenn man den Output der funktionalen und der imperativen Methoden vergleichen möchte.
Um die Klasse zu benutzen, muss man lediglich den auskommentierten Code in der Main-Klasse benutzen.
Zusätzlich sollte man den Star-Befehl der Konsolenapplikation auskommentieren, um den Überblick über den Output zu behalten.

## Output

Da wir so viele Variationen unserer Funktionen bzw. Methoden haben, haben wir uns dazu entschieden, nicht ganz alle Variationen hier im Output zu
zeigen.
Alle Funktionen werden 2 Mal mit unterschiedlichen Variationen ausgeführt, jeweils einmal funktional und einmal imperativ.

### Output v1 - Imperative Logik

```
Welcome to the Property Comparator!
We have 355 properties in our database.
All of our logic in this project is created in a functional and and imperative way.
Would you like to use our functional or imperative logic?
f - functional
i - imperative
Please select an option: i
You have selected the imperative logic.

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 1
Do you want to print the properties in a compact form? (y/n): y
Do you want to set a limit? (y/n): y
Please enter the limit: 5



The first 5 properties in compact form:
Year Distract   Rooms       Price
2011 ARLESHEIM  TOTAL      719362
2011 ARLESHEIM  ONE        138233
2011 ARLESHEIM  TWO        346354
2011 ARLESHEIM  THREE      581285
2011 ARLESHEIM  FOUR       812620
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 1
Do you want to print the properties in a compact form? (y/n): n
Do you want to set a limit? (y/n): y
Please enter the limit: 5



The first 5 properties
Year:     2011
District: ARLESHEIM
Rooms:    TOTAL
Price:    719362

Year:     2011
District: ARLESHEIM
Rooms:    ONE
Price:    138233

Year:     2011
District: ARLESHEIM
Rooms:    TWO
Price:    346354

Year:     2011
District: ARLESHEIM
Rooms:    THREE
Price:    581285

Year:     2011
District: ARLESHEIM
Rooms:    FOUR
Price:    812620

Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 2
You can filter by year, district, or rooms.
Would you like to filter by year? (y/n): y
Would you like to filter by district? (y/n): y



Number of sales per year and district: 
Year   ARLESHEIM      LAUFEN     LIESTAL     SISSACH  WALDENBURG
2011           6           5           6           5           5
2012           6           5           6           6           4
2013           6           5           6           5           5
2014           6           5           6           6           5
2015           6           5           6           6           5
2016           6           5           6           5           5
2017           6           5           6           6           5
2018           6           5           6           6           5
2019           6           5           6           6           3
2020           6           5           6           5           5
2021           6           5           6           5           5
2022           6           5           6           6           6
2023           6           5           6           5           4
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 2
You can filter by year, district, or rooms.
Would you like to filter by year? (y/n): y
Would you like to filter by district? (y/n): n
Would you like to filter by rooms? (y/n): y



Number of sales per year and number of rooms: 
Year        ONE        TWO      THREE       FOUR      TOTAL  FIVE_PLUS
2011          2          5          5          5          5          5
2012          3          4          5          5          5          5
2013          2          5          5          5          5          5
2014          3          5          5          5          5          5
2015          3          5          5          5          5          5
2016          2          5          5          5          5          5
2017          3          5          5          5          5          5
2018          3          5          5          5          5          5
2019          3          4          5          5          5          4
2020          2          5          5          5          5          5
2021          2          5          5          5          5          5
2022          4          5          5          5          5          5
2023          2          5          5          5          5          4
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 3
Do you only want to print out the price of the properties? (y/n): n
How many properties do you want to print: 5



The most expensive properties
     Year  District   Rooms      Price CHF 
1    2022  ARLESHEIM  FIVE_PLUS  1689501   
2    2023  ARLESHEIM  FIVE_PLUS  1493819   
3    2021  ARLESHEIM  FIVE_PLUS  1418235   
4    2018  ARLESHEIM  FIVE_PLUS  1406650   
5    2019  ARLESHEIM  FIVE_PLUS  1399948   
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 3
Do you only want to print out the price of the properties? (y/n): y
How many properties do you want to print: 5



The price of the most expensive properties
  1: selling price CHF: 1689501
  2: selling price CHF: 1493819
  3: selling price CHF: 1418235
  4: selling price CHF: 1406650
  5: selling price CHF: 1399948
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 4
You can filter by year, district, or rooms.
Would you like to filter by year? (y/n): y
Would you like to filter by district as well? (y/n): y



Average price per year and district
year   ARLESHEIM      LAUFEN     LIESTAL     SISSACH  WALDENBURG
2011   612195.17   512139.33   509057.00   524651.40   436623.33
2012   654913.17   668468.25   464696.00   485372.60   512942.50
2013   687641.00   557777.40   471057.67   677841.00   444329.50
2014   727824.17   618420.50   541331.33   484866.83   499527.33
2015   677182.33   550380.75   550670.00   674852.40   621011.00
2016   748639.00   518208.80   626683.40   650687.40   535870.67
2017   784371.67   626530.40   657348.20   730739.25   512321.00
2018   918964.00   577556.00   583474.33   633047.20   514639.80
2019   842386.83   521497.25   681530.20   632133.20   471913.33
2020   844862.17   553611.00   667876.00   772069.00   575662.25
2021   857811.50   648514.80   589246.17   683252.20   678952.75
2022   990046.00   710170.00   701754.17   674110.33   520883.75
2023   906049.33   741181.60   775677.33   757537.20   580848.25
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 4
You can filter by year, district, or rooms.
Would you like to filter by year? (y/n): y
Would you like to filter by district as well? (y/n): n



Average price per year for properties.
year: 2011, average price: 531272.77
year: 2012, average price: 558446.78
year: 2013, average price: 573369.31
year: 2014, average price: 579855.92
year: 2015, average price: 617077.72
year: 2016, average price: 628222.67
year: 2017, average price: 664407.40
year: 2018, average price: 648615.38
year: 2019, average price: 657581.17
year: 2020, average price: 690313.88
year: 2021, average price: 694499.69
year: 2022, average price: 734438.81
year: 2023, average price: 765667.19
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 5
Here you can compare the price difference between two number of rooms.
Please enter the first number of rooms (the more expensive one).
Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): 2
You have selected 2 rooms.

Please enter the second number of rooms (the cheaper one).
Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): 1
You have selected 1 room.

You have selected the rooms TWO and ONE.
Now you can filter the properties by year or district.
Do you want to filter by year? (y/n): y



Price difference per year between TWO and ONE
Year: 2011, Price difference: 164911.33
Year: 2012, Price difference: 132713.33
Year: 2013, Price difference: 165366.90
Year: 2014, Price difference: 109427.33
Year: 2015, Price difference: 237021.00
Year: 2016, Price difference: 162790.50
Year: 2017, Price difference: 211408.00
Year: 2018, Price difference: 260196.25
Year: 2019, Price difference:  82231.50
Year: 2020, Price difference:  33967.33
Year: 2021, Price difference: 181063.00
Year: 2022, Price difference: 220566.50
Year: 2023, Price difference: 132563.60
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 5
Here you can compare the price difference between two number of rooms.
Please enter the first number of rooms (the more expensive one).
Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): 2
You have selected 2 rooms.

Please enter the second number of rooms (the cheaper one).
Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): 1
You have selected 1 room.

You have selected the rooms TWO and ONE.
Now you can filter the properties by year or district.
Do you want to filter by year? (y/n): n
Do you want to filter by district? (y/n): y



Price difference per district between TWO and ONE
District:     LAUFEN, Price Difference:  397125.00
District: WALDENBURG, Price Difference:  357914.75
District:  ARLESHEIM, Price Difference:  220446.67
District:    LIESTAL, Price Difference:  194427.61
District:    SISSACH, Price Difference:  162510.73
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 6
Here you can get the price development between two years (2011-2023).
Please enter the first year (the smaller year): 2015
Please enter the second year: (the greater year) 2020
You have selected the years 2015 and 2020.
Now you can filter the properties by district or number of rooms.
Do you want to filter by district? (y/n): y



Price development in percent per district between 2015 and 2020
District:  ARLESHEIM, Price development:  24.76%
District:    LIESTAL, Price development:  21.28%
District:    SISSACH, Price development:  14.41%
District:     LAUFEN, Price development:   0.59%
District: WALDENBURG, Price development:  -7.30%
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 6
Here you can get the price development between two years (2011-2023).
Please enter the first year (the smaller year): 2015
Please enter the second year: (the greater year) 2020
You have selected the years 2015 and 2020.
Now you can filter the properties by district or number of rooms.
Do you want to filter by district? (y/n): n
Do you want to filter by number of rooms? (y/n): y



Price development in percent per number of rooms between 2015 und 2020
Rooms:        ONE, Price development:  95.74%
Rooms:      THREE, Price development:  11.90%
Rooms:       FOUR, Price development:   8.50%
Rooms:  FIVE_PLUS, Price development:   7.40%
Rooms:      TOTAL, Price development:   6.51%
Rooms:        TWO, Price development:   1.35%
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 7
Exiting the program...

Process finished with exit code 0
```

### Output v2 - Funktionale Logik

```
Welcome to the Property Comparator!
We have 355 properties in our database.
All of our logic in this project is created in a functional and and imperative way.
Would you like to use our functional or imperative logic?
f - functional
i - imperative
Please select an option: f
You have selected the functional logic.

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 1
Do you want to print the properties in a compact form? (y/n): y
Do you want to set a limit? (y/n): y
Please enter the limit: 5



The first 5 properties in compact form:
Year Distract   Rooms       Price
2011 ARLESHEIM  TOTAL      719362
2011 ARLESHEIM  ONE        138233
2011 ARLESHEIM  TWO        346354
2011 ARLESHEIM  THREE      581285
2011 ARLESHEIM  FOUR       812620
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 1
Do you want to print the properties in a compact form? (y/n): n
Do you want to set a limit? (y/n): y
Please enter the limit: 5



The first 5 properties
Year:     2011
District: ARLESHEIM
Rooms:    TOTAL
Price:    719362

Year:     2011
District: ARLESHEIM
Rooms:    ONE
Price:    138233

Year:     2011
District: ARLESHEIM
Rooms:    TWO
Price:    346354

Year:     2011
District: ARLESHEIM
Rooms:    THREE
Price:    581285

Year:     2011
District: ARLESHEIM
Rooms:    FOUR
Price:    812620

Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 2
You can filter by year, district, or rooms.
Would you like to filter by year? (y/n): y
Would you like to filter by district? (y/n): y



Number of sales per year and district: 
Year   ARLESHEIM      LAUFEN     LIESTAL     SISSACH  WALDENBURG
2011           6           5           6           5           5
2012           6           5           6           6           4
2013           6           5           6           5           5
2014           6           5           6           6           5
2015           6           5           6           6           5
2016           6           5           6           5           5
2017           6           5           6           6           5
2018           6           5           6           6           5
2019           6           5           6           6           3
2020           6           5           6           5           5
2021           6           5           6           5           5
2022           6           5           6           6           6
2023           6           5           6           5           4
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 2
You can filter by year, district, or rooms.
Would you like to filter by year? (y/n): y
Would you like to filter by district? (y/n): n
Would you like to filter by rooms? (y/n): y



Number of sales per year and number of rooms: 
Year        ONE        TWO      THREE       FOUR      TOTAL  FIVE_PLUS
2011          2          5          5          5          5          5
2012          3          4          5          5          5          5
2013          2          5          5          5          5          5
2014          3          5          5          5          5          5
2015          3          5          5          5          5          5
2016          2          5          5          5          5          5
2017          3          5          5          5          5          5
2018          3          5          5          5          5          5
2019          3          4          5          5          5          4
2020          2          5          5          5          5          5
2021          2          5          5          5          5          5
2022          4          5          5          5          5          5
2023          2          5          5          5          5          4
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 3
Do you only want to print out the price of the properties? (y/n): n
How many properties do you want to print: 5



The most expensive properties
     Year  District   Rooms      Price CHF 
1    2022  ARLESHEIM  FIVE_PLUS  1689501   
2    2023  ARLESHEIM  FIVE_PLUS  1493819   
3    2021  ARLESHEIM  FIVE_PLUS  1418235   
4    2018  ARLESHEIM  FIVE_PLUS  1406650   
5    2019  ARLESHEIM  FIVE_PLUS  1399948   
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 3
Do you only want to print out the price of the properties? (y/n): y
How many properties do you want to print: 5



The price of the most expensive properties
  1: selling price CHF: 1689501
  2: selling price CHF: 1493819
  3: selling price CHF: 1418235
  4: selling price CHF: 1406650
  5: selling price CHF: 1399948
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 4
You can filter by year, district, or rooms.
Would you like to filter by year? (y/n): y
Would you like to filter by district as well? (y/n): y



Average price per year and district
year   ARLESHEIM      LAUFEN     LIESTAL     SISSACH  WALDENBURG
2011   612195.17   512139.33   509057.00   524651.40   436623.33
2012   654913.17   668468.25   464696.00   485372.60   512942.50
2013   687641.00   557777.40   471057.67   677841.00   444329.50
2014   727824.17   618420.50   541331.33   484866.83   499527.33
2015   677182.33   550380.75   550670.00   674852.40   621011.00
2016   748639.00   518208.80   626683.40   650687.40   535870.67
2017   784371.67   626530.40   657348.20   730739.25   512321.00
2018   918964.00   577556.00   583474.33   633047.20   514639.80
2019   842386.83   521497.25   681530.20   632133.20   471913.33
2020   844862.17   553611.00   667876.00   772069.00   575662.25
2021   857811.50   648514.80   589246.17   683252.20   678952.75
2022   990046.00   710170.00   701754.17   674110.33   520883.75
2023   906049.33   741181.60   775677.33   757537.20   580848.25
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 4
You can filter by year, district, or rooms.
Would you like to filter by year? (y/n): y
Would you like to filter by district as well? (y/n): n



Average price per year for properties.
year: 2011, average price: 531272.77
year: 2012, average price: 558446.78
year: 2013, average price: 573369.31
year: 2014, average price: 579855.92
year: 2015, average price: 617077.72
year: 2016, average price: 628222.67
year: 2017, average price: 664407.40
year: 2018, average price: 648615.38
year: 2019, average price: 657581.17
year: 2020, average price: 690313.88
year: 2021, average price: 694499.69
year: 2022, average price: 734438.81
year: 2023, average price: 765667.19
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 5
Here you can compare the price difference between two number of rooms.
Please enter the first number of rooms (the more expensive one).
Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): 2
You have selected 2 rooms.

Please enter the second number of rooms (the cheaper one).
Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): 1
You have selected 1 room.

You have selected the rooms TWO and ONE.
Now you can filter the properties by year or district.
Do you want to filter by year? (y/n): y



Price difference per year between TWO and ONE
Year: 2011, Price difference: 164911.33
Year: 2012, Price difference: 132713.33
Year: 2013, Price difference: 165366.90
Year: 2014, Price difference: 109427.33
Year: 2015, Price difference: 237021.00
Year: 2016, Price difference: 162790.50
Year: 2017, Price difference: 211408.00
Year: 2018, Price difference: 260196.25
Year: 2019, Price difference:  82231.50
Year: 2020, Price difference:  33967.33
Year: 2021, Price difference: 181063.00
Year: 2022, Price difference: 220566.50
Year: 2023, Price difference: 132563.60
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 5
Here you can compare the price difference between two number of rooms.
Please enter the first number of rooms (the more expensive one).
Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): 2
You have selected 2 rooms.

Please enter the second number of rooms (the cheaper one).
Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): 1
You have selected 1 room.

You have selected the rooms TWO and ONE.
Now you can filter the properties by year or district.
Do you want to filter by year? (y/n): n
Do you want to filter by district? (y/n): y



Price difference per district between TWO and ONE
District:     LAUFEN, Price Difference:  397125.00
District: WALDENBURG, Price Difference:  357914.75
District:  ARLESHEIM, Price Difference:  220446.67
District:    LIESTAL, Price Difference:  194427.61
District:    SISSACH, Price Difference:  162510.73
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 6
Here you can get the price development between two years (2011-2023).
Please enter the first year (the smaller year): 2015
Please enter the second year: (the greater year) 2020
You have selected the years 2015 and 2020.
Now you can filter the properties by district or number of rooms.
Do you want to filter by district? (y/n): y



Price development in percent per district between 2015 and 2020
District:  ARLESHEIM, Price development:  24.76%
District:    LIESTAL, Price development:  21.28%
District:    SISSACH, Price development:  14.41%
District:     LAUFEN, Price development:   0.59%
District: WALDENBURG, Price development:  -7.30%
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 6
Here you can get the price development between two years (2011-2023).
Please enter the first year (the smaller year): 2015
Please enter the second year: (the greater year) 2020
You have selected the years 2015 and 2020.
Now you can filter the properties by district or number of rooms.
Do you want to filter by district? (y/n): n
Do you want to filter by number of rooms? (y/n): y



Price development in percent per number of rooms between 2015 und 2020
Rooms:        ONE, Price development:  95.74%
Rooms:      THREE, Price development:  11.90%
Rooms:       FOUR, Price development:   8.50%
Rooms:  FIVE_PLUS, Price development:   7.40%
Rooms:      TOTAL, Price development:   6.51%
Rooms:        TWO, Price development:   1.35%
Press enter to continue...

1. Print all properties
2. Print the number of sales
3. Print the most expensive properties
4. Print the average price of the properties
5. Print the price difference of the properties between two number of rooms
6. Print the price development of the properties between two years
7. Exit the program
Please select an option: 7
Exiting the program...

Process finished with exit code 0
```




