package com.elvenrings.qdemo.test;

import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.view.console.FillBlankConsoleRenderer;
import com.elvenrings.qdemo.view.console.MultipleChoiceConsoleRenderer;
import com.elvenrings.qdemo.view.console.SingleChoiceConsoleRenderer;
import com.elvenrings.qdemo.view.events.console.FillBlankConsoleEvent;
import com.elvenrings.qdemo.view.events.console.MultipleChoiceConsoleSelectionEvent;
import com.elvenrings.qdemo.view.events.console.SingleChoiceConsoleSelectionEvent;
import com.elvenrings.qdemo.view.swing.listeners.SubmitConsoleListener;
import com.elvenrings.qdemo.view.swing.markers.FillBlankMarker;
import com.elvenrings.qdemo.view.swing.markers.MultipleChoiceMarker;
import com.elvenrings.qdemo.view.swing.markers.SingleChoiceMarker;

public class Test4 implements SubmitConsoleListener
{
	public Test4()
	{
		SingleChoiceQuestion sc1 = (SingleChoiceQuestion) QuestionFactory.GetQuestion(QuestionType.SINGLECHOICE, true);
		MultipleChoiceQuestion mc1 = (MultipleChoiceQuestion) QuestionFactory.GetQuestion(QuestionType.MULTICHOICE, true);
		FillBlankQuestion fb1 = (FillBlankQuestion) QuestionFactory.GetQuestion(QuestionType.FILLBLANK, true);
		
		FillBlankConsoleRenderer fbcr = new FillBlankConsoleRenderer(fb1);
		SingleChoiceConsoleRenderer sccr = new SingleChoiceConsoleRenderer(sc1);
		MultipleChoiceConsoleRenderer mccr = new MultipleChoiceConsoleRenderer(mc1);
		
		fbcr.addSubmitListener(this);
		sccr.addSubmitListener(this);
		mccr.addSubmitListener(this);
		
		System.out.println(fbcr.render());
		fbcr.getResponse();
		System.out.println("================================================");
		
		
		System.out.println(sccr.render());
		sccr.getResponse();
		System.out.println("================================================");
		
		
		System.out.println(mccr.render());
		mccr.getResponse();
		System.out.println("================================================");
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args)
	{
		new Test4();
	}

	@Override
	public void fillBlankConsoleEventOccurred(FillBlankConsoleEvent e)
	{
		System.out.println(new FillBlankMarker().isCorrect(e));
	}

	@Override
	public void singleChoiceConsoleEventOccurred(SingleChoiceConsoleSelectionEvent e)
	{
		System.out.println(new SingleChoiceMarker().isCorrect(e));
	}

	@Override
	public void multipleChoiceConsoleEventOccurred(MultipleChoiceConsoleSelectionEvent e)
	{
		System.out.println(new MultipleChoiceMarker().isCorrect(e));
	}

}
