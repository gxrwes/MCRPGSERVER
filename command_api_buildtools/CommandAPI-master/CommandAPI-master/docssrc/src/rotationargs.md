# Rotation arguments

![](./images/arguments/rotation.png)

The `RotationArgument` allows users to specify a pair of pitch and yaw coordinates. By default (using the `~` symbol), this refers to the player's current pitch and yaw of where they are looking at.

The `RotationArgument` class returns a `Rotation` object, which consists of the following methods:

| Method name                  | What it does                                        |
| ---------------------------- | --------------------------------------------------- |
| `float getPitch()`           | Returns a player's pitch (up and down rotation)     |
| `float getYaw()`             | Returns a player's yaw (left and right rotation)    |
| `float getNormalizedPitch()` | Returns a player's pitch between -90 and 90 degrees |
| `float getNormalizedYaw()`   | Returns a player's yaw between -180 and 180 degrees |

<div class="example">

### Example: Rotate an armor stand head

Say we want to make an armor stand look in a certain direction. To do this, we'll use the following command:

```mccmd
/rotate <rotation> <target>
```

To do this, we'll use the rotation from the `RotationArgument` and select an entity using the `EntitySelectorArgument`, with `EntitySelector.ONE_ENTITY`. We then check if our entity is an armor stand and if so, we set its head pose to the given rotation.

```java
{{#include ../../CommandAPI/commandapi-core/src/test/java/Examples.java:rotationarguments}}
```

Note how the head pose requires an `EulerAngle` as opposed to a pitch and yaw. To account for this, we convert our rotation (which is in degrees) into an `EulerAngle` in radians.

</div>