//Arshad Khan 100695721
public class StudentRecord {
    private String SID;
    private float Asmt;
    private float Mid;
    private float Exam;
    private float Final;
    private String grade;

    public StudentRecord(String SID, float Asmt, float Mid, float Exam) {
        this.SID = SID;
        this.Asmt = Asmt;
        this.Mid = Mid;
        this.Exam = Exam;
        this.Final = Final;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public float getAsmt() {
        return Asmt;
    }

    public void setAsmt(float asmt) {
        Asmt = asmt;
    }

    public float getMid() {
        return Mid;
    }

    public void setMid(float mid) {
        Mid = mid;
    }

    public float getExam() {
        return Exam;
    }

    public void setExam(float exam) {
        Exam = exam;
    }

    public String getGrade() {
        if (getFinal()>=80 && getFinal()<100) {
            grade= "A";
        } else if (getFinal()==100) {
            grade= "A";
        } else if (getFinal()>=70 && getFinal()<80) {
            grade = "B";
        } else if (getFinal()>=60 && getFinal()<70) {
            grade = "C";
        } else if (getFinal()>=50 && getFinal()<60) {
            grade = "D";
        } else if (getFinal()>=0 && getFinal()<50) {
            grade = "F";
        }

        return grade;
    }

    public float getFinal() {
        this.Final = (float)(.2 * getAsmt()) + (float)(.3 * getMid()) + (float)(.5 * getExam());
        return Final;
    }
}