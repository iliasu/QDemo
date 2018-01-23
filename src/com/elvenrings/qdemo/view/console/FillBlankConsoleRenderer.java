package com.elvenrings.qdemo.view.console;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.view.events.console.FillBlankConsoleEvent;
import com.elvenrings.qdemo.view.swing.listeners.SubmitConsoleListener;

/**
 * A <code>ConsoleRenderer</code> implementation that provides the following
 * functionality: <br>
 * 1. Renders the input prompt. <br>
 * 2. Retrieves the console input and fires a <code>FillBlankConsoleEvent</code>
 * to all registered listeners.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class FillBlankConsoleRenderer extends DefaultConsoleRenderer
{

	private static Scanner scanner = new Scanner(System.in);
	private String[] input = null;

	/**
	 * Single Parameter constructor accepts a <code>FillBlankQuestion</code> as the
	 * question to Render
	 * 
	 * @param question
	 *            <code>FillBlankQuestion</code> to render
	 */
	public FillBlankConsoleRenderer(FillBlankQuestion question)
	{
		this.question = question;
	}

	/**
	 * Adds a user prompt to the already rendered question. If inputs have been
	 * accepted for this question already, they are also rendered as a
	 * comma-separated String.
	 * 
	 * @return A fully rendered Question.
	 */
	@Override
	public String render()
	{
		String output = super.render();
		StringBuilder sb = new StringBuilder();
		StringJoiner sj = new StringJoiner(",");

		if (input != null)
		{
			for (String i : input)
			{
				sj.add(i);
			}
		}
		sb.append(sj.toString());

		sb.append("\n>> ");

		return output + sb.toString();

	}

	/**
	 * Accepts user input a fires a <code>FillBlankConsoleEvent</code> accordingly
	 * for all listeners
	 */
	public void getResponse()
	{
		getInput();
		Choice choice = this.question.getChoice();
		FillBlankConsoleEvent fbe = new FillBlankConsoleEvent(FillBlankConsoleRenderer.this,
				FillBlankConsoleRenderer.this.input, choice);
		fireBlankFilledEvent(fbe);
	}

	private void fireBlankFilledEvent(FillBlankConsoleEvent event)
	{
		for (SubmitConsoleListener listener : listeners)
		{
			listener.fillBlankConsoleEventOccurred(event);
		}
	}

	/**
	 * Accepts user input. Separate tokens should be delimited by a comma.
	 * 
	 * @return <code>List</code> of Strings from the user input.
	 */
	public List<String> getInput()
	{
		List<String> temp = null;
		boolean validInput = false;
		while (!validInput)
		{
			try
			{
				String input = scanner.nextLine();
				temp = extractInput(input, ",");
				validInput = true;
			} catch (InputMismatchException e)
			{
				System.err.println("Invalid input");
				scanner.next();
			}

			input = temp.toArray(new String[temp.size()]);

			return temp;

		}
		return null;
	}

	private List<String> extractInput(String line, String tokenizer)
	{
		String[] tokens = line.split(tokenizer);
		List<String> strTokens = new ArrayList<>();

		for (int i = 0; i < tokens.length; i++)
		{
			strTokens.add(tokens[i].trim());
		}

		return strTokens;
	}

}
