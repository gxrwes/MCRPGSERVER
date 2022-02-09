/*******************************************************************************
 * Copyright 2018, 2021 Jorel Ali (Skepter) - MIT License
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
package dev.jorel.commandapi;

/**
 * A class to contain information about how to configure the CommandAPI during
 * its loading step.
 */
public class CommandAPIConfig {
	
	// The default configuration. This should mirror the commandapi-plugin
	// config.yml file.
	boolean verboseOutput = false;
	boolean silentLogs = false;
	boolean useLatestNMSVersion = false;
	String missingExecutorImplementationMessage = "This command has no implementations for %s";
	
	/**
	 * Sets verbose output logging for the CommandAPI if true.
	 * @param value whether verbose output should be enabled
	 * @return this CommandAPIConfig
	 */
	public CommandAPIConfig verboseOutput(boolean value) {
		this.verboseOutput = value;
		return this;
	}
	
	/**
	 * Silences all logs (including warnings, but not errors) for the CommandAPI if true.
	 * @param value whether logging suppression should be enabled 
	 * @return this CommandAPIConfig
	 */
	public CommandAPIConfig silentLogs(boolean value) {
		this.silentLogs = value;
		return this;
	}

	/**
	 * Sets whether the CommandAPI should run the latest available version of NMS
	 * support, regardless of Minecraft version. This may produce unexpected results
	 * if the latest NMS version is not supported by the CommandAPI. This can be
	 * used to potentially provide compatibility with future Minecraft versions
	 * before the CommandAPI pushes a release to support it.
	 * 
	 * @param value whether the latest version of NMS should be used
	 * @return this CommandAPIConfig
	 */
	public CommandAPIConfig useLatestNMSVersion(boolean value) {
		this.useLatestNMSVersion = value;
		return this;
	}
	
	/**
	 * Sets the message to display to users when a command has no executor. Available formatting parameters are:
	 * 
	 * <ul> <li>%s - the executor class (lowercase)</li>
	 * <li>%S - the executor class (normal case)</li></ul>
	 * 
	 * @param value the message to display when a command has no executor 
	 * @return this CommandAPIConfig
	 */
	public CommandAPIConfig missingExecutorImplementationMessage(String value) {
		this.missingExecutorImplementationMessage = value;
		return this;
	}
	
}