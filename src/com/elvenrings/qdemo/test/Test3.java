package com.elvenrings.qdemo.test;

import com.elvenrings.qdemo.model.FillBlank;
import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.view.console.FillBlankConsoleRenderer;

public class Test3
{

	public static void main(String[] args)
	{
		FillBlankQuestion fb1 = new FillBlankQuestion();
		fb1.addPreambleText("For the questions 1 through 5 each question consists of a word");
		fb1.addPreambleText("in capital letters, followed by five lettered words or phrases.");
		fb1.addPreambleText("provide a word that is most nearly similar");
		fb1.addPreambleText("in meaning to the word in capital letters.");
		fb1.addPreambleText("");
		// Preamble p1 = sc1.getPreamble();
		fb1.addBodyText("");
		fb1.addBodyText("ADULTERATED");

		FillBlank fillBlank = (FillBlank) fb1.getChoice();
		String[] answers = { "contaminate|taint|defile" };
		fillBlank.setPatterns(answers);

		FillBlankConsoleRenderer renderer = new FillBlankConsoleRenderer(fb1);
		System.out.println(renderer.render());

	}

}
