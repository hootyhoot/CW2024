## TODO
- projectiles shoot enemies outside of screen
- javadocs over each class and method
- 5-7 tests min (game logic not gui eg testing data comms between functions, conditions for level up)
- add playable level(s)
- menu and pause screen
- creative additions (eg powerups, different enemies, leaderboard saving?)
- scrolling background?

# Github - https://github.com/hootyhoot/CW2024

# Compilation Instructions

# Implemented and Working Correctly
- UserPlane can move left, right, up, down without stopping at any point
- All sprites cropped to adjust hitboxes
- Shield capability for boss

# Implemented but Not Working Correctly
- Resume button resumes after loss screen for one frame at a time

# Features Not Implemented
- Main menu, pause menu and loss restart buttons
- 2 additional levels

# New Java Classes
- CollisionHandler Class
- ControlsHandler Class
- DestructibleEntityHandler Class
- DestructibleEntity Interface

# Modified Java Classes

### All Classes
- used Uncle Bob's 10 coding convention rules for variable, field, and method names
- ordered methods in classes by visibility (public, protected, package-private, private)
- placed classes in packages according to their function (eg. entities, handlers, gui)
- set local variables to final if they are not reassigned
- removed unused imports
- setting fields to local variables

### ControlsHandler Class
- edited controls logic so that the user plane never stops moving when a key is pressed and released at the same time
- linked the key listener to the scene instead of the background

### ActiveActor Class
- renamed to GameEntity Class

### ActiveActorDestructible Class
- renamed to DestructibleEntity Class

### ShieldImage Class
- image misreference fixed from .jpg to .png

### UserPlane Class
- adjusted image_height (image too large after replacing with new sprite)
- adjusted projectile position (projectile came out offset from new sprite)
- added horizontal movement (added horizontal velocity variables and moveLeft and moveRight methods)

### EnemyPlane Class
- adjusted image_height (image too large after replacing with new sprite)
- adjusted projectile position (projectile came out offset from new sprite)

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

### LevelViewLevelTwo Class
- deprecating and deleting this class on next commit after removing redundant functions

# Unexpected Problems
