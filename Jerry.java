import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
  Jerry Project for Jerry-Tac-Toe. Draws a Jerry-Tac-Toe boards and allows the user to play against the computer.
  Need to get three on a line in a row to win.  
  @author Jen Gattuso
*/
class Washer extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        System.out.println("Good Game!!");
        System.exit(0);
    }
}

public class Jerry extends JFrame implements ActionListener
{
    //player buttons and choices
    JButton p1,p2,quit,newGame;
    JButton [] buttons;
    JTextArea input;
    pictureclass background;
    //false when game ends
    boolean inGame=true;
    //keeps track if anyone wins
    boolean compWin = false; //changes to true if computer wins
    boolean humWin = false; // changes to true if human wins
    //goes over 9 if all of the spaces are filled
    int fill=0;


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==p1)
        {
            input.setText("\n You are first, choose a space! \n");
            p1.setVisible(false);
            p2.setVisible(false);
        }
        if(e.getSource()==p2)
        {
            computer();
            p1.setVisible(false);
            p2.setVisible(false);
        }
        //makes buttons show
        for(int i=0; i<buttons.length; i++)
        {
            buttons[i].setVisible(true);
        }
        
        //when user chooses a circle
        for(int i=0; i<buttons.length;i++)
        {
            if(e.getSource()==buttons[i])
            {
                if(circles[i].getOcc()==0)
                {
                    fill++;
                    circles[i].change(new Color(114,49,255));
                    circles[i].setOcc(1);//occupied by human
                    input.setText("\n You have chosen circle "+(i+1)+". \n");
                    ifWon(1);
                    if(humWin)
                    { 
                        eraseButtons();
                        input.setText("\n You Win! Congratulations, you got three in a row! \n");
                        newGame.setVisible(true);
                        quit.setVisible(true);
                    }
                    else if(fill>=9)
                    {
                        eraseButtons();
                        input.setText("\n No more available moves. Cats game.  \n");
                        newGame.setVisible(true);
                        quit.setVisible(true);
                    }
                    else {computer();}

                }
                else
                    input.setText("\n Space is already occupied, pick an open one!\n");
            }  
        }
        if(e.getSource()==quit)
        {
            System.out.println("Good Game!!");
            System.exit(0);
        }
        if(e.getSource()==newGame)
        {
            dispose();
            new Jerry();
        }
        repaint();
    }

    public void eraseButtons()
    {
        for(int i=0; i<buttons.length; i++)
        { buttons[i].setVisible(false);}
    }

    public void ifWon(int occupier)
    {
        //  Determines if there is a three in a row or not
        //1-4-8
        if(circles[0].getOcc()==occupier && circles[3].getOcc()==occupier && circles[7].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true; }
            else { compWin=true; }
            circles[0].bigger(); circles[3].bigger(); circles[7].bigger();
        }
        //1-2-3
        if(circles[0].getOcc()==occupier && circles[1].getOcc()==occupier && circles[2].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true;}
            else { compWin=true; }
            circles[0].bigger(); circles[1].bigger(); circles[2].bigger();
        }
        //1-5-9
        if(circles[0].getOcc()==occupier && circles[4].getOcc()==occupier && circles[8].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true;}
            else { compWin=true; }
            circles[0].bigger(); circles[4].bigger(); circles[8].bigger();
        }
        //3-5-7
        if(circles[2].getOcc()==occupier && circles[4].getOcc()==occupier && circles[6].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true;}
            else { compWin=true; }
            circles[2].bigger(); circles[4].bigger(); circles[6].bigger();
        }
        //3-6-8
        if(circles[2].getOcc()==occupier && circles[5].getOcc()==occupier && circles[7].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true;}
            else { compWin=true; }
            circles[2].bigger(); circles[5].bigger(); circles[7].bigger();
        }
        //2-4-7
        if(circles[1].getOcc()==occupier && circles[3].getOcc()==occupier && circles[6].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true;}
            else { compWin=true; }
            circles[1].bigger(); circles[3].bigger(); circles[6].bigger();
        }
        //2-6-9  
        if(circles[1].getOcc()==occupier && circles[5].getOcc()==occupier &&circles[8].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true;}
            else { compWin=true; }
            circles[1].bigger(); circles[5].bigger(); circles[8].bigger();
        }
        //7-8-9
        if(circles[6].getOcc()==occupier && circles[7].getOcc()==occupier && circles[8].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true;}
            else { compWin=true; }
            circles[6].bigger(); circles[7].bigger(); circles[8].bigger();
        }
        //4-5-6
        if(circles[3].getOcc()==occupier && circles[4].getOcc()==occupier && circles[5].getOcc()==occupier)
        { 
            if(occupier==1) { humWin=true;}
            else { compWin=true; }
            circles[3].bigger(); circles[4].bigger(); circles[5].bigger();
        }     
    }
    public void computer()
    {
        //computers turn: chooses a random space that is not occupied
        int move=(int)(Math.random()*8);
        while(circles[move].getOcc()!=0)
        {
            move=(int)(Math.random()*8);     
        }

        circles[move].change(new Color(255,186,49));
        circles[move].setOcc(2);
        input.setText("\n Computer has chosen circle " + (move+1) + ".\n Your turn! \n");
        ifWon(2);
        if(compWin)
        {
            eraseButtons();
            input.setText("\n You Lose! Computer got three in a row, better luck next time. \n");
            newGame.setVisible(true);
            quit.setVisible(true);
        }
        fill++;
        if(fill>=9)
        { 
            eraseButtons();
            input.setText("\n No more available moves. Cats game.  \n");
            newGame.setVisible(true);
            quit.setVisible(true);
        }


    }
 
    //array to create the numbered buttons
    public void butArray()
    {
        buttons = new JButton[9];
        for(int i=0; i<buttons.length; i++)
        {
            buttons[i]=new JButton(Integer.toString(i+1));
            buttons[i].setBounds(25,125+(i*50),50,50);
        }
    }

    //circle class to create the different spaces
    class circle
    {
        int x, y;
        int diameter;
        Color color;
        int occupier;//0=empty,1=human,2=computer

        public circle(int xin, int yin, Color c)
        {
            x=xin; y=yin; diameter=50;
            color=c;
            occupier=0;// CHANGE LATER
        }
        public circle(int xin, int yin)
        {
            x=xin; y=yin; diameter=50;
            color=new Color(75,250,20);
            occupier=0;
        }
        // changes color of circle for when player chooses it
        void change(Color c)
        {
            color=c;
        }

        void draw(Graphics g) // draws the circle
        {
            g.setColor(color);
            g.fillOval(x+100,y, diameter, diameter);
        }
        public void setOcc(int num)
        {
            //whether circle is occupied
            // by human or computer
            occupier=num;
        }
        //returns if occupied/by who
        public int getOcc() { return occupier; }
        //makes circles bigger if they are the three in a row
        public void bigger() { diameter=100; x=x-25; y=y-25; }


 
    }

    circle [] circles;


    class pictureclass extends JPanel
    {
        Color color=new Color(224,1,69);
	    public pictureclass()
	    { setSize(900,700); }
		
	    public void paintComponent(Graphics g)
	    {
            // background color
		    g.setColor(color);
            g.fillRect(100,0,800,650);
            //lines
            g.setColor(new Color(56,255,248));
            g.drawLine(330, 200, 522, 400); // 1-4-8
            g.drawLine(330, 200, 705, 200); // 1-2-3
            g.drawLine(330, 200, 705, 395); // 1-5-9
            g.drawLine(712, 200, 330, 395); // 3-5-7
            g.drawLine(712, 200, 522, 400); // 3-6-8
            g.drawLine(517, 200, 330, 395); // 2-4-7
            g.drawLine(517, 200, 705, 385); // 2-6-9
            g.drawLine(330, 403, 705, 403); // 7-8-9
            g.drawLine(423, 299, 611, 299); // 4-5-6
            //draws each circle
            for(int i=0; i<circles.length; i++) 
                circles[i].draw(g);
            //numbers on cirlces
            g.setColor(new Color(0,0,0));
            g.drawString("1", 320, 205);
            g.drawString("2", 520, 205);
            g.drawString("3", 720, 205);
            g.drawString("4", 420, 306);
            g.drawString("5", 520, 306);
            g.drawString("6", 620, 306);
            g.drawString("7", 320, 405);
            g.drawString("8", 520, 405);
            g.drawString("9", 720, 405);
	    }
    }

    public Jerry()
    {
        addWindowListener( new Washer() );
		setTitle("Jerry-Tac-Toe");
        setSize(900,700); // in pixels

        //adds the circles to array
        circles=new circle[9];
        int left = 200;//for the placement of the first circle
        for(int i=0; i<circles.length; i++)
        {
            if(i<3) 
            { circles[i]=new circle(left+(200*i),175); }
            else if(i<6) 
            { circles[i]=new circle(left+(100*(i-2)),275); }
            else 
            { circles[i]=new circle(left+(200*(i-6)),375); }
        }

        Container game=getContentPane();
        game.setLayout( new BorderLayout() );

        background=new pictureclass();
        game.add( background );

        p1=new JButton("Player 1");
        p1.setBounds(5,25,90,45);
        game.add(p1,"South");
        p1.addActionListener(this);
        p2=new JButton("Player 2");
        p2.setBounds(5,65,90,45);
        game.add(p2,"South");
        p2.addActionListener(this);
        newGame=new JButton("New Game");
        newGame.setBounds(5,525,90,50);
        game.add(newGame, "South");
        newGame.addActionListener(this);
        quit=new JButton("Quit");
        quit.setBounds(5,575,90,50);
        game.add(quit, "South");
        quit.addActionListener(this);
        newGame.setVisible(false);
        quit.setVisible(false);

        //adds all the numbered buttons to the screen
        butArray();
        for(int i=0; i<buttons.length;i++)
        {
            game.add(buttons[i],"South");
            buttons[i].addActionListener(this);
            buttons[i].setVisible(false);
        }

        input=new JTextArea("\nChoose Player 1 or 2"+"\n");
        game.add( input,"South" );

        setVisible(true);
    }


    public static void main(String [] args)
	{
       Jerry tactoe=new Jerry();
	}
}