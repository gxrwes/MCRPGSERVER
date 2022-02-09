/*******************************************************************************
 * Copyright 2018, 2020 Jorel Ali (Skepter) - MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package dev.jorel.commandapi.arguments;

import org.bukkit.command.CommandSender;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import dev.jorel.commandapi.CommandAPIHandler;
import dev.jorel.commandapi.nms.NMS;

/**
 * An argument that represents any custom object
 *
 * @param <T> the return type of this argument
 */
public class CustomArgument<T> extends Argument {
	
	private CustomArgumentInfoParser<T> infoParser;
	private boolean keyed;
	
	/**
	 * Creates a CustomArgument with a valid parser, defaults to non-keyed argument
	 * 
	 * @param nodeName the name of the node for this argument
	 * @param parser   A {@link CustomArgumentInfo} parser object which includes
	 *                 information such as the command sender, previously declared
	 *                 arguments and current input. This parser should return an
	 *                 object of your choice.
	 */
	public CustomArgument(String nodeName, CustomArgumentInfoParser<T> parser) {
		this(nodeName, parser, false);
	}
	
	/**
	 * Creates a CustomArgument with a valid parser, defaults to non-keyed argument
	 * 
	 * @param nodeName the name of the node for this argument
	 * @param parser   A {@link CustomArgumentInfo} parser object which includes
	 *                 information such as the command sender, previously declared
	 *                 arguments and current input. This parser should return an
	 *                 object of your choice.
	 * @param keyed     Whether this argument can accept Minecraft's
	 *                 <code>NamespacedKey</code> as valid arguments
	 */
	public CustomArgument(String nodeName, CustomArgumentInfoParser<T> parser, boolean keyed) {
		super(nodeName, keyed ? CommandAPIHandler.getInstance().getNMS()._ArgumentMinecraftKeyRegistered() : StringArgumentType.string());
		this.keyed = keyed;
		this.infoParser = parser;
	}
	
	/**
	 * Returns true if this argument is represented by a NamespacedKey
	 * @return true if this argument is represented by a NamespacedKey
	 */
	public boolean isKeyed() {
		return this.keyed;
	}

	@Override
	public Class<T> getPrimitiveType() {
		return null;
	}

	@Override
	public CommandAPIArgumentType getArgumentType() {
		return CommandAPIArgumentType.CUSTOM;
	}
	
	public <CommandListenerWrapper> Object parseCustomArgument(NMS<CommandListenerWrapper> nms,
			CommandContext<CommandListenerWrapper> cmdCtx, String key, Object[] previousArguments) throws CommandSyntaxException {
		String customresult;
		if(this.keyed) {
			customresult = nms.getKeyedAsString(cmdCtx, key);
		} else {
			customresult = cmdCtx.getArgument(key, String.class);
		}
		
		try {
			CustomArgumentInfo info = new CustomArgumentInfo(nms.getCommandSenderFromCSS(cmdCtx.getSource()), previousArguments, 
					customresult);
			return infoParser.apply(info);
		} catch (CustomArgumentException e) {
			throw e.toCommandSyntax(customresult, cmdCtx);
		} catch (Exception e) {
			String errorMsg = new MessageBuilder("Error in executing command ").appendFullInput().append(" - ")
					.appendArgInput().appendHere().toString().replace("%input%", customresult)
					.replace("%finput%", cmdCtx.getInput());
			throw new SimpleCommandExceptionType(() -> {
				return errorMsg;
			}).create();
		}
	}
	
	@Override
	public <CommandListenerWrapper> Object parseArgument(NMS<CommandListenerWrapper> nms,
			CommandContext<CommandListenerWrapper> cmdCtx, String key) throws CommandSyntaxException {
		throw new RuntimeException("parseArgument() is not implemented for CustomArgument. Did you mean parseCustomArgument()?");
	}
	
	/**
	 * MessageBuilder is used to create error messages for invalid argument inputs
	 */
	public static class MessageBuilder {
		StringBuilder builder;
		
		/**
		 * Create a blank message
		 */
		public MessageBuilder() {
			builder = new StringBuilder();
		}
		
		/**
		 * Create a message with an input string
		 * @param str The string to start the message with
		 */
		public MessageBuilder(String str) {
			builder = new StringBuilder(str);
		}
		
