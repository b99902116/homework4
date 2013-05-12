package ntu.csie.oop13spring;

public class Arena extends POOArena {
    protected POOPet p1 = new Pet1();
    protected POOPet p2 = new Pet2();
    private static POOCoordinate[] coord = new Coordinate[2];
    private static char [][] maps = new char[8][8];

    final int SAFE = 0;
    final int ICE = 1;
    final int FIRE = 2;
    //final int POISON = 3;
    private int [] status = new int[2];
    
    private boolean[] ready = new boolean[2];
    private int control = 3; // server get a control when starts
    public boolean fight() {
	if ( control==-1 ) {
	    System.out.println("-----Game Over-----");
	    return false;
	}
	
	if ( control == 3 ) {
	    init();
	    control = 0;
	    return true;
	}
	if ( control == 0 ) {
	    if ( p1.getAGI() >= p2.getAGI() )
		control = 1;
	    else 
		control = 2;
	}
	
	start();
	
	if ( p1.getHP()==0 || p2.getHP()==0 )
	    control = -1; // game over
	
	return true;
    }
    public void start() {
	
	POOAction action = new POOAction();
	if ( control == 1 ) { // p1 get a control
	    if ( ready[0] ) { // p1 ready
		System.out.println("~~"+ p1.getName() +" action~~");
		/* action */
		coord[0] = p1.move(this);
		action = p1.act(this);
		action.skill.act(action.dest);
		
		ready[0] = false; // p1 done
		control = 2; 	// give control to p2
	    }
	    else 
		control = 0;	// return control
	}
	else if ( control == 2 ) {
	    if ( ready[1] ) { // p2 ready
		System.out.println("~~"+ p2.getName() +" action~~");
		/* action */
		coord[0] = p2.move(this);

		    System.out.println(coord[0].x + " " + coord[0].y);
			System.out.println(coord[1].x + " " + coord[1].y);
		action = p2.act(this);
		action.skill.act(action.dest);
		
		ready[1] = false; // p2 done
		control = 1; 	// give control to p1
	    }
	    else 
		control = 0;	// return control
	}
	System.out.println(coord[0].x + " " + coord[0].y);
	System.out.println(coord[1].x + " " + coord[1].y);
	updateMaps();
	System.out.println(coord[0].x + " " + coord[0].y);
	System.out.println(coord[1].x + " " + coord[1].y);
    }
    public void updateMaps() {
	clearMaps();
	maps[coord[0].x][coord[0].y] = 'A';
	maps[coord[1].x][coord[1].y] = 'B';
    }
    
    public void show() {
	for (int i=0; i<8; i++) {
	    for (int j=0; j<8; j++)
		System.out.print( maps[i][j] + " ");
	    System.out.println("");
	}
	System.out.println(p1.getName() +"~ HP:"+ p1.getHP() +" MP:"+ p1.getMP() +" AGI:"+ p1.getAGI() );
	System.out.println(p2.getName() +"~ HP:"+ p2.getHP() +" MP:"+ p2.getMP() +" AGI:"+ p2.getAGI() );
    }
    
    public POOCoordinate getPosition(POOPet p) {
	if (p.getName() == "p1")
	    return coord[0];
	return coord[1];  
    }  
    
    public void init() {
	clearMaps();	
	p1.setName("p1");
	p2.setName("p2");
	initPet(p1);
	initPet(p2);
	addPet(p1);
	addPet(p2);
	initPosition();
    }
    public void clearMaps() {
	for (int i=0; i<8; i++)
	    for (int j=0; j<8; j++)
		maps[i][j] = '.';
    }
    public void initPosition() {
	coord[0] = new Coordinate();
	coord[1] = new Coordinate();
	
	coord[0].x=0;
	coord[0].y=0;
	coord[1].x=7;
	coord[1].y=7;
	updateMaps();
    }
    public void initPet(POOPet p) {
	if (p.getName()=="p1") { 
	   status[0] = SAFE;
	   ready[0] = true;
    	   p.setHP(10);
    	   p.setMP(5);
    	   p.setAGI(1);
	}
	else {
	    status[1] = SAFE;
	    ready[1] = true;
	    p.setHP(5);
	    p.setMP(10); 
	    p.setAGI(2);
	}	
    }
}
