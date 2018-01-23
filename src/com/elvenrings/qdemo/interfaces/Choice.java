package com.elvenrings.qdemo.interfaces;

import java.util.List;

/**
 * An interface representing the options available for a question.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @version 1.0
 * @since 1.0
 */
public interface Choice
{
	/**
	 * Returns a <code>List</code> of <code>String</code>s representing the options
	 * for a question.
	 * 
	 * @return A list of strings representing the options for a question.
	 */
	public List<String> getOptionList();
}
