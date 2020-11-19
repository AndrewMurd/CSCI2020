public class StudentRecord {
    public String Id;
    public float Assignments;
    public float Midterm;
    public float Exam;
    public float FinalMark;
    public char LetterGrade;

    public StudentRecord(String id, float assign, float mid, float fin){
        this.Id = id;
        this.Assignments = assign;
        this.Midterm = mid;
        this.Exam = fin;
        mark();
    }

    public void mark(){
        FinalMark = Assignments * 0.20f + Midterm * 0.30f + Exam * 0.50f;

        if (FinalMark < 50f){
            LetterGrade = 'F';
        } else if (FinalMark > 49 && FinalMark < 60){
            LetterGrade = 'D';
        } else if (FinalMark > 59 && FinalMark < 70){
            LetterGrade = 'C';
        } else if (FinalMark > 69 && FinalMark < 80){
            LetterGrade = 'B';
        } else if (FinalMark > 79){
            LetterGrade = 'A';
        }
    }

    public String getId(){ return Id; }

    public float getAssignments(){return Assignments;}

    public float getMidterm(){return Midterm;}

    public float getExam(){return Exam;}

    public float getFinalMark(){return FinalMark;}

    public char getLetterGrade(){return LetterGrade;}
}
