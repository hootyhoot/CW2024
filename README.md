## Changes

### ShieldImage Class
- image misreference fixed from .jpg to .png

### UserPlane Class
- changed image_height to 50 after image resizing

### EnemyPlane Class
- changed image_height to 50 after image resizing

### Boss Class
- changed image_height to 75 after image resizing

### UserProjectile Class
- changed image_height to 8 after image resizing

### EnemyProjectile Class
- changed image_height to 25 after image resizing

### LevelView Class
- edited loss screen x and y positions to center loss image

  

## Problems
- Enemy hitbox too large (solved by cropping borders of images)
- bullets now come out of userplane wrong, offset
- Memory usage high when leveling up
- hard coupling (D in SOLID)
