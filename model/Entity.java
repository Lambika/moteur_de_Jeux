package model;

import controller.*;

public abstract class Entity {
    IGrille g;
    public int etat_courant;
    Automaton a;
    public Direction direction;

    int x, y;

    public Entity(IGrille g) {
        this.g = g;
    }

    public abstract cellType getType();

    public void step(BufferAction buff) {
        a.step_A(this,buff);
    }
    
    
    //abstract boolean eval(...);
    public abstract boolean eval_cell(Entity e, DirRelative dir, cellType type);
    //abstract boolean do(...);
    public abstract boolean do_move(Entity e);
    public abstract boolean do_egg(Entity e);
    public abstract boolean do_pick(Entity e);
    public abstract boolean do_turn(Entity e, DirRelative dir);

}
