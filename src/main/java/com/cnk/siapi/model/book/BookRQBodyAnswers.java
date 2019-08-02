package com.cnk.siapi.model.book;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class BookRQBodyAnswers 
{
	@XmlPath("Question/QuestionID/text()")
	private String questionID;
	@XmlPath("Question/QuestionText/text()")
	private String questionText;
	@XmlPath("Question/AnswerType/text()")
	private String answerType;
	@XmlPath("Question/AnswerExample/text()")
	private String answerExample;
	@XmlPath("Question/RequiredFlag/text()")
	private String answerRequiredFlag;
	@XmlPath("Question/ExtraInfo/text()")
	private String extraInfo;
	@XmlPath("Answer/text()")
	private String answer;
	
	
	public BookRQBodyAnswers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookRQBodyAnswers(String questionID, String questionText, String answerType, String answerExample,
			String answerRequiredFlag, String extraInfo, String answer) {
		super();
		this.questionID = questionID;
		this.questionText = questionText;
		this.answerType = answerType;
		this.answerExample = answerExample;
		this.answerRequiredFlag = answerRequiredFlag;
		this.extraInfo = extraInfo;
		this.answer = answer;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public String getAnswerExample() {
		return answerExample;
	}
	public void setAnswerExample(String answerExample) {
		this.answerExample = answerExample;
	}
	public String getAnswerRequiredFlag() {
		return answerRequiredFlag;
	}
	public void setAnswerRequiredFlag(String answerRequiredFlag) {
		this.answerRequiredFlag = answerRequiredFlag;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
