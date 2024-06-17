package info3.game.model.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.controller.*;
import info3.game.controller.Conditions.*;
import info3.game.controller.Actions.*;
import info3.game.model.*;

public class Player1 extends Entity{

    
    
    public Player1(IGrille g, int x, int y, Automate a) {
        super(g);
        life = 6;
        etat_courant = a.getState();
        this.a = a;
        g.getCell(x, y).setEntity(this);
        direction = Direction.Nord;
        this.x = x;
        this.y = y;

         try {
            m_images = Grille.loadSprite("resources/magesquelette.png", 1, 4);
            m_hit = Grille.loadSprite("resources/hit.png", 1, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public cellType getType() {
        return cellType.Player1;
    }

     @Override
    public char getCategory() {
        return Category.H;
    }

    

    
    @Override
    public boolean do_egg(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'do_egg'");
    }
    @Override
    public boolean do_pick(Entity e) {
        return true;
    }

    @Override
    public boolean do_pop(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'do_move'");
    }

    @Override
    public boolean do_wizz(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'do_move'");
    }

    @Override
    public boolean do_turn(Entity e, DirRelative dir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'do_turn'");
    }

    
    @Override
    public boolean do_hit(Entity e, DirRelative dir) { 
            in_hit = true;
            switch (dir) {

            case Devant:
                g.getCell(this.x, this.y -1).GetEntity().get_hit(1);
                direction = Direction.Nord;
                return true;
            case Derriere:
                g.getCell(this.x, this.y + 1).GetEntity().get_hit(1);
                direction = Direction.Sud;
                return true;
            case Droite:
                g.getCell(this.x + 1, this.y).GetEntity().get_hit(1);
                direction = Direction.Est;
                return true;
            case Gauche:
                g.getCell(this.x - 1, this.y).GetEntity().get_hit(1);
                direction = Direction.Ouest;
                return true;
            default:   
                return false;
        }
    
    }
    
    @Override
    public void paint(Graphics graphics, int x, int y, int width, int height) {
        if (in_movement != -1) {
            if (direction == Direction.Nord) {
                y += height * slide[nb_frame_move - in_movement];
            } else if (direction == Direction.Est) {
                x -= width * slide[nb_frame_move - in_movement];
            } else if (direction == Direction.Sud) {
                y -= height * slide[nb_frame_move - in_movement];
            } else if (direction == Direction.Ouest) {
                x += width * slide[nb_frame_move - in_movement];
            }
            in_movement--;
        }
            

        graphics.drawImage(m_images[image_index], x, y, width, height, null);
        if (in_hit) {
            if (direction == Direction.Nord) {
                graphics.drawImage(m_hit[image_hit], x, y-height, width, height, null);
            } else if (direction == Direction.Est) {
                graphics.drawImage(m_hit[image_hit], x+width, y, width, height, null);
            } else if (direction == Direction.Sud) {
                graphics.drawImage(m_hit[image_hit], x, y+height, width, height, null);
            } else if (direction == Direction.Ouest) {
                graphics.drawImage(m_hit[image_hit], x-width, y, width, height, null);
            }

        }

    }

    boolean in_hit = false;
    int hit_anim = 0;
    BufferedImage[] m_hit ;
    int image_hit = 0;

    @Override
    public void tick(long elapsed) {
        if (in_hit) {
            hit_anim +=1;
            if (hit_anim > 70) {
                hit_anim = 0;
                image_hit = (image_hit + 1);
                
            }
        }
        if (image_hit == 4) {
            in_hit = false;
            image_hit = 0;
    
        }

        animation_elapsed += elapsed;
        if (animation_elapsed > 200) {
            image_index = (image_index + 1) % 4;
            animation_elapsed = 0;
        }
        
    }
    }

