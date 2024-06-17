package info3.game.model.Entities;

import info3.game.controller.DirRelative;
import info3.game.controller.Direction;
import info3.game.model.Category;
import info3.game.model.IGrille;
import info3.game.model.cellType;

public abstract class Ennemi extends Entity{
    int life;
    Player1 p;
    public Ennemi(IGrille g, int x, int y) {
        super(g);
        etat_courant = "0";
        direction = Direction.Est;
        this.x = x;
        this.y = y;
        g.getCell(x, y).setEntity(this);
    }

    @Override
    public cellType getType() {
        return cellType.Ennemi;
    }

    @Override
    public char getCategory() {
        return Category.E;
    }

    public boolean Do_hit(Entity E, DirRelative dir) {
        if (E.eval_cell(E, dir, 'H')){
            p.gethit(1);
            return true;
        }
        return false;        
    }
    public void get_hit(int damage) {
        p.setLife(life-damage);
    }

}
