package net.minecraft.commands.arguments;

import com.google.common.collect.Lists;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.selector.ArgumentParserSelector;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.network.chat.IChatBaseComponent;

public class ArgumentChat implements ArgumentType<ArgumentChat.a> {

    private static final Collection<String> EXAMPLES = Arrays.asList("Hello world!", "foo", "@e", "Hello @p :)");

    public ArgumentChat() {}

    public static ArgumentChat message() {
        return new ArgumentChat();
    }

    public static IChatBaseComponent getMessage(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return ((ArgumentChat.a) commandcontext.getArgument(s, ArgumentChat.a.class)).toComponent((CommandListenerWrapper) commandcontext.getSource(), ((CommandListenerWrapper) commandcontext.getSource()).hasPermission(2));
    }

    public ArgumentChat.a parse(StringReader stringreader) throws CommandSyntaxException {
        return ArgumentChat.a.parseText(stringreader, true);
    }

    public Collection<String> getExamples() {
        return ArgumentChat.EXAMPLES;
    }

    public static class a {

        private final String text;
        private final ArgumentChat.b[] parts;

        public a(String s, ArgumentChat.b[] aargumentchat_b) {
            this.text = s;
            this.parts = aargumentchat_b;
        }

        public String getText() {
            return this.text;
        }

        public ArgumentChat.b[] getParts() {
            return this.parts;
        }

        public IChatBaseComponent toComponent(CommandListenerWrapper commandlistenerwrapper, boolean flag) throws CommandSyntaxException {
            if (this.parts.length != 0 && flag) {
                ChatComponentText chatcomponenttext = new ChatComponentText(this.text.substring(0, this.parts[0].getStart()));
                int i = this.parts[0].getStart();
                ArgumentChat.b[] aargumentchat_b = this.parts;
                int j = aargumentchat_b.length;

                for (int k = 0; k < j; ++k) {
                    ArgumentChat.b argumentchat_b = aargumentchat_b[k];
                    IChatBaseComponent ichatbasecomponent = argumentchat_b.toComponent(commandlistenerwrapper);

                    if (i < argumentchat_b.getStart()) {
                        chatcomponenttext.append(this.text.substring(i, argumentchat_b.getStart()));
                    }

                    if (ichatbasecomponent != null) {
                        chatcomponenttext.append(ichatbasecomponent);
                    }

                    i = argumentchat_b.getEnd();
                }

                if (i < this.text.length()) {
                    chatcomponenttext.append(this.text.substring(i));
                }

                return chatcomponenttext;
            } else {
                return new ChatComponentText(this.text);
            }
        }

        public static ArgumentChat.a parseText(StringReader stringreader, boolean flag) throws CommandSyntaxException {
            String s = stringreader.getString().substring(stringreader.getCursor(), stringreader.getTotalLength());

            if (!flag) {
                stringreader.setCursor(stringreader.getTotalLength());
                return new ArgumentChat.a(s, new ArgumentChat.b[0]);
            } else {
                List<ArgumentChat.b> list = Lists.newArrayList();
                int i = stringreader.getCursor();

                while (stringreader.canRead()) {
                    if (stringreader.peek() == '@') {
                        int j = stringreader.getCursor();

                        EntitySelector entityselector;

                        try {
                            ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(stringreader);

                            entityselector = argumentparserselector.parse();
                        } catch (CommandSyntaxException commandsyntaxexception) {
                            if (commandsyntaxexception.getType() != ArgumentParserSelector.ERROR_MISSING_SELECTOR_TYPE && commandsyntaxexception.getType() != ArgumentParserSelector.ERROR_UNKNOWN_SELECTOR_TYPE) {
                                throw commandsyntaxexception;
                            }

                            stringreader.setCursor(j + 1);
                            continue;
                        }

                        list.add(new ArgumentChat.b(j - i, stringreader.getCursor() - i, entityselector));
                    } else {
                        stringreader.skip();
                    }
                }

                return new ArgumentChat.a(s, (ArgumentChat.b[]) list.toArray(new ArgumentChat.b[0]));
            }
        }
    }

    public static class b {

        private final int start;
        private final int end;
        private final EntitySelector selector;

        public b(int i, int j, EntitySelector entityselector) {
            this.start = i;
            this.end = j;
            this.selector = entityselector;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public EntitySelector getSelector() {
            return this.selector;
        }

        @Nullable
        public IChatBaseComponent toComponent(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
            return EntitySelector.joinNames(this.selector.findEntities(commandlistenerwrapper));
        }
    }
}
