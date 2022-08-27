import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tris implements ActionListener {
    Random rand =new Random();
    JFrame frame =new JFrame();
    JButton[] buttons=new JButton[9];
    JPanel textPanel=new JPanel();
    JPanel buttonPanel=new JPanel();
    JLabel textLabel =new JLabel();

    boolean playerTurn=true;
    Tris(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tris");
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());


        textLabel.setText("TRIS");
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(new Color(0x123456));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("JetBrains mono",Font.PLAIN,75));
        textLabel.setOpaque(true);


        textPanel.setPreferredSize(new Dimension(800,100));
        textPanel.setLayout(new BorderLayout());



        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(0x1F7FE1));


        for(int i=0;i<9;i++){
            buttons[i]=new JButton();
            buttons[i].setText("");
            buttons[i].setFont(new Font("JetBrains mono",Font.PLAIN,75));
            buttons[i].setFocusable(false);
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);

        }

        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.setVisible(true);

        firstTurn();
    }

    //firstTurn()
    public void firstTurn(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(rand.nextBoolean()){
            playerTurn=true;
            textLabel.setText("Turno X");
        }else{
            playerTurn=false;
            textLabel.setText("Turno O");
        }
    }
    //check()
    public void check(){
        //combinazioni X
        if(buttons[0].getText()=="X"&& buttons[1].getText()=="X"&& buttons[2].getText()=="X") xWins(0,1,2);
        else checkTie();
        if(buttons[3].getText()=="X"&& buttons[4].getText()=="X"&& buttons[5].getText()=="X") xWins(3,4,5);
        else checkTie();
        if(buttons[6].getText()=="X"&& buttons[7].getText()=="X"&& buttons[8].getText()=="X") xWins(6,7,8);
        else checkTie();

        if(buttons[0].getText()=="X"&& buttons[3].getText()=="X"&& buttons[6].getText()=="X") xWins(0,3,6);
        else checkTie();
        if(buttons[1].getText()=="X"&& buttons[4].getText()=="X"&& buttons[7].getText()=="X") xWins(1,4,7);
        else checkTie();
        if(buttons[2].getText()=="X"&& buttons[5].getText()=="X"&& buttons[8].getText()=="X") xWins(2,5,8);
        else checkTie();

        if(buttons[0].getText()=="X"&& buttons[4].getText()=="X"&& buttons[8].getText()=="X") xWins(0,4,8);
        else checkTie();
        if(buttons[2].getText()=="X"&& buttons[4].getText()=="X"&& buttons[6].getText()=="X") xWins(2,4,6);
        else checkTie();

        //Combinationi O
        if(buttons[0].getText()=="O"&& buttons[1].getText()=="O"&& buttons[2].getText()=="O") oWins(0,1,2);
        else checkTie();
        if(buttons[3].getText()=="O"&& buttons[4].getText()=="O"&& buttons[5].getText()=="O") oWins(3,4,5);
        else checkTie();
        if(buttons[6].getText()=="O"&& buttons[7].getText()=="O"&& buttons[8].getText()=="O") oWins(6,7,8);
        else checkTie();

        if(buttons[0].getText()=="O"&& buttons[3].getText()=="O"&& buttons[6].getText()=="O") oWins(0,3,6);
        else checkTie();
        if(buttons[1].getText()=="O"&& buttons[4].getText()=="O"&& buttons[7].getText()=="O") oWins(1,4,7);
        else checkTie();
        if(buttons[2].getText()=="O"&& buttons[5].getText()=="O"&& buttons[8].getText()=="O") oWins(2,5,8);
        else checkTie();

        if(buttons[0].getText()=="O"&& buttons[4].getText()=="O"&& buttons[8].getText()=="O") oWins(0,4,8);
        else checkTie();
        if(buttons[2].getText()=="O"&& buttons[4].getText()=="O"&& buttons[6].getText()=="O") oWins(2,4,6);
        else checkTie();

    }

    public void checkTie(){
        int x=0;
        for(int i=0;i<9;i++){
            if(buttons[i].getText()!=""){
                x++;
            }
        }
        if (x==9) tie();
    }
    //xWins
    public void xWins(int a,int b,int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        textLabel.setText("X Vince");

        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
    }
    //oWins
    public void oWins(int a,int b,int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        textLabel.setText("O Vince");

        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
    }
    //tie
    public void tie(){

        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton b: buttons){
            if(e.getSource()==b){
                if(b.getText()=="") {
                    if (playerTurn) {
                        playerTurn=false;
                        b.setText("X");
                        b.setForeground(Color.red);
                        textLabel.setText("Turno O");
                    } else {
                        playerTurn=true;
                        b.setText("O");
                        b.setForeground(Color.BLUE);
                        textLabel.setText("Turno X");
                    }
                    check();
                }
            }
        }
    }
}
