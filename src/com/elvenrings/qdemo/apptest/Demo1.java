package com.elvenrings.qdemo.apptest;

import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.test.QList;
import com.elvenrings.qdemo.test.QuestionFactory;
import com.elvenrings.qdemo.test.QuestionType;
import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.DefaultSwingRenderer;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

public class Demo1 implements SubmitSwingListener
{

	public static void main(String[] args)
	{
		QList<DefaultSwingRenderer> qlist = new QList<>();
		Demo1 me = new Demo1();
		qlist.addListener(me);
		
		SingleChoiceQuestion sc1 = (SingleChoiceQuestion) QuestionFactory.GetQuestion(QuestionType.SINGLECHOICE, true);
		MultipleChoiceQuestion mc1 = (MultipleChoiceQuestion) QuestionFactory.GetQuestion(QuestionType.MULTICHOICE,
				true);
		FillBlankQuestion fb1 = (FillBlankQuestion) QuestionFactory.GetQuestion(QuestionType.FILLBLANK, true);
		
		SingleChoiceSwingRenderer scsr = new SingleChoiceSwingRenderer(sc1);
		MultipleChoiceSwingRenderer mcsr = new MultipleChoiceSwingRenderer(mc1);
		FillBlankSwingRenderer fbsr = new FillBlankSwingRenderer(fb1);
		
		qlist.addRenderer(scsr);
		qlist.addRenderer(mcsr);
		qlist.addRenderer(fbsr);
		
		
		
	}

	@Override
	public void fillBlankSwingEventOccurred(FillBlankSwingEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void singleChoiceSwingEventOccurred(SingleChoiceSwingSelectionEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void multipleChoiceSwingEventOccurred(MultipleChoiceSwingSelectionEvent event)
	{
		// TODO Auto-generated method stub
		
	}

}
