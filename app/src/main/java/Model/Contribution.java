package Model;

public class Contribution {
    public int id;
    public int type;
    public String goal;
    public int value;

    public Contribution(int type, String goal, int value) {
        this.type = type;
        this.goal = goal;
        this.value = value;
    }

    public Contribution(int type, int value) {
        this.type = type;
        this.value = value;
    }

    public Contribution(int id, int type, String goal, int value) {
        this.id = id;
        this.type = type;
        this.goal = goal;
        this.value = value;
    }

    public Contribution() {
    }

    public int getId(){ return id;}
    public void setId(int id){this.id=id;}
    public int getType(){return type;}
    public void setType(int type){this.type=type;}
    public String getGoal(){return goal;}
    public void setGoal(String goal){this.goal=goal;}
    public int getValue(){return value;}
    public void setValue(int value){this.value=value;}

}

