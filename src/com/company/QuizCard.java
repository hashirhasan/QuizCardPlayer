package com.company;

public class QuizCard {
    private String question;
    private String answer;
    public QuizCard(String ques,String ans)
    {
        this.question=ques;
        this.answer=ans;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
