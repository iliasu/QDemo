package com.elvenrings.qdemo.view.events;

import java.util.Map;

import com.elvenrings.qdemo.interfaces.SwingRenderer;

public class GroupGradeResultEvent
{
	private int correct;
	private int total;
	private Map<SwingRenderer,Boolean> rendererMap;
	
	public GroupGradeResultEvent(Map<SwingRenderer,Boolean> rendererMap, int correct, int total)
	{
		this.correct = correct;
		this.total = total;
		this.rendererMap = rendererMap;
	}
	public int getCorrect()
	{
		return correct;
	}
	public int getTotal()
	{
		return total;
	}
	public Map<SwingRenderer, Boolean> getRendererMap()
	{
		return rendererMap;
	}
}
