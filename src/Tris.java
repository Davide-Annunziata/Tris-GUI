import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Tris implements ActionListener {
    Random rand =new Random();
    JFrame frame =new JFrame();
    JButton[] buttons=new JButton[9];
    JPanel textPanel=new JPanel();
    JPanel buttonPanel=new JPanel();
    JLabel textLabel =new JLabel();

    JCheckBox changeMode;

    boolean botmode;

    JButton newGame;

    boolean playerTurn=true;
    Tris(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tris");
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        newGame=new JButton("Nuova partita");
        newGame.setFocusable(false);
        newGame.setForeground(new Color(0x123456));
        newGame.setBackground(Color.BLACK);
        newGame.setFont(new Font("JetBrains mono",Font.BOLD,14));
        newGame.addActionListener(this);

        textLabel.setText("TRIS");
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(new Color(0x123456));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("JetBrains mono",Font.PLAIN,75));
        textLabel.setOpaque(true);

        changeMode=new JCheckBox("gioca contro bot");
        changeMode.setFocusable(false);
        changeMode.setForeground(new Color(0x123456));
        changeMode.setBackground(Color.BLACK);
        changeMode.setFont(new Font("JetBrains mono",Font.BOLD,14));
        changeMode.addActionListener(this);

        textPanel.setPreferredSize(new Dimension(800,100));
        textPanel.setLayout(new BorderLayout());



        buttonPanel.setLayout(new GridLayout(3,3,3,3));
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



        textPanel.add(textLabel,BorderLayout.CENTER);
        textPanel.add(changeMode,BorderLayout.WEST);
        textPanel.add(newGame,BorderLayout.EAST);
        frame.add(textPanel,BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.setVisible(true);

        firstTurn();
    }

    //firstTurn()
    public void firstTurn(){
        try {
            sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(rand.nextBoolean()){
            playerTurn=true;
            textLabel.setText("Turno X");
        }else if(botmode){
            playerTurn=false;
            textLabel.setText("Turno O");
            botMove();
        }else{
            playerTurn=false;
            textLabel.setText("Turno O");
        }
    }
    //check()
    public void check(){
        //combinazioni X
        if(buttons[0].getText()=="X"&& buttons[1].getText()=="X"&& buttons[2].getText()=="X") xWins(0,1,2);
        else if(buttons[3].getText()=="X"&& buttons[4].getText()=="X"&& buttons[5].getText()=="X") xWins(3,4,5);
        else if(buttons[6].getText()=="X"&& buttons[7].getText()=="X"&& buttons[8].getText()=="X") xWins(6,7,8);
        else if(buttons[0].getText()=="X"&& buttons[3].getText()=="X"&& buttons[6].getText()=="X") xWins(0,3,6);
        else if(buttons[1].getText()=="X"&& buttons[4].getText()=="X"&& buttons[7].getText()=="X") xWins(1,4,7);
        else if(buttons[2].getText()=="X"&& buttons[5].getText()=="X"&& buttons[8].getText()=="X") xWins(2,5,8);
        else if(buttons[0].getText()=="X"&& buttons[4].getText()=="X"&& buttons[8].getText()=="X") xWins(0,4,8);
        else if(buttons[2].getText()=="X"&& buttons[4].getText()=="X"&& buttons[6].getText()=="X") xWins(2,4,6);


        //Combinationi O
        else if(buttons[0].getText()=="O"&& buttons[1].getText()=="O"&& buttons[2].getText()=="O") oWins(0,1,2);
        else if(buttons[3].getText()=="O"&& buttons[4].getText()=="O"&& buttons[5].getText()=="O") oWins(3,4,5);
        else if(buttons[6].getText()=="O"&& buttons[7].getText()=="O"&& buttons[8].getText()=="O") oWins(6,7,8);
        else if(buttons[0].getText()=="O"&& buttons[3].getText()=="O"&& buttons[6].getText()=="O") oWins(0,3,6);
        else if(buttons[1].getText()=="O"&& buttons[4].getText()=="O"&& buttons[7].getText()=="O") oWins(1,4,7);
        else if(buttons[2].getText()=="O"&& buttons[5].getText()=="O"&& buttons[8].getText()=="O") oWins(2,5,8);
        else if(buttons[0].getText()=="O"&& buttons[4].getText()=="O"&& buttons[8].getText()=="O") oWins(0,4,8);
        else if(buttons[2].getText()=="O"&& buttons[4].getText()=="O"&& buttons[6].getText()=="O") oWins(2,4,6);
        else checkDraw();
    }

    public void checkDraw(){
        int x=0;
        for(int i=0;i<9;i++){
            if(buttons[i].getText()!=""){
                x++;
            }
        }
        if (x==9) draw();
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
    public void draw(){
        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
            textLabel.setText("Pareggio");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==changeMode){
            if(botmode){
                botmode=false;
            }else{
                botmode=true;
                if(!playerTurn){
                    botMove();
                }
            }
        }else if(e.getSource()==newGame){
            this.frame.dispose();
            new Tris();
        }

        for(JButton b: buttons){
            if(e.getSource()==b){
                if(b.getText()=="") {
                    if (playerTurn) {
                        playerTurn=false;
                        b.setText("X");
                        b.setForeground(Color.red);
                        textLabel.setText("Turno O");
                        if(botmode){
                            botMove();
                        }
                    } else if(!botmode) {
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

    private void botMove() {

        int botChoice;
        do {
            botChoice = rand.nextInt(9);
        } while (buttons[botChoice].getText() != "");

        buttons[botChoice].setText("O");
        buttons[botChoice].setForeground(Color.BLUE);
        playerTurn = true;
        textLabel.setText("Turno X");
        check();
    }
}
