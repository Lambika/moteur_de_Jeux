package info3.game.controller;

import info3.game.model.Entity;

public class Pick extends Action {
    
    public boolean exec(Entity e) {
        return e.do_pick(e);
    }
}
