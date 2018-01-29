package com.elvenrings.qdemo.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.elvenrings.qdemo.exceptions.InvalidChoiceException;
import com.elvenrings.qdemo.model.FillBlank;
import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoice;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.model.SingleChoice;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;

public class ReadQuestions
{
	public List<Question> questions ;
	public ReadQuestions(String XMLFile) throws Exception
	{
		XMLReader reader = XMLReaderFactory.createXMLReader();
		QdemoHandler handler = new QdemoHandler();
		reader.setContentHandler(handler);
		InputSource source = new InputSource(XMLFile);
		reader.parse(source);
		questions = handler.getQuestions();
	}

	public static void main(String[] args) throws SAXException, IOException
	{
		
	}

}

class QdemoHandler extends DefaultHandler
{
	List<Question> questions = new ArrayList<>();
	String currentElement = "";
	String myNamespace = "http://elvenrings.com/qdemo";
	Question currentQuestion = null;
	String questionType = null;
	MultipleChoice currentMultipleChoice = null;
	List<Integer> correctChecks = null;
	private int sequence = 0;
	
	public List<Question> getQuestions()
	{
		return questions;
	}
	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException
	{
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		if(localName.equals("fill-blank"))
		{
			currentQuestion = new FillBlankQuestion();
			questionType="fill-blank";
			currentElement="fill-blank";
		}
		if(localName.equals("single-choice"))
		{
			currentQuestion = new SingleChoiceQuestion();
			questionType="single-choice";
			currentElement ="single-choice";
		}
		
		if(localName.equals("multiple-choice"))
		{
			currentQuestion = new MultipleChoiceQuestion();
			questionType="multiple-choice";
			currentElement = "multiple-choice";
		}
		
		if(localName.equals("radio"))
		{
			currentElement = "radio";
		}
		if(localName.equals("questions"))
		{
			currentElement = "questions";
		}
		if(localName.equals("question"))
		{
			currentElement = "question";
		}
		
		if(localName.equals("correct"))
		{
			currentElement = "correct";
		}
		
		if(localName.equals("correctRegex"))
		{
			currentElement = "correctRegex";
		}
		
		if(localName.equals("options"))
		{
			currentElement = "options";
			sequence = 0;
		}
		
		if(localName.equals("parts"))
		{
			currentElement = "parts";
		}
		if(localName.equals("bodies"))
		{
			currentElement = "bodies";
		}
		if(localName.equals("body"))
		{
			currentElement = "body";
		}
		
		if(localName.equals("preambles"))
		{
			currentElement = "preambles";
		}
		
		if(localName.equals("preamble"))
		{
			currentElement = "preamble";
		}
		if(localName.equals("correctRadio"))
		{
			currentElement = "correctRadio";
		}
		if(localName.equals("check"))
		{
			currentElement = "check";
		}
		if(localName.equals("correctChecks"))
		{
			MultipleChoiceQuestion q = (MultipleChoiceQuestion) currentQuestion;
			currentMultipleChoice = (MultipleChoice) q.getChoice();
			correctChecks = new ArrayList<>();
			currentElement = "correctChecks";
		}
		if(localName.equals("attempts"))
		{
			currentElement = "attempts";
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if(localName.equals("correctChecks"))
		{
			try
			{
				currentMultipleChoice.setCorrectChoices(correctChecks);
			} catch (InvalidChoiceException e)
			{
				throw new SAXException(e);
			}
		}
		if(localName.equals("fill-blank"))
		{
			questions.add(currentQuestion);
		}
		if(localName.equals("single-choice"))
		{
			questions.add(currentQuestion);
		}
		if(localName.equals("multiple-choice"))
		{
			questions.add(currentQuestion);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		String s = new String(ch, start, length);
		System.out.println("questionType:" + questionType + ", " +currentElement + ":    " + s);

		if(currentElement.equals("preamble"))
		{
			currentQuestion.addPreambleText(s);
		}
		if(currentElement.equals("body"))
		{
			currentQuestion.addBodyText(s);
		}
		if(currentElement.equals("correctRegex"))
		{
			FillBlankQuestion q = (FillBlankQuestion) currentQuestion ;
			FillBlank choice = (FillBlank) q.getChoice();
			choice.setPatterns(new String[] {s});
		}
		
		if(currentElement.equals("radio"))
		{
			if(questionType.equalsIgnoreCase("single-choice"))
			{
				SingleChoiceQuestion q = (SingleChoiceQuestion)currentQuestion;
				SingleChoice c = (SingleChoice) q.getChoice();
				c.addOption(++sequence, s);
			}
		}
		
		if(currentElement.equals("check"))
		{
			if(questionType.equalsIgnoreCase("multiple-choice"))
			{
				MultipleChoiceQuestion q = (MultipleChoiceQuestion)currentQuestion;
				MultipleChoice c = (MultipleChoice) q.getChoice();
				c.addOption(++sequence, s);
			}
		}
		
		if(currentElement.equals("attempts"))
		{
			currentQuestion.setAttempts(Integer.parseInt(s));
		}
		if(currentElement.equals("correct"))
		{
			if(questionType.equals("single-choice"))
			{
				SingleChoiceQuestion q = (SingleChoiceQuestion) currentQuestion;
				SingleChoice choice = (SingleChoice) q.getChoice();
				try
				{
					choice.setCorrectChoice(Integer.parseInt(s));
				} catch (NumberFormatException e)
				{
					throw new SAXException(e);
				} catch (InvalidChoiceException e)
				{
					throw new SAXException(e);
				}
			}
			if(questionType.equals("multiple-choice"))
			{
				try
				{
					correctChecks.add(Integer.parseInt(s));
				} catch (NumberFormatException e)
				{
					throw new SAXException(e);
				}
			}
		}
		
	}
	
}