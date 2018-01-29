package com.elvenrings.qdemo.test;

import com.elvenrings.qdemo.exceptions.InvalidChoiceException;
import com.elvenrings.qdemo.model.SingleChoice;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.view.console.SingleChoiceConsoleRenderer;

public class Test
{

	public static void main(String[] args)
	{

		SingleChoiceQuestion sc1 = new SingleChoiceQuestion();
		sc1.addPreambleText("For the next 3 questions select the continent");
		sc1.addPreambleText("where the stated river can be found.");
		// Preamble p1 = sc1.getPreamble();
		sc1.addBodyText("");
		sc1.addBodyText("Nile");
		SingleChoice singleChoice = (SingleChoice) sc1.getChoice();

		singleChoice.addOption(1, "Africa");
		singleChoice.addOption(2, "North America");
		singleChoice.addOption(3, "South America");
		singleChoice.addOption(4, "Europe");
		singleChoice.addOption(5, "Asia");

		try
		{
			singleChoice.setCorrectChoice(1);
		} catch (InvalidChoiceException e)
		{
			e.printStackTrace();
		}
		SingleChoiceConsoleRenderer singleChoiceConsoleRenderer = new SingleChoiceConsoleRenderer(sc1);
		System.out.println(singleChoiceConsoleRenderer.render());

	}
}
