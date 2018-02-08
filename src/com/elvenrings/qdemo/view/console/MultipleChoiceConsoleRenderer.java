package com.elvenrings.qdemo.view.console;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.view.events.console.MultipleChoiceConsoleSelectionEvent;
import com.elvenrings.qdemo.view.swing.listeners.SubmitConsoleListener;

/**
 * A <code>ConsoleRenderer</code> implementation that provides the following
 * functionality: <br>
 * 1. Renders all options associated with the question.<br>
 * 2. Renders the input prompt. <br>
 * 3. Retrieves the console input and fires a
 * <code>MultipleChoiceConsoleSelectionEvent</code> to all registered listeners.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class MultipleChoiceConsoleRenderer extends DefaultConsoleRenderer
{
	private static Scanner scanner = new Scanner(System.in);
	private Set<Integer> chosenInput = null;

	/**
	 * Single Parameter constructor accepts a <code>MultipleChoiceQuestion</code> as
	 * the question to Render
	 * 
	 * @param question
	 *            <code>MultipleChoiceQuestion</code> to render
	 */
	public MultipleChoiceConsoleRenderer(MultipleChoiceQuestion question)
	{
		this.question = question;
	}

	/**
	 * Adds a user prompt to the already rendered question and all options
	 * associated with the question. If inputs have been accepted for this question
	 * already, the selected options are marked with a '*'.
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
			if (chosenInput != null && chosenInput.contains(sequence))
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
	 * Accepts user input a fires a <code>MultipleChoiceConsoleSelectionEvent</code>
	 * accordingly for all listeners
	 */
	public void getResponse()
	{
		getInput();
		Choice choice = this.question.getChoice();
		MultipleChoiceConsoleSelectionEvent mce = new MultipleChoiceConsoleSelectionEvent(
				MultipleChoiceConsoleRenderer.this, MultipleChoiceConsoleRenderer.this.chosenInput, choice);
		fireMultipleChoiceEvent(mce);
	}

	/**
	 * Accepts user input. Separate tokens should be delimited by a comma.
	 * 
	 * @return <code>Set</code> of Integers from the user input.
	 */
	public Set<Integer> getInput()
	{
		Set<Integer> answers = null;
		boolean validInput = false;
		String input = "";
		while (!validInput)
		{
			try
			{
				input = scanner.nextLine();
				answers = extractInput(input, ",");
				validInput = true;
			} catch (InputMismatchException | NumberFormatException e)
			{
				System.err.println("Invalid input");
				scanner.next();
			}
		}

		chosenInput = answers;
		return answers;
	}

	private void fireMultipleChoiceEvent(MultipleChoiceConsoleSelectionEvent event)
	{
		SubmitConsoleListener[] consoleListeners = listeners.getListeners(SubmitConsoleListener.class);
		for(int i=0; i< consoleListeners.length; i++)
		{
			consoleListeners[i].multipleChoiceConsoleEventOccurred(event);
		}
	}

	private Set<Integer> extractInput(String line, String tokenizer)
	{
		String[] tokens = line.split(tokenizer);
		Set<Integer> intTokens = new HashSet<>();

		for (int i = 0; i < tokens.length; i++)
		{
			intTokens.add(Integer.parseInt(tokens[i].trim()));
		}

		return intTokens;
	}

}
