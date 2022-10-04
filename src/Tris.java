import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Tris implements ActionListener {
    Random rand = new Random();
    JFrame frame = new JFrame();
    JButton[] buttons = new JButton[9];
    JPanel textPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textLabel = new JLabel();

    JCheckBox changeMode;

    boolean botmode=true;

    JButton newGame;

    boolean playerTurn = true;

    boolean fine = false;

    Action newGameAction;
    /** Creazione frame principale
     */
    public Tris() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tris");
        frame.setSize(800, 800);
        frame.setMinimumSize(new Dimension(670,600));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        newGame = new JButton("Nuova partita");
        newGame.setFocusable(false);
        newGame.setForeground(new Color(0x123456));
        newGame.setBackground(Color.BLACK);
        newGame.setFont(new Font("JetBrains mono", Font.BOLD, 14));
        newGame.addActionListener(this);

        textLabel.setText("TRIS");
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(new Color(0x123456));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("JetBrains mono", Font.PLAIN, 75));
        textLabel.setOpaque(true);

        changeMode = new JCheckBox("gioca contro bot");
        changeMode.setSelected(true);
        changeMode.setFocusable(false);
        changeMode.setForeground(new Color(0x123456));
        changeMode.setBackground(Color.BLACK);
        changeMode.setFont(new Font("JetBrains mono", Font.BOLD, 14));
        changeMode.addActionListener(this);

        textPanel.setPreferredSize(new Dimension(800, 100));
        textPanel.setLayout(new BorderLayout());

        buttonPanel.setLayout(new GridLayout(3, 3, 4, 4));
        buttonPanel.setBackground(new Color(0x123456));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].setFont(new Font("JetBrains mono", Font.PLAIN, 75));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.GRAY);
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);

        }
        // se l'utente preme N crea una nuova partita
        newGameAction= new newGame();
        textLabel.getInputMap().put(KeyStroke.getKeyStroke("N"),"newGameAction");
        textLabel.getActionMap().put("newGameAction",newGameAction);
        // se l'utente preme ESC l'utente esce
        textLabel.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"escGameAction");
        textLabel.getActionMap().put("escGameAction",new escGame());

        textPanel.add(textLabel, BorderLayout.CENTER);
        textPanel.add(changeMode, BorderLayout.WEST);
        textPanel.add(newGame, BorderLayout.EAST);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.setVisible(true);
        firstTurn();
    }
    //firstTurn()
     /** Nel primo turno genera un boolean
     * casuale per vedere
     * se deve iniziare il player, se
     * questo cosa non si verifica controlla
     * se la {@code @botmode} e fa partire
     * la mossa del bot.
     */
     private void firstTurn() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (rand.nextBoolean()) {
            playerTurn = true;
            textLabel.setText("Turno X");
        } else if (botmode) {
            playerTurn = false;
            textLabel.setText("Turno O");
            botMove();
        } else {
            playerTurn = false;
            textLabel.setText("Turno O");
        }
    }

    //check()
    /** Controlla se il player
     *  o il bot/player2 ha vinto
     *  e richiama la funzione in
     *  base a quello, e se nessuno
     *  ha vinto controlla se c'è
     *  un pareggo e chiama il metodo
     *  che lo controlli.
     */
    private void check() {
        //combinazioni X
        if (buttons[0].getText() == "X" && buttons[1].getText() == "X" && buttons[2].getText() == "X")
            xWins(0, 1, 2);
        else if (buttons[3].getText() == "X" && buttons[4].getText() == "X" && buttons[5].getText() == "X")
            xWins(3, 4, 5);
        else if (buttons[6].getText() == "X" && buttons[7].getText() == "X" && buttons[8].getText() == "X")
            xWins(6, 7, 8);
        else if (buttons[0].getText() == "X" && buttons[3].getText() == "X" && buttons[6].getText() == "X")
            xWins(0, 3, 6);
        else if (buttons[1].getText() == "X" && buttons[4].getText() == "X" && buttons[7].getText() == "X")
            xWins(1, 4, 7);
        else if (buttons[2].getText() == "X" && buttons[5].getText() == "X" && buttons[8].getText() == "X")
            xWins(2, 5, 8);
        else if (buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "X")
            xWins(0, 4, 8);
        else if (buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "X")
            xWins(2, 4, 6);


            //Combinationi O
        else if (buttons[0].getText() == "O" && buttons[1].getText() == "O" && buttons[2].getText() == "O")
            oWins(0, 1, 2);
        else if (buttons[3].getText() == "O" && buttons[4].getText() == "O" && buttons[5].getText() == "O")
            oWins(3, 4, 5);
        else if (buttons[6].getText() == "O" && buttons[7].getText() == "O" && buttons[8].getText() == "O")
            oWins(6, 7, 8);
        else if (buttons[0].getText() == "O" && buttons[3].getText() == "O" && buttons[6].getText() == "O")
            oWins(0, 3, 6);
        else if (buttons[1].getText() == "O" && buttons[4].getText() == "O" && buttons[7].getText() == "O")
            oWins(1, 4, 7);
        else if (buttons[2].getText() == "O" && buttons[5].getText() == "O" && buttons[8].getText() == "O")
            oWins(2, 5, 8);
        else if (buttons[0].getText() == "O" && buttons[4].getText() == "O" && buttons[8].getText() == "O")
            oWins(0, 4, 8);
        else if (buttons[2].getText() == "O" && buttons[4].getText() == "O" && buttons[6].getText() == "O")
            oWins(2, 4, 6);
        else checkDraw();

    }

    /**Controlla se tutti itasti sono occupati,
     * e in quel caso chiamare il pareggo.
     */
    private void checkDraw() {
        int x = 0;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText() != "") {
                x++;
            }
        }
        if (x == 9) draw();
    }
    //xWins
    /** Se questo metodo viene chiamato significa che il player ha vinto.
     * Richiede le tre posizioni che hanno portato alla vittoria, e
     * cambia il Background di tali posizioni in verde.
     */
    private void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        textLabel.setText("X Vince");

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        fine = true;
    }

    //oWins
    /** Se questo metodo viene chiamato significa che il bot/player2 ha vinto.
     * Richiede le tre posizioni che hanno portato alla vittoria, e
     * cambia il BackGround di tali posizioni in verde.
     * @param a prima posizione che ha portato alla vittoria
     * @param b seconda posizione che ha portato alla vittoria
     * @param c terza posizione che ha portato alla vittoria
     */
    private void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        textLabel.setText("O Vince");

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        fine = true;
    }

    //draw
    /** Se questo metodo viene chiamato significa che c'è stato un pareggio.
     * Blocca tutti i tasti dato che la partita è finita
     */
    private void draw() {
        textLabel.setText("Pareggio");
        for (int i = 0; i < 9; i++)
            buttons[i].setEnabled(false);

        fine = true;
    }

    /** Controlla che bottone è stato premuto,
     * se è un tasto di gioco, imposta il testo di quel
     * tasto in X/O in base di chi era il turno,
     * e se il prossimo turno è del bot richiama la mossa del bot.

     * Se è stato premuto il tasto nuova partita crea un nuovo frame.

     * E se viene premuto il tasto per cambiare modalitò, cambia modalita è se
     * la botmode è attivata ed è il turno del bot richiama la mossa del bot.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeMode) {
            if (botmode) {
                botmode = false;
            } else {
                botmode = true;
                if (!playerTurn&&!fine) {
                    botMove();
                }
            }
            return;
        } else if (e.getSource() == newGame) {
            this.frame.dispose();
            new Tris();
            return;
        }

        for (JButton b : buttons) {
            if (e.getSource() == b) {
                if (b.getText() == "") {
                    if (playerTurn) {
                        playerTurn = false;
                        b.setText("X");
                        b.setForeground(Color.red);
                        textLabel.setText("Turno O");
                        check();
                        if (botmode && !fine) {
                            botMove();
                        }
                    } else if (!botmode) {
                        playerTurn = true;
                        b.setText("O");
                        b.setForeground(Color.BLUE);
                        textLabel.setText("Turno X");
                    }
                    check();
                }
            }
        }
    }

    /**Se questo medoto viene chiamato significa
     * che il bot ha possibilitò di vincere, o
     * d'impedire la vittoria al player.

     * Imposta O dove il bot deve giocare
     * @param x posizione da occupare
     */
    private void botcheck(int x){
        buttons[x].setText("O");
        buttons[x].setForeground(Color.BLUE);
        check();
        playerTurn = true;
        textLabel.setText("Turno X");
    }

    /** Se il bot può vincere nelle diagonali chiama il metodo botcheck con la posizione da occupare,
     * se il bot puo vincere nelle righe o nelle colonne vince,
     * se il player puo vincere nelle righe o nelle colonne chiama il metodo botcheck con la posizione da occupare,
     * Se il player può vincere nelle chiama il metodo botcheck con la posizione da occupare per bloccarlo.
     */
    private void botMove() {
        //controlla se il bot puo vincere nelle diagonali
        if (buttons[0].getText() == "O" && buttons[4].getText() == "O" && buttons[8].getText() == "") {
            botcheck(8);
            return;
        } else if (buttons[4].getText() == "O" && buttons[8].getText() == "O" && buttons[0].getText() == "") {
            botcheck(0);
            return;
        } else if (buttons[0].getText() == "O" && buttons[8].getText() == "O" && buttons[4].getText() == "") {
            botcheck(4);
            return;
        } else if (buttons[2].getText() == "O" && buttons[4].getText() == "O" && buttons[6].getText() == "") {
            botcheck(6);
            return;
        } else if (buttons[4].getText() == "O" && buttons[6].getText() == "O" && buttons[2].getText() == "") {
            botcheck(2);
            return;
        } else if (buttons[2].getText() == "O" && buttons[6].getText() == "O" && buttons[4].getText() == "") {
            botcheck(4);
            return;
        }

        int c,i,x;
        String sign = "O";
        for(int k=0;k<2;k++) {
            x = 0;
            //controlla se il bot puo vincere nelle righe
            for (int j = 3; j <= 9; j += 3) {
                c = 0;
                for (i = x; i < j; i++) {
                    if (buttons[i].getText().equals(sign)) {
                        c++;
                    }
                }
                if (c == 2) {
                    for (i = x; i < j; i++) {
                        if (buttons[i].getText() == "") {
                            buttons[i].setText("O");
                            buttons[i].setForeground(Color.BLUE);
                            playerTurn = true;
                            textLabel.setText("Turno X");
                            check();
                            return;
                        }
                    }
                }
                x += 3;
            }
            //controlla se il bot puo vincere nelle colonne
            x = 6;
            int y = 0;
            for (int j = 0; j < 3; j++) {
                c = 0;
                for (i = y; i <= x; i += 3) {
                    if (buttons[i].getText() == sign) {
                        c++;
                    }
                }
                if (c == 2) {
                    for (i = y; i <= x; i += 3) {
                        if (buttons[i].getText() == "") {
                            buttons[i].setText("O");
                            buttons[i].setForeground(Color.BLUE);
                            playerTurn = true;
                            textLabel.setText("Turno X");
                            check();
                            return;
                        }
                    }
                }
                y++;
                x++;
            }
            sign = "X";
        }

        //controlla se il player puo vincere nelle diagonali e bloccalo
        if (buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "") {
            botcheck(8);
            return;
        } else if (buttons[4].getText() == "X" && buttons[8].getText() == "X" && buttons[0].getText() == "") {
            botcheck(0);
            return;
        } else if (buttons[0].getText() == "X" && buttons[8].getText() == "X" && buttons[4].getText() == "") {
            botcheck(4);
            return;
        } else if (buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "") {
            botcheck(6);
            return;
        } else if (buttons[4].getText() == "X" && buttons[6].getText() == "X" && buttons[2].getText() == "") {
            botcheck(2);
            return;
        } else if (buttons[2].getText() == "X" && buttons[6].getText() == "X" && buttons[4].getText() == "") {
            botcheck(4);
            return;
        }
        if(fine){return;}

        //se non si verficica nessuno dei precendenti casi il bot gioca in una posizione a caso
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

    public class newGame extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new Tris();
        }
    }

    private class escGame extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }
}