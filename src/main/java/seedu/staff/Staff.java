package seedu.staff;
public class Staff {

    protected String id;
    protected String name;
    protected String age;
    protected String specialisation;

    public Staff(String[] list){

        this.id = list[0];
        this.name = list[1];
        this.age = list[2];
        this.specialisation = list[3];
    }

    //Need to implement addNurse and addDoctor if the parameters will be changed

    public String getName() {
        return this.name;
    }
    public String getAge() {
        return this.age;
    }
    public String getSpecialisation() {
        return this.specialisation;
    }
    public String getId() {
        return this.id;
    }
    public String getType() {
        return this.id.substring(0,1);
    }


}
