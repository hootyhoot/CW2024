## TODO
- bullets now come out of userplane wrong, offset (solved by adjusting offset in Userplane class)
- hard coupling (D in SOLID) splitting classes
- split controllers based on function, UI, game
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
- Shield capability for boss

# Implemented but Not Working Correctly

# Features Not Implemented

# New Java Classes

# Modified Java Classes

### All Classes
- used Uncle Bob's 10 coding convention rules for variable, field, and method names and placements

### ShieldImage Class
- image misreference fixed from .jpg to .png

### UserPlane Class
- adjusted image_height (image too large after replacing with new sprite)
- adjusted projectile position (projectile came out offset from new sprite)
- added horizontal movement (added horizontal velocity variables and moveLeft and moveRight methods)

### EnemyPlane Class
- adjusted image_height (image too large after replacing with new sprite)

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
- added horizontal movement key listeners

### LevelTwo Class
- changed LevelViewLevelTwo instance to LevelView after refactoring of it
- called and added shieldimage to root in spawnenemyunits function using getter from boss class

### LevelViewLevelTwo Class
- deprecating and deleting this class on next commit after removing redundant functions

# Unexpected Problems
