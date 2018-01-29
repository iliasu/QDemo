package com.elvenrings.qdemo.test;

import java.util.ArrayList;
import java.util.List;

import com.elvenrings.qdemo.exceptions.InvalidChoiceException;
import com.elvenrings.qdemo.model.MultipleChoice;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.view.console.MultipleChoiceConsoleRenderer;

public class Test2
{

	public static void main(String[] args)
	{
		MultipleChoiceQuestion mc1 = new MultipleChoiceQuestion();
		mc1.addPreambleText("For the questions 1 through 3 select the continent");
		mc1.addPreambleText("where the stated river can be found.");
		// Preamble p1 = sc1.getPreamble();
		mc1.addBodyText("");
		mc1.addBodyText("Nile");
		MultipleChoice multiChoice = (MultipleChoice) mc1.getChoice();

		multiChoice.addOption(1, "Africa");
		multiChoice.addOption(2, "North America");
		multiChoice.addOption(3, "South America");
		multiChoice.addOption(4, "Europe");
		multiChoice.addOption(5, "Asia");

		List<Integer> corrects = new ArrayList<>();
		corrects.add(2);
		corrects.add(3);
		try
		{
			multiChoice.setCorrectChoices(corrects);
			MultipleChoiceConsoleRenderer multipleChoiceConsoleRenderer = new MultipleChoiceConsoleRenderer(mc1);
			System.out.println(multipleChoiceConsoleRenderer.render());
		} catch (InvalidChoiceException e)
		{
			e.printStackTrace();
		}

	}

}
