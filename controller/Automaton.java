package controller;
import model.Entity;

public class Automaton {

    int status;
    Transition[] Trans;

    public Automaton(int s,Transition[] T){
        this.Trans=T;
        this.status=s;
    }
    
    public boolean step_A(Entity e){
        int i=0;
        while (this.Trans[i]!=null) {
            if (e.etat_courant==this.Trans[i].init) {
                if (this.Trans[i].cond.eval(e)) {
                    return this.Trans[i].act.exec(e,);
                }
            }
        i++;
        }
        return true;
        
    }
}
