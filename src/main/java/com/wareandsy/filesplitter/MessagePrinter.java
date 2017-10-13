/**
 * 
 */
package com.wareandsy.filesplitter;

/**
 * @author fangbe
 *
 */
public interface MessagePrinter {
	
	/**
	 * 
	 * @param message
	 */
	void print(String message);
	
	/**
	 * 
	 * @param message
	 */
	void println(String message);
	
	/**
	 * 
	 * @param e
	 */
	void error(Exception e);
}
