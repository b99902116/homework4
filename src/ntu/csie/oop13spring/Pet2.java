package ntu.csie.oop13spring;

import java.util.Scanner;

public class Pet2 extends POOPet{   
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
	MyCoord = arena.getPosition(((Arena)arena).p2);
	System.out.println(MyCoord.x + " " + MyCoord.y);
	int CanMove = ((Arena)arena).p2.getAGI();
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

class ICESkill extends POOSkill{
    public void act(POOPet pet){
        int hp = pet.getHP();
        if (hp > 0)
            pet.setHP(hp - 1);
        
        int agi = pet.getHP();
        if (agi > 0)
            pet.setHP(agi - 1);
    }
}