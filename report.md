# FLAPS Report

**First author**: Tomás Falcão(s4246683)

**Second author**: Yorrick Boswijk (s4349245)

##1. Introduction

This report outlines and explains the design and development of our F.L.A.P.S. computer software. We will go further into explaining  our methodology and our design decisions on each step of the project and explain why we have decided to implement functionalities in a certain way. The program was written in Java to run in any operating system, using the IntelliJ Ultimate IDE. The report includes an explanation step by step of our code.

## 2. Problem:
In this programming assignment our team has been tasked with extending a F.L.A.P.S. framework. F.L.A.P.S. stands for Flight Logistics Aviation Planning Simulation. F.L.A.P.S. is a framework that allows users to send multiple aircrafts to multiple airports. Depending on the location of the airport we can have different aircrafts and different cargo that the aircrafts transport. The cargo and fuel price might also differ depending on the location as well. 

We have to add an editor that allows the user of the F.L.A.P.S. to edit the configuration of the different aircrafts in different airports from around the globe. Unlike the previous assignments, we have more freedom to choose how the user interface appears on the screen and our team is responsible to make design decisions along the project to match the Team Operations Worldwide Elevation Routes , or T.O.W.E.R,  requirements. Our team tried to approach this project by first analysing every step and having an idea on how to implement each different component.  We wanted to make a simple interface that is easy to use by the user. Below we have attached a summary with the main requirements our program should suffice. 

