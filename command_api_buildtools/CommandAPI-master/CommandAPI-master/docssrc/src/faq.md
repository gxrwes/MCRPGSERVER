# FAQ

Here's a list of questions that have come up time and time again which all have the same answer.

-----

## How do I use (insert feature here) in the CommandAPI?

The CommandAPI's documentation is the place to search for anything! In the top left corner of this documentation, you can find this <i class="fas fa-search"></i> icon. You can pretty much search for anything - it'll find it!

-----

## Does the CommandAPI support optional arguments?

Currently, no. Adding optional arguments is actually a surprisingly difficult task because of how Brigadier handles "argument conflicts". For example, if I made a command (where arguments in brackets `()` are optional):

```mccmd
/mycommand (string) (integer)
```

We could then run any of the following:

```mccmd
/mycommand
/mycommand hello
/mycommand hello 10
/mycommand 10
```

The issue with this example is that `/mycommand 10` would not work as intended, because Brigadier treats the `10` argument as a valid string, so uses it as a string instead of as an integer.

The CommandAPI's optional arguments are a feature that it will support in the future, as stated in the [project roadmap](https://github.com/JorelAli/CommandAPI#future-project-plans--timeline)

-----

## Does the CommandAPI support suggestions in the annotation system?

Not yet. The CommandAPI's annotation system was actually originally a little test on writing a compile-time annotation system which actually worked out much better than I had intended. I plan to rewrite the CommandAPI's annotation system to make it much more powerful (and support suggestions!). This is stated in the [project roadmap](https://github.com/JorelAli/CommandAPI#future-project-plans--timeline)

-----

## Can you add tooltips to literal/multiliteral arguments?

No. This is a Brigadier limitation.

> **Proof that this is a limitation of Brigadier**
>
> Brigadier's code has two classes for arguments, [`LiteralCommandNode`](https://github.com/Mojang/brigadier/blob/master/src/main/java/com/mojang/brigadier/tree/LiteralCommandNode.java) and [`ArgumentCommandNode`](https://github.com/Mojang/brigadier/blob/master/src/main/java/com/mojang/brigadier/tree/ArgumentCommandNode.java). The `ArgumentCommandNode` class contains a field `customSuggestions` of type `SuggestionProvider<S>` which is used to handle suggestions - this field is not present inside `LiteralCommandNode`, meaning that LiteralArguments cannot provide suggestions to users.
>
> We need suggestions to provide tooltips. This is because [`SuggestionProvider` provides us with an instance of `SuggestionsBuilder`](https://github.com/Mojang/brigadier/blob/master/src/main/java/com/mojang/brigadier/suggestion/SuggestionProvider.java#L13), [`SuggestionsBuilder` contains a `List<Suggestion>`](https://github.com/Mojang/brigadier/blob/cf754c4ef654160dca946889c11941634c5db3d5/src/main/java/com/mojang/brigadier/suggestion/SuggestionsBuilder.java#L20) and the [`Suggestion` class contains `Message`](https://github.com/Mojang/brigadier/blob/cf754c4ef654160dca946889c11941634c5db3d5/src/main/java/com/mojang/brigadier/suggestion/Suggestion.java#L14). This `Message` class is what is needed to display a tooltip to users.

-----

## Can I change the message that is sent to the user when they don't have permission to run a command?

No. That message is handled client-side and isn't controlled by the CommandAPI. An alternative solution is to perform permission checking _inside_ the command and return a custom message if it's not satisfied:

```java
new CommandAPICommand("mycommand")
    .withPermission("some.permission")
    .executes((sender, args) -> {
        sender.sendMessage("Hello!");
    })
    .register();
```

\\[\downarrow\\]

```java
new CommandAPICommand("mycommand")
    .executes((sender, args) -> {
        if(!sender.hasPermission("some.permission")) {
            CommandAPI.fail("You don't have permission to run /mycommand!");
            return;
        }
        sender.sendMessage("Hello!");
    })
    .register();
```

-----

## Can I change the message that is sent to the user when an argument isn't valid?

No. That message is handled client-side and isn't controlled by the CommandAPI.