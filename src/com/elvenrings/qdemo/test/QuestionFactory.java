package com.elvenrings.qdemo.test;

import java.util.ArrayList;
import java.util.List;

import com.elvenrings.qdemo.exceptions.InvalidChoiceException;
import com.elvenrings.qdemo.interfaces.Questionable;
import com.elvenrings.qdemo.model.FillBlank;
import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoice;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.SingleChoice;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;

public class QuestionFactory
{
	private QuestionFactory()
	{

	}

	public static Questionable GetQuestion(QuestionType type, boolean sample)
	{
		if (sample)
		{
			if (type == QuestionType.FILLBLANK)
			{
				return newFillInTheBlank();
			}
			if (type == QuestionType.SINGLECHOICE)
			{
				return newSingleChoice();
			}
			if (type == QuestionType.MULTICHOICE)
			{
				return newMultiChoice();
			}

		}
		else
		{
			if (type == QuestionType.FILLBLANK)
			{
				return emptyFillBlankQuestion();
			}
			if (type == QuestionType.SINGLECHOICE)
			{
				return emptySingleChoiceQuestion();
			}
			if (type == QuestionType.MULTICHOICE)
			{
				return emptyMultipleChoiceQuestion();
			}
			
				
		}
		return null;
		
	}

	private static MultipleChoiceQuestion emptyMultipleChoiceQuestion()
	{
		return new MultipleChoiceQuestion();
	}

	private static SingleChoiceQuestion emptySingleChoiceQuestion()
	{
		return new SingleChoiceQuestion();
	}

	private static FillBlankQuestion emptyFillBlankQuestion()
	{
		return new FillBlankQuestion();
	}

	private static Questionable newMultiChoice()
	{
		MultipleChoiceQuestion question = new MultipleChoiceQuestion();
		question.addBodyText("<html>Select below <strong>ONLY</strong> those planets that have rings.<html>");
		MultipleChoice multiChoice = (MultipleChoice) question.getChoice();

		multiChoice.addOption(1, "Mars");
		multiChoice.addOption(2, "Jupiter");
		multiChoice.addOption(3, "Saturn");
		multiChoice.addOption(4, "Venus");
		multiChoice.addOption(5, "Uranus");
		multiChoice.addOption(6, "Neptune");

		List<Integer> corrects = new ArrayList<>();
		corrects.add(2);
		corrects.add(3);
		corrects.add(5);
		corrects.add(6);
		try
		{
			multiChoice.setCorrectChoices(corrects);
		} catch (InvalidChoiceException e)
		{
			e.printStackTrace();
			return null;
		}

		return question;

	}

	private static Questionable newSingleChoice()
	{
		SingleChoiceQuestion question = new SingleChoiceQuestion();
		question.addBodyText("How many planets in the solar system have rings?");
		SingleChoice singleChoice = (SingleChoice) question.getChoice();

		singleChoice.addOption(1, "two");
		singleChoice.addOption(2, "three");
		singleChoice.addOption(3, "four");
		singleChoice.addOption(4, "five");

		try
		{
			singleChoice.setCorrectChoice(3);
		} catch (InvalidChoiceException e)
		{
			e.printStackTrace();
			return null;
		}

		return question;
	}

	private static Questionable newFillInTheBlank()
	{
		FillBlankQuestion question = new FillBlankQuestion();
		question.addBodyText("What is the hottest planet in the solar system?");

		FillBlank fillBlank = (FillBlank) question.getChoice();
		String[] answers = { "(?i)venus" };
		fillBlank.setPatterns(answers);

		return question;
	}
}
