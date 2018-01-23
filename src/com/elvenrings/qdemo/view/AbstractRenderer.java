package com.elvenrings.qdemo.view;

import com.elvenrings.qdemo.interfaces.Questionable;

/**
 * An abstract class that all renderers overload.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public abstract class AbstractRenderer
{
	protected Questionable question;
	protected int sequence = 0;
}
