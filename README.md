## Changes

### ShieldImage Class
- image misreference fixed from .jpg to .png

### UserPlane Class
- changed image_height to 50 after image resizing
- adjusted projectile position

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

### LevelTwo Class
- changed LevelViewLevelTwo instance to LevelView after refactoring of it
- called and added shieldimage to root in spawnenemyunits function using getter from boss class

### LevelViewLevelTwo Class
- deprecating and deleting this class on next commit after removing redundant functions

  

## Problems
- Enemy hitbox too large (solved by cropping borders of images)
- bullets now come out of userplane wrong, offset (solved by adjusting offset in Userplane class)
- Memory usage high when leveling up (solved by destroying userplane instance in LevelParent gotolevel function)
- hard coupling (D in SOLID)