		/**
		 * Appends the argument input that the CommandSender used in this command.<br>
		 * For example, if <code>/foo bar</code> was executed and an error occurs
		 * with the CustomArgument <code>bar</code>, then the arg input will append
		 * <code>bar</code> to the end of the message.<br><br> This input is determined
		 * at runtime, and is stored as <code>%input%</code> until executed 
		 * @return A reference to this object
		 */
		public MessageBuilder appendArgInput() {
			builder.append("%input%");
			return this;
		}
		
		/**
		 * Appends the whole input that the CommandSender used in this command.<br>
		 * For example, if <code>/foo bar</code> was executed, then <code>foo bar</code> will be
		 * appended to the end of the message.<br><br> This input is determined
		 * at runtime, and is stored as <code>%finput%</code> until executed 
		 * @return A reference to this object
		 */
		public MessageBuilder appendFullInput() {
			builder.append("%finput%");
			return this;
		}
		
		/**
		 * Appends <code>&lt;--[HERE]</code> to the end of the message
		 * @return A reference to this object
		 */
		public MessageBuilder appendHere() {
			builder.append("<--[HERE]");
			return this;
		}
		
		/**
		 * Appends a string to the end of this message
		 * @param str The string to append to the end of this message
		 * @return A reference to this object
		 */
		public MessageBuilder append(String str) {
			builder.append(str);
			return this;
		}
		
		/**
		 * Appends an object to the end of this message
		 * @param obj The object to append to the end of this message
		 * @return A reference to this object
		 */
		public MessageBuilder append(Object obj) {
			builder.append(obj);
			return this;
		}
		
		/**
		 * Returns the String content of this MessageBuilder
		 * @return the String content of this MessageBuilder
		 */
		@Override
		public String toString() {
			return builder.toString();
		}
	}
	
	/**
	 * An exception used to create command-related errors for the CustomArgument
	 */
	@SuppressWarnings("serial")
	public static class CustomArgumentException extends Exception {

		final String errorMessage;
		final MessageBuilder errorMessageBuilder;
		
		/**
		 * Constructs a CustomArgumentException with a given error message
		 * @param errorMessage the error message to display to the user when this exception is thrown
		 */
		public CustomArgumentException(String errorMessage) {
			this.errorMessage = errorMessage;
			this.errorMessageBuilder = null;
		}
		
		/**
		 * Constructs a CustomArgumentException with a given error message
		 * @param errorMessage the error message to display to the user when this exception is thrown
		 */
		public CustomArgumentException(MessageBuilder errorMessage) {
			this.errorMessage = null;
			this.errorMessageBuilder = errorMessage;
		}
		
		/**
		 * Converts this CustomArgumentException into a CommandSyntaxException 
		 * @param result the argument that the user entered that caused this exception to arise
		 * @param cmdCtx the command context that executed this command
		 * @return a Brigadier CommandSyntaxException
		 */
		public CommandSyntaxException toCommandSyntax(String result, CommandContext<?> cmdCtx) {
			if(errorMessage == null) {
				//Deal with MessageBuilder
				String errorMsg = errorMessageBuilder.toString().replace("%input%", result).replace("%finput%", cmdCtx.getInput());
				return new SimpleCommandExceptionType(() -> {return errorMsg;}).create();
			} else {
				//Deal with String
				return new SimpleCommandExceptionType(new LiteralMessage(errorMessage)).create();
			}
		}
		
	}
	
	/**
	 * A record which contains information which can be passed to the custom
	 * argument's parser.
	 * 
	 * @param sender       the sender that types this argument
	 * @param previousArgs an array of previously declared (parsed) arguments. This
	 *                     can be used as if it were arguments in a command executor
	 *                     method
	 * @param input        the current input which the user has typed for this
	 *                     argument
	 */
	public record CustomArgumentInfo(
		CommandSender sender, 
		Object[] previousArgs, 
		String input) {
	}
	
	/**
	 * A FunctionalInterface that takes in a {@link CustomArgumentInfo}, returns T
	 * and can throw a {@link CustomArgumentException}
	 * 
	 * @param <T> the type that is returned when applying this parser
	 */
	@FunctionalInterface
	public static interface CustomArgumentInfoParser<T> {
		
		/**
		 * Applies a CustomArgumentInfo input to this custom argument parser
		 * 
		 * @param info the custom argument info to apply to this parser
		 * @return the applied output represented by this FunctionalInterface
		 * @throws CustomArgumentException if an error occurs during parsing
		 */
		public T apply(CustomArgumentInfo info) throws CustomArgumentException;
	}
}
