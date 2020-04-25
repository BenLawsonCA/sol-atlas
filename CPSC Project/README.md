# Sol Atlas

## An interactive map of our solar system


My project will create a **basic graphical interface to visualize the locations and orbital paths of the planets in our solar 
system**. The planetary data for each planet will be pulled from an API when the program is run and a list of planets will be 
instantiated with live data. The graphical interface will visualize planets from this list and the program will allow 
users to request desired information about each planet. Features of this program include:
- Interactive graphical interface
- Current location of each planet in solar system (including orbital path)
- Access to planetary information depending on user input
- Calculations for future system event

Users will use this program if they wish to see the current orbital map of our solar system and to learn more about the 
bodies in our solar system. 

*This project was inspired by an interest in Astronomy and the desire to apply my skills to create 
a program that utilizes real world collections of data*. 



## User Story
- As a user I want to be able to populate SolarSystem with Planets (including their data)
- As a user I want to be able to search for a celestial body and add it to SolarSystem
- As a user I want to see a list of the planets + celestial bodies I've searched for
- As a user I want to select a planet and view information about it
- As a user I want to be able to save the planets I have searched for when I close program
- As a user I want to be able to load the saved list of planets on start up

## Instructions for Grader
**NOTE!**: Code in PlanetDataLoader does NOT run in Autobot as it calls an API so coverage is impossible. Instructors 
have made exceptions for this as long as everything else runs. I do have local tests that cover 100% of the code.

- Planets are loaded into the list by default and data can be viewed
- Celestials are added to the list using the "Search for celestial" field/button. Ex. Type "halley" for Halley's Comet
    or "titan" for one of Jupiter's moons. You can search for over 200 different celestials as your search query is 
    sent to an API to pull live data. ( things like "ganymede", "callisto", "io", "moon" work)
- You can remove the added celestials, but I do not allow the user to remove the default Planets. If you added a celestial 
    you can select it and click the "Remove" button to remove it from the list. 
- This visual component is automatically displayed in the program. It shows the planets orbiting the sun
- You can save the list of celestials you have added by 
    1) Hitting the "Save". 
    2) Hitting the "Exit" button and hitting "Yes" when it asks if you want to save
    NOTE: If you hit "Exit" and click "No" to saving data, it will not save the data even if you hit "Save" before hitting "Exit"
- The data you have saved will automatically load on startup. If you want to reset to default, either remove the celestials you 
    have added OR hit "Exit" and hit "No" when prompted to save.
    
## Phase 4: Task 2    
- I have chosen to implement Option #2, implementing a type hierarchy. CelestialDataLoader is the interface, implemented by
    HttpCelestialDataLoader and FileCelestialDataLoader. This was implemented to allow testing of local data instead of always calling the
    API to test functionality. There are two interface methods, one of which has different implementation (reading from a file versus 
    calling an API).
## Phase 4: Task 3
- This fix builds off of task 2. I had no way of testing my code if the API was not called, so I implemented a type hierarchy
    which allows me to switch between reading from a file of saved API data OR calling the API directly. While this would have originally been
    one class, I thought it would be better design to split them into their own classes to carry out their individual functionality
    while implementing interface. This was to solve poor testability if access to API is unavailable. 
- In class SolarSystem, method getSolarSystemList() was carrying out too many tasks, some of which were redundant/unnecessary 
    depending on which conditions were met. I extracted functionality and made another method that can be called if conditions dictate.
- In class MainPanel in method displayData. I moved method functionality to class Celestial to maintain Celestial cohesion. Did not
    want class specific functionality outside of class. This lets the MainPanel class display celestial data without knowing too much of class
    Celestial