package ntu.csie.oop13spring;

import java.util.Scanner;

public class Pet1 extends POOPet{   
    private Scanner input_obj = new Scanner(System.in);;
    
    /**
    act defines how the pet would choose a pet
    on the arena (including possibly itself)
    and determine a skill to be used on
    the pet
     */
    public POOAction act(POOArena arena) {
	// Hero
	POOAction ActP1 = new POOAction();
	
	System.out.println("1:Attack  2:Fire  3:cure  4:Suicide");
	int i = input_obj.nextInt();
	if ( i == 1 ) {	    
	    POOSkill attack = new POOTinyAttackSkill();  
	    ActP1.skill = attack;
	    ActP1.dest = ((Arena)arena).p2;
	}
	else if ( i == 2 ) {	      
	    POOSkill fire = new FIRESkill();  
	    ActP1.skill = fire;
	    ActP1.dest = ((Arena)arena).p2;
	}
	else if ( i == 3 ) {
	    POOSkill cure = new CURESkill();  
	    ActP1.skill = cure;
	    ActP1.dest = ((Arena)arena).p1;
	}
	else {
	    POOSkill blew = new BLEW();  
	    ActP1.skill = blew;
	    ActP1.dest = ((Arena)arena).p1;
	}
	return ActP1;
    }
    
 /**
    move defines how the pet would want to move in an arena;
    note that the range of moving should be related to AGI
  */
    public POOCoordinate move(POOArena arena) {	
	POOCoordinate MyCoord = new Coordinate();
	MyCoord = arena.getPosition(((Arena)arena).p1);
	int CanMove = ((Arena)arena).p1.getAGI();
	int i=5;
	// Can keep moving and two pet not meet yet
	while ( CanMove > 0 && i>0) {
	    System.out.println("1:left  2:right  3:up  4:down  0:stay");
	    i = input_obj.nextInt();
	    MyCoord = choiceToMove(MyCoord, i);	// moving
	    CanMove--;
	}
		
	return MyCoord;
    }
    
    public POOCoordinate choiceToMove(POOCoordinate p1, int i) {	
	if	( i == 1 ) {    p1.x--;	}
	else if ( i == 2 ) {    p1.x++;	}
	else if ( i == 3 ) {    p1.y--;	}
	else if ( i == 4 ) {    p1.y++;	}
	return p1;
    }
    
}

class FIRESkill extends POOSkill{
    public void act(POOPet pet){
        int hp = pet.getHP();
        if (hp > 0)
            pet.setHP(hp - 3);
    }
}
class CURESkill extends POOSkill{
    public void act(POOPet pet){
        int hp = pet.getHP();
        if (hp > 0)
            pet.setHP(hp + 1);
    }
}
class BLEW extends POOSkill{
    public void act(POOPet pet){
        pet.setHP(0);
    }
}

class Coordinate extends POOCoordinate{
    public boolean equals(POOCoordinate other) {	
	if (x == other.x && y == other.y)
	    return true;
	return false;
    }
}