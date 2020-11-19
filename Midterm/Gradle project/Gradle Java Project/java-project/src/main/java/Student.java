import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Student {
    private int id;
    private String name;
    private String address;
    private String program;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setProgram(String program){
        this.program = program;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getProgram(){
        return program;
    }

    public String toString(){
        return "Student info: " + id +", " + name+ ", " + address + ", " +program;
    }

    public static void main(String[] args) {
        Student student = new Student();

        student.setId(110079242);
        student.setName("George");
        student.setAddress("Simcoe St, On, Canada");
        student.setProgram("Computer Science");

        System.out.println(student.toString());

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(student);
        System.out.println(json);
    }
}
