import javax.swing.*;
import java.awt.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    JScrollPane topPnl;
    JPanel midPnl;
    JPanel botPnl;

    JTextArea results;
    JButton rock;
    JButton paper;
    JButton scissors;
    JButton quit;
    String plrGuess;
    String comGuess;
    JLabel plrWinsL;
    JTextField plrWinsF;
    JLabel comWinsL;
    JTextField comWinsF;
    JLabel tiesL;
    JTextField tiesF;

    public RockPaperScissorsFrame() {
        setLayout(new BorderLayout());

        createTopPanel();
        createMidPanel();
        createBotPanel();

        setTitle("Rock Paper Scissors Game");
        setLocationRelativeTo(null);
        setLocation(500, 200);
        setSize(new Dimension(800, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel() {
        topPnl = new JScrollPane();

        results = new JTextArea("Results: ",15,20);
        results.setSize(200,400);

        topPnl.setViewportView(results);
        add(topPnl, BorderLayout.NORTH);
    }

    private void createMidPanel() {
        midPnl = new JPanel();

        plrWinsL = new JLabel("Player Wins: ");
        plrWinsF = new JTextField("0",1);
        midPnl.add(plrWinsL);
        midPnl.add(plrWinsF);

        comWinsL = new JLabel("Computer Wins:");
        comWinsF = new JTextField("0",1);
        midPnl.add(comWinsL);
        midPnl.add(comWinsF);

        tiesL = new JLabel("Ties: ");
        tiesF = new JTextField("0",1);
        midPnl.add(tiesL);
        midPnl.add(tiesF);

        add(midPnl, BorderLayout.CENTER);
    }
    private void createBotPanel() {
        botPnl = new JPanel();

        ImageIcon rockIcon = new ImageIcon(getClass().getResource("Rock.png"));
        ImageIcon paperIcon = new ImageIcon(getClass().getResource("Paper.png"));
        ImageIcon scissorsIcon = new ImageIcon(getClass().getResource("Scissors.png"));


        rock = new JButton("Rock!");
        rock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plrGuess = "rock";
                comGuess();
            }
        });

        paper = new JButton("Paper!");
        paper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plrGuess = "paper";
                comGuess();
            }
        });

        scissors = new JButton("Scissors!");
        scissors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plrGuess = "scissors";
                comGuess();
            }
        });
        botPnl.add(scissors);

        quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        btnPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rock, Paper, Scissors... Shoot!"));
        btnPanel.add(rock);
        btnPanel.add(paper);
        btnPanel.add(scissors);
        btnPanel.add(quit);
        botPnl.add(btnPanel);
        rock.setIcon(rockIcon);
        paper.setIcon(paperIcon);
        scissors.setIcon(scissorsIcon);

        add(botPnl, BorderLayout.SOUTH);
    }

    private void comGuess() {
        String[] choices = {"rock", "paper", "scissors"};
        int rand = new Random().nextInt(choices.length);
        String comGuess = choices[rand];

        String winner;

        if (comGuess.equals(plrGuess)) {
            winner = "tie";
        } else if (comGuess.equals("rock") && plrGuess.equals("scissors") ||
                comGuess.equals("paper") && plrGuess.equals("rock") ||
                comGuess.equals("scissors") && plrGuess.equals("paper")) {
            winner = "computer";
        } else {
            winner = "player";
        }

        updateResults(winner, comGuess);
    }

    private void updateResults(String outcome, String comGuess) {
        int plrWins = Integer.parseInt(plrWinsF.getText());
        int comWins = Integer.parseInt(comWinsF.getText());
        int ties = Integer.parseInt(tiesF.getText());

        if (outcome.equals("player")) {
            plrWins++;
            results.append("\nYou win! " + plrGuess + " beats " + comGuess + "!");
        } else if (outcome.equals("computer")) {
            comWins++;
            results.append("\nYou lose! " + comGuess + " beats " + plrGuess + "!");
        } else {
            ties++;
            results.append("\nYou Tied! You both chose " + plrGuess + ".");
        }

        plrWinsF.setText(Integer.toString(plrWins));
        comWinsF.setText(Integer.toString(comWins));
        tiesF.setText(Integer.toString(ties));
    }

    public static void main(String[] args) {
    }

}
