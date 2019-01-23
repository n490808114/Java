import java.io.Serializable;

class QuizCard implements Serializable {
    private String quiz;
    private String answer;
    QuizCard(String q,String a){
        quiz = q;
        answer = a;
    }
    String getQuiz(){
        return quiz;
    }
    String getAnswer(){
        return answer;
    }
}