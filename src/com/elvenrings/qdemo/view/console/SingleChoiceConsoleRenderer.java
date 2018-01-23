package com.elvenrings.qdemo.view.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.view.events.console.SingleChoiceConsoleSelectionEvent;
import com.elvenrings.qdemo.view.swing.listeners.SubmitConsoleListener;

/**
 * A <code>ConsoleRenderer</code> implementation that provides the following
 * functionality: <br>
 * 1. Renders all options associated with the question. <br>
 * 2. Renders the input prompt. <br>
 * 3. Retrieves the console input and fires a
 * <code>SingleChoiceConsoleSelectionEvent</code> to all registered listeners.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class SingleChoiceConsoleRenderer extends DefaultConsoleRenderer
{
	private static Scanner scanner = new Scanner(System.in);
	private Integer chosenInput = -1;

	/**
	 * Single Parameter constructor accepts a <code>SingleChoiceQuestion</code> as
	 * the question to Render
	 * 
	 * @param question
	 *            <code>SingleChoiceQuestion</code> to render
	 */
	public SingleChoiceConsoleRenderer(SingleChoiceQuestion question)
	{
		this.question = question;
	}

	/**
	 * Adds a user prompt to the already rendered question and all options
	 * associated with the question. If an input has been accepted for this question
	 * already, the selected option is marked with a '*'.
	 * 
	 * @return A fully rendered Question.
	 */
	@Override
	public String render()
	{
		String output = super.render();
		StringBuilder sb = new StringBuilder();

		Choice choice = question.getChoice();
		for (String str : choice.getOptionList())
		{
			sb.append("(" + ++sequence + ") " + str);
			if (chosenInput == sequence)
			{
				sb.append(" *");
			}
			sb.append("\n");
		}

		sb.append(">> ");
		sequence = 0;
		return output + sb.toString();
	}

	/**
	 * Accepts user input a fires a <code>SingleChoiceConsoleSelectionEvent</code>
	 * accordingly for all listeners
	 */
	public void getResponse()
	{
		getInput();
		Choice choice = this.question.getChoice();
		SingleChoiceConsoleSelectionEvent sce = new SingleChoiceConsoleSelectionEvent(SingleChoiceConsoleRenderer.this,
				SingleChoiceConsoleRenderer.this.chosenInput, choice);
		fireSingleChoiceEvent(sce);
	}

	private void fireSingleChoiceEvent(SingleChoiceConsoleSelectionEvent event)
	{
		for (SubmitConsoleListener listener : listeners)
		{
			listener.singleChoiceConsoleEventOccurred(event);
		}
	}

	/**
	 * Accepts user input. Input should be a single integer value.
	 * 
	 * @return <code>Set</code> of Integers from the user input.
	 */
	public Integer getInput()
	{
		int answer = -1;
		boolean validInput = false;
		while (!validInput)
		{
			try
			{
				answer = scanner.nextInt();
				validInput = true;
			} catch (InputMismatchException e)
			{
				System.err.println("Invalid input");
				scanner.next();
			}
		}

		chosenInput = answer;
		return answer;
	}

}
