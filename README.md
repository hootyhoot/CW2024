## TODO
- projectiles shoot enemies outside of screen
- javadocs over each class and method
- 5-7 tests min (game logic not gui eg testing data comms between functions, conditions for level up)
- add playable level(s)
- scrolling background?

# Github - https://github.com/hootyhoot/CW2024

# Compilation Instructions

# Implemented and Working Correctly
- Hitboxes improved for all entities 
- Shield capability for boss
- Powerups for user (health powerup)
- Main Menu
- End game Menu (For both Win/Lose, dynamically changes menu)
- Level 3 (More enemies, higher enemy shooting rate, less user health, health powerup available)

# Implemented but Not Working Correctly
- Resume button resumes game even after loss screen shown by one frame at a time. (Clear root?)
- UserPlane can move left, right, up, down but holding left and then right momentarily stops it, holding down and then up momentarily stops it. (ControlsHandler)

# Features Not Implemented
- Scrolling background

# New Java Classes
- CollisionHandler Class
- ControlsHandler Class
- DestructibleEntityHandler Class
- EnemyInterface Class
- LevelThree Class
- Window Class
- MenuImage Class
- ButtonImage Class
- MainMenu Class
- EndMenu Class
- Powerup Class
- HeartPowerup Class

# Modified Java Classes

### Removals
- WinImage Class
- GameOverImage Class
- LevelViewLevelTwo Class

### All Classes
- used Uncle Bob's 10 coding convention rules for variable, field, and method names
- ordered methods in classes by visibility (public, protected, package-private, private)
- placed classes in separate packages according to their function (eg. entities, handlers, gui, levels)
- set local variables to final if they are not reassigned
- removed unused imports
- setting fields to local variables

### Main Class
- added window class instance to initialise the stage or window
- added MainMenu class instance to show the main menu on start
- moved controller instantiation to MainMenu class
- added stage.toFront() to bring the stage to the front when the game is started

### ControlsHandler Class
- edited controls logic so that the user plane never stops moving when a key is pressed and released at the same time
- linked the key listener to the scene instead of the background

### CollisionHandler Class
- moved collision detection logic from LevelParent class to this class
- added collision detection for user and powerups

### DestructibleEntityHandler Class
- moved destructible entity managing logic from LevelParent class to this class
- added powerups field variable to store powerups
- added accessor and mutator methods for powerups

### EnemyInterface Class
- defines contract for managing enemy entities 

### Window Class
- singleton class to initialise the window and ensure only one stage is used throughout the game

### MenuImage Class
- ImageView class for menu background images

### ButtonImage Class
- ImageView class for menu button images

### MainMenu Class
- singleton class to show the main menu allows user to start game or exit
- moved stage and scene configuration logic from Main class to this class
- controller instance moved here from Main class to start level from main menu

### EndMenu Class
- singleton class to show the end menu allows user to restart game or exit
- shows different menu based on win or lose condition by passing argument to constructor

### Powerup Class
- abstract class for defining powerup entities
- defines its movement behavior and its effects applied to the user

### HeartPowerup Class
- extends Powerup class, defines heart powerup entity which increments user health

### ActiveActor Class
- renamed to GameEntity Class

### ActiveActorDestructible Class
- renamed to DestructibleEntity Class

### Destructible Class
- renamed to DestructibleInterface Class

### ShieldImage Class
- image misreference fixed from .jpg to .png

### UserPlane Class
- adjusted image_height (image too large after replacing with new sprite)
- adjusted projectile position (projectile came out offset from new sprite)
- added horizontal movement (added horizontal velocity variables and moveLeft and moveRight methods)

### EnemyPlane Class
- adjusted image_height (image too large after replacing with new sprite)
- adjusted projectile position (projectile came out offset from new sprite)
- added fire rate field variable passed from argument in constructor (so fire rate can be adjusted in by level basis)

### GameEntity Class
- added logging and checks for null values of image files

### Boss Class
- adjusted image_height (image too large after replacing with new sprite)
- adjusted boss shield probability and max frames with shield (rebalancing user and boss to decrease fight length)
- instantiate ShieldImage class here instead of in LevelViewLevelTwo class
- fixed activate and deactivate shield functions (fixed shield related functions)
- added shieldimage getter so it can be called in LevelTwo

### UserProjectile Class
- adjusted image_height (image too large after replacing with new sprite)

### EnemyProjectile Class
- adjusted image_height (image too large after replacing with new sprite)

### LevelView Class
- adjusted loss screen x and y positions to center loss image

### LevelParent Class
- destroyed Userplane instance on level up (cleared user instance from previous level to prevent memory leak and reduce memory usage)
- moved out functions to separate classes (moved out collision detection, user input handling, and destructible entity handling to separate classes)
- added controlsHandler instance to handle user input
- added collisionHandler instance to handle collision detection
- added destructibleEntityHandler instance to handle destructible entities

### LevelTwo Class
- changed LevelViewLevelTwo instance to LevelView after refactoring of it
- called and added shieldimage to root in spawnenemyunits function using getter from boss class

### LevelThree Class
- added LevelThree class to implement harder level
- more enemies, higher enemy shooting rate, less user health, and health powerup available

# Unexpected Problems

