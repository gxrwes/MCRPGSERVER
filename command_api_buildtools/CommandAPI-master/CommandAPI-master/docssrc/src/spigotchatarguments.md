# Spigot chat arguments

> **Developer's Note:**
>
> The two following classes, `ChatComponentArgument` and `ChatArgument` depend on a [Spigot](https://www.spigotmc.org/) based server. This means that these arguments will not work on a non-Spigot based server, such as CraftBukkit. If you use this class on a non-Spigot based server, it will throw a `SpigotNotFoundException`
>

## Chat component argument

The `ChatComponentArgument` class accepts raw chat-based JSON as valid input. Despite being regular JSON, it _must_ conform to the standard declared [here](https://minecraft.gamepedia.com/Raw_JSON_text_format), which consists of JSON that has a limited subset of specific keys (In other words, you can have a JSON object that has the key `text`, but not one that has the key `blah`).

This is converted into Spigot's `BaseComponent[]`, which can be used for the following:

- Broadcasting messages to all players on the server using:

  ````java
  Bukkit.getServer().spigot().broadcast(BaseComponent[]);
  ````

- Adding and setting pages to books using `BookMeta`:

  ```java
  BookMeta meta = // ...
  meta.spigot().setPages(BaseComponent[]);
  ```
  
- Sending messages to `Player` objects:

  ```java
  Player player = // ...
  player.spigot().sendMessage(BaseComponent[]);
  ```

- Sending messages to `CommandSender` objects:

  ```java
  CommandSender sender = // ...
  sender.spigot().sendMessage(BaseComponent[]);
  ```

<div class="example">

### Example - Book made from raw JSON

Say we want to generate a book using raw JSON. For this example, we'll use the following JSON (generated from [minecraftjson.com](https://minecraftjson.com/)) to generate our book:

```json
["", {
    "text": "Once upon a time, there was a guy call "
}, {
    "text": "Skepter",
    "color": "light_purple",
    "hoverEvent": {
        "action": "show_entity",
        "value": "Skepter"
    }
}, {
    "text": " and he created the "
}, {
    "text": "CommandAPI",
    "underlined": true,
    "clickEvent": {
        "action": "open_url",
        "value": "https://github.com/JorelAli/CommandAPI"
    }
}]
```

Since we're writing a book, we must ensure that all quotes have been escaped. This can also be performed on the [minecraftjson.com](https://minecraftjson.com/) website by selecting "book": 

```json
["[\"\",{\"text\":\"Once upon a time, there was a guy call \"},{\"text\":\"Skepter\",\"color\":\"light_purple\",\"hoverEvent\":{\"action\":\"show_entity\",\"value\":\"Skepter\"}},{\"text\":\" and he created the \"},{\"text\":\"CommandAPI\",\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://github.com/JorelAli/CommandAPI\"}}]"]
```

Now let's define our command. Since book text is typically very large - too large to be entered into a chat, we'll make a command block compatible command by providing a player parameter:

```mccmd
/makebook <player> <contents>
```

Now we can create our book command. We use the player as the main target by using their name for the author field, as well as their inventory to place the book. We finally construct our book using the `.setPages(BaseComponent[])` method:

```java
{{#include ../../CommandAPI/commandapi-core/src/test/java/Examples.java:chatcomponentarguments}}
```

</div>

-----

## Chat argument

> **Note:**
>
> The `ChatArgument` class is an argument similar to the [`GreedyStringArgument`](./stringarguments.html#greedy-string-argument), in the sense that it has no terminator and must be defined at the end of your `List` of arguments. For more information on this, please read the section on [Greedy arguments](./stringarguments.html#greedy-string-argument).

The `ChatArgument` is basically identical to the `GreedyStringArgument`, with the added functionality of enabling _entity selectors_, such as `@e`, `@p` and so on. The `ChatArgument` also returns a `BaseComponent[]`, similar to the `ChatComponentArgument`.

<div class="example">

### Example - Sending personalized messages to players

Say we wanted to broadcast a "personalized" message to players on the server. By "personalized", we mean a command which changes its output depending on who we are sending the output to. Simply put, we want a command of the following syntax:

```mccmd
/pbroadcast <message>
```

Say we're on a server with 2 players: _Bob_ and _Michael_. If I were to use the following command:

```mccmd
/pbroadcast Hello @p
```

_Bob_ would receive the message "Hello Bob", whereas _Michael_ would receive the message "Hello Michael". We can use the `ChatArgument` to create this "personalized" broadcast:

```java
{{#include ../../CommandAPI/commandapi-core/src/test/java/Examples.java:chatarguments}}
```

</div>