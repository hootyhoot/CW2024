## TODO
- bullets now come out of userplane wrong, offset (solved by adjusting offset in Userplane class)
- Memory usage high when leveling up (solved by destroying userplane instance in LevelParent gotolevel function)
- hard coupling (D in SOLID) splitting classes
- split controllers based on function, UI, game
- coding conventions for var and field names (Bob's C^3 10 RULES week 13 slides)
- javadocs over each class and method
- 5-7 tests min (game logic not gui eg testing data comms between functions, conditions for level up)
- add playable level(s)
- menu and pause screen
- creative additions (eg powerups, different enemies, leaderboard saving?)

# Github - https://github.com/hootyhoot/CW2024

# Compilation Instructions

# Implemented and Working Correctly
- UserPlane can move left, right, up, down
- All sprites cropped to adjust hitboxes

# Implemented but Not Working Correctly

# Features Not Implemented

# New Java Classes

# Modified Java Classes

### All Classes
- used Uncle Bob's 10 coding convention rules for variable, field, and method names and placements

### ShieldImage Class
- image misreference fixed from .jpg to .png

### UserPlane Class
- changed image_height to 50 after image resizing
- adjusted projectile position
- added horizontal movement

### EnemyPlane Class
- changed image_height to 50 after image resizing

### Boss Class
- changed image_height to 75 after image resizing
- edited boss shield probability and max frames with shield to make it more interactive
- instantiate ShieldImage class here instead of in LevelViewLevelTwo class
- fixed activate, deactivate shield functions
- added shieldimage getter so it can be called in LevelTwo

### UserProjectile Class
- changed image_height to 8 after image resizing

### EnemyProjectile Class
- changed image_height to 25 after image resizing

### LevelView Class
- edited loss screen x and y positions to center loss image

### LevelParent Class
- destroyed Userplane instance on level up
- added horizontal movement key listeners

### LevelTwo Class
- changed LevelViewLevelTwo instance to LevelView after refactoring of it
- called and added shieldimage to root in spawnenemyunits function using getter from boss class

### LevelViewLevelTwo Class
- deprecating and deleting this class on next commit after removing redundant functions

# Unexpected Problems