The program should be able to do the following:  
-Display the blueprint image of the aircraft 
-Display (different) indicators for the cargo areas and fuel tanks 
-Add/remove fuel to/from fuel tanks 
-Add/remove cargo to/from cargo areas 
-Show the following information: 
`	1.	Range 	
	2.	Empty aircraft center of gravity 
	3.	Loaded aircraft center of gravity 
	4.	Revenue of the journey 
	5.	Cost of the journey 
	6.	Profit of the journey 
-Depart planes 
-Support undo/redo functionality
-Your program should follow the MVC structure.
-All exceptions should be handled appropriately

## Program design
3.	Outline solution and detailed design 

3.1	View:

The first frame that appears when the user runs the program will be the main FLAPS frame. This frame was not designed by our Team but here the user will be able to select an aircraft and open a configuration menu of that aircraft. 

The configuration menu is the first frame our team worked on. We approach the view by trying to make the frame as simple as possible, and as easy as possible for the user to understand what he is doing. For that we decided to add a titled border in all panels with the panel name. Our team also tried to have as few frames as is feasible in order to make the program as simple as possible. For that reason we have decided to have 2 main panels in our Aircraft Editor frame. 

In our approach we decided to split the Aircraft editor frame in two with a split pane. On the left side we have a blueprint panel that displays the blueprint of the selected aircraft and the different fuel tanks and cargo areas on the aircraft. Fuel areas are displayed as blue dots and cargo as dark grey dots. The user is able to select each one of the fuel and cargo areas by clicking on them and the selected area will turn into a red point. On the right side we have a Data panel that contains 3 more panels, FuelTank, Information and CargoArea panels. This way the user is able to edit the size of both sides of the frame, depending if the user desires to see more or less of the Data panel. 

Besides from the Aircraft editor frame, we had to add one more small frame. If the users want to add more cargo to the aircraft, it is possible by clicking on the Load cargo button and this will open a smaller frame called Cargo Load Menu. 

When we were discussing the best way to implement the Cargo Load Menu we discussed two different approaches. We first thought about hiding the Data panel and displaying another panel in the Aircraft Editor frame that would allow the user to load cargo. This panel would only appear when clicked on the Load cargo button. However we decided to implement a second frame for the simple reason that might be useful to the user to see the information panel at the same time as the CargoArea panel, and this way with a smaller frame the user can drag the frame to the side and have both frames opened at the same time. 

On the Cargo Load Menu we decided to have 3 panels. One with a list of all cargo types where the user can select the desired cargo to load or delete from the selected cargo area, A second where the user has a slider and can choose how many kg of the selected area is loaded or deleted from the cargo area. And a last panel where the user can see a list of the current loaded cargo into the selected cargo area. 

3.2 Model:
For models we have the Editor and Information models. These models are used to create data and also as a place to fetch the data from.

The Editormodel is a model which has several methods, being selectedPointsbycoords, init , the construtor and the addlistener.

The selectedPointsbycoords method is a method which uses a listener to find the selected point selected by the user and then looks if the point selected by the user is on a point drawn in the blueprintpanel. If this is true then it will select the fuel tank connected to the point and display the information with a method out of editorframe.

The init method constructs arrays for both fuel tanks and cargo areas and collects the coordinates of the empty plane's center of gravity.

In the constructor almost all variables get initialized and look at which png corresponds to the aircraft type.

The addlistener does what the name suggests and adds a listener to a set of listeners.

The information model also contains several methods, which are the constructor and the CenterofGravity.

The constructor mainly just initializes variables and gives through towards the next method being CenterofGravity.

The CenterofGravity method is a method which calculates the coordinates of the center of Gravity of the plane after fuel or cargo has been added or removed.

3.3 Controller:

The listeners in our program are essentially the same if a button is pressed, the following happens. We used mouse listeners and action listeners to make this work and the following is a short summary of what they do:

●	 LoadCargoListerner: Adds a Cargo Unit to the selectedCargo area.

●	LoadFuelListerner: Adds Fuel to the selectedFuel Tank.

●	RemoveCargoListerner: Deletes a Cargo Unit from the selectedCargo area.

●	OpenLoadCargo: Open the OpenLoadCargo Frame.

●	SelectionController: Gets the current mouse location and gives it to the selectPointbycoords method.

●	DepartAction: Plays a departure clip and removes fuel from fuel tanks.


## Evaluation of the program

Our team has tested the code and all the functionalities that we have implemented. 
First, we have started by selecting the 3 different types of aircrafts and checked that the corresponding blueprints with the corresponding indicators are displayed in the correct position. 

Additionally, the team has tested loading fuel to a fuel tank area and adding more than the capacity of the fuel tank. This will result on a pop-up message that will inform the user that has exceeded the fuel tank capacity. Furthermore we have tested clicking on all the different cargo and fuel tank areas and checked that the corresponding information is displayed in the corresponding panels. 

Moreover, we have tested that the correct information is also displayed and update if the user loads fuel to the aircraft or any kind of cargo. However we encountered some bugs  that we were not able to fix due to time. Sometimes when a user tries to delete a cargo that is loaded into the cargo area, the program will give an error saying the cargoUnit is null, but if the user closes the pop up Load cargo frame and opens it again, it will work and delete the cargo unit. 

Another bug our Team as encountered is that when successfully removing an cargo unit from the loaded cargo list. All names displayed in the loaded cargo list will disappear, but if we close Load cargo menu and we open it again, the remaining cargo will still be there. 

Finally we have checked that planes are able to depart to another airport however we were not able to implement undo/redo. 

## Future Development

Even though our code satisfies all requirements there is still room to improvement. If our team had more time we would try to fix the bugs of the Load Cargo menu. We also wanted to implement a slider for the delete cargo button so that the user would be able to select how much of the selected cargo should be deleted. 

Further improvements would be adding icons to the cargo lists in order to have a better interface and more user-friendly. Changing some text fonts and font sizes and in general making the interface look better.  

## Process evaluation

Our team first started the assigment by doing the view for almost every element and view requirement of the project. The view part was in our opinion the easiest and more fun part to do. We had to research online to understand better how some Java Swing components worked and we also discovered new components. 

After we had the view done we started working on displaying aircraft data in the panels and after the data was done we started working on the listeners for our buttons and other components. That part was challenging since we did not had much knowledged about listeners but we managed to make them work by doing research online and using materials made available to us such as tutorials and the reader. The hardest listeners were in our opinion the Load Cargo menu listeners.

Once we had the view and all data working we were able to start with the Departures. After that we just had to add the Undo and Redo to the functionalities we implemented. 

We worked together in almost everything. Our work stategy was dividing the work as we just explained and normally give task to each one. Once one was finished with their task the other could continue on with the code and get the next task done, and so on. 

## Conclusions

The problem was quite challenging for us, but we succeeded in every requirement and provided a clean and nice design and code. On this assignment we were able to practice most of all the OOP concepts and especially the Graphic user Interface. Even though it was a very hard assignment it was fun coding our own aircraft editor program and our team has learnt many extra topics inside the Java programming language that we had to research on our own to implement into our Project. 


