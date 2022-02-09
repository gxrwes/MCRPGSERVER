# Time arguments

![](./images/arguments/time.png)

The `TimeArgument` class represents in-game time, _in the number of in-game ticks_. This allows command senders to specify a certain number of ticks in a simpler way, by including the characters `d` to specify the numbers of days, `s` to specify the number of seconds or `t` to specify a number of ticks.

The CommandAPI converts the inputs provided by the command sender into a number of ticks as an integer.

> **Developer's Note:**
>
> The `TimeArgument` provides inputs such as `2d` (2 in-game days), `10s` (10 seconds) and `20t` (20 ticks), but does **not** let you combine them, such as `2d10s`.

<div class="example">

### Example - Displaying a server-wide announcement

Say we have a command `bigmsg` that displays a title message to all players for a certain duration:

```mccmd
/bigmsg <duration> <message>
```

```java
{{#include ../../CommandAPI/commandapi-core/src/test/java/Examples.java:timearguments}}
```

</div>