package info3.game.model.Entities;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.controller.*;
import info3.game.controller.Conditions.*;
import info3.game.controller.Actions.*;
import info3.game.model.*;


public class Slime extends Entity {
    int life;
    BufferedImage[] m_images;
    int image_index = 0;

    public Slime(IGrille g, int x, int y, Automate a) {
        super(g);
        etat_courant = a.getState();
        this.life=1;
        this.a = a;
        g.getCell(x, y).setEntity(this);
        direction = Direction.Nord;
        this.x = x;
        this.y = y;

        try {
            m_images = Grille.loadSprite("resources/faucheuse.png", 1, 4); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public cellType getType() {
        return cellType.Slime;
    }

    @Override
    public char getCategory() {
        return Category.E;
    }

    public boolean eval_cell(Entity e, DirRelative dir, char type) {
        switch (dir) {
            case Devant:
                if (y == 0) {
                    return type == Category.O;
                }
                return g.getCell(x, y - 1).getCategory() == type;

            case Gauche:
                if (x == 0) {
                    return type == Category.O;
                }
                return g.getCell(x - 1, y).getCategory() == type;

            case Droite:
                if (x == g.getCols() - 1) {
                    return type == Category.O;
                }
                return g.getCell(x + 1, y).getCategory() == type;

            case Derriere:
                if (y == g.getRows() - 1) {
                    return type == Category.O;
                }
                return g.getCell(x, y + 1).getCategory() == type;

            default:
                return g.getCell(x, y).getCategory() == type;
        }
    }

    int animation_elapsed=0;
    int in_movement = -1;
    int nb_frame_move = 7;

    @Override
    public boolean do_move(Entity e, DirRelative dir) {
        in_movement = nb_frame_move;
        switch (dir) {
            case Devant:
                this.y--;
                g.getCell(this.x, this.y + 1).resetEntity();
                g.getCell(this.x, this.y).setEntity(this);
                direction = Direction.Nord;
                return true;
            case Derriere:
                this.y++;
                g.getCell(this.x, this.y - 1).resetEntity();
                g.getCell(this.x, this.y).setEntity(this);
                direction = Direction.Sud;
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean do_egg(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'do_egg'");
    }

    @Override
    public boolean do_pick(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'do_pick'");
    }

    @Override
    public boolean do_pop(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'do_pop'");
    }

    @Override
    public boolean do_wizz(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'do_wizz'");
    }

    @Override
    public boolean do_turn(Entity e, DirRelative dir) {
        if (dir == DirRelative.Devant || dir == DirRelative.Derriere) {
            direction = (direction == Direction.Nord) ? Direction.Sud : Direction.Nord;
            return true;
        }
        return false;
    }

    @Override
    public void paint(Graphics graphics, int x, int y, int width, int height) {
        if (in_movement != -1) {
            if (direction == Direction.Nord) {
                y += (height * in_movement) / nb_frame_move;
            } else if (direction == Direction.Sud) {
                y -= (height * in_movement) / nb_frame_move;
            }
            in_movement--;
        }

        graphics.drawImage(m_images[image_index], x, y, width, height, null);
    }

    @Override
    public void tick(long elapsed) {
        animation_elapsed += elapsed;
        if (animation_elapsed > 200) {
            image_index = (image_index + 1) % 4;
            animation_elapsed = 0;
        }
        
    }
}
