import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI project for Assignment 3
 * @date: 03/04/20
 * @author Aleksej Bratkovskij (ab2323)
 */
public class Gui extends JFrame {

    //local vars
    private JPanel gridPanel, controlsPanel, difficultyPanel;
    private JPanel[] squares = new JPanel[10];
    private JButton play, exit, easy, intermediate, difficult;
    private JLabel looser, winner;
    private Dimension spacing = new Dimension(10, 10);
    private int points, difficulty;
    private boolean impossible = false;
    private String title = "Chasing Bombs ab2323   points: ";
    //Mouse Adapter is used in order to avoid use of unnecesary methods.
    private MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //check if clicked object is JPanel on the grid.
                if (mouseEvent.getSource() instanceof JPanel) {
                    JPanel target = (JPanel) mouseEvent.getSource();
                    winner.setVisible(false);
                    //update information and add points.
                    if (target != squares[0] && !impossible) {
                        positiveClickOnTile(target);
                        //loose condition
                    } else {
                        looser.setText("You loose! You got " + points + " points.");
                        looser.setVisible(true);
                    }
                    //win condition
                    if (points == difficulty) {
                        winner.setText("You won the game!");
                        winner.setVisible(true);
                    }
                }
                //JButton actions:
                if (mouseEvent.getSource() == play) {
                    newGame();
                    impossible = false;
                }

                if (mouseEvent.getSource() == exit) {
                    System.exit(0);
                }

                if (mouseEvent.getSource() == easy) {
                    difficulty = 5;
                    impossible = false;
                }
                if (mouseEvent.getSource() == intermediate) {
                    difficulty = 7;
                    impossible = false;
                }
                if (mouseEvent.getSource() == difficult) {
                    impossible = true;
                }

            }
        };

    /**
     * Main class constructor
     */
    public Gui(){
        super("Chasing Bombs ab2323   points: 0");

        setLayout(new GridLayout(1,3));
        //variable initialisation
        points = 0;
        difficulty = 5;

        //create and set-up all objects needed
        createAll();

        //add all objects to their panels
        addAll();

        //add necessary panels to the frame;
        add(gridPanel);
        add(controlsPanel);
        add(difficultyPanel);

        //final set up of functionality and layout management
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Panel and button initialisation and set-up
     */
    private void createAll(){
        setUpPanels();
        setUpButtons();
        setUpLabels();
    }

    /**
     * sets up all label objects
     */
    private void setUpLabels() {
        looser = new JLabel();
        looser.setVisible(false);
        looser.setAlignmentX(Component.CENTER_ALIGNMENT);
        looser.setForeground(Color.white);

        winner = new JLabel();
        winner.setVisible(false);
        winner.setAlignmentX(Component.CENTER_ALIGNMENT);
        winner.setForeground(Color.white);
    }

    /**
     * sets up all JButton objects
     */
    private void setUpButtons() {

        play = new JButton("Play the Game");
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        play.addMouseListener(ml);

        exit = new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addMouseListener(ml);

        easy = new JButton("Easy");
        easy.setAlignmentX(Component.CENTER_ALIGNMENT);
        easy.addMouseListener(ml);

        intermediate = new JButton("Intermediate");
        intermediate.setAlignmentX(Component.CENTER_ALIGNMENT);
        intermediate.addMouseListener(ml);

        difficult = new JButton("Difficult");
        difficult.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficult.addMouseListener(ml);
    }

    /**
     * sets up all JPanels
     */
    private void setUpPanels() {
        //Game grid set-up (left)
        gridPanel = new JPanel(new GridLayout(5,2, 1, 1));
        //add JPanels to the grid
        for(int i = 0; i < 10; i++) {
            squares[i] = new JPanel();
            gridPanel.add(squares[i]);
            squares[i].setBackground(Color.red);
            squares[i].addMouseListener(ml);
        }

        //Controls section (middle) set-ip
        controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel,BoxLayout.Y_AXIS));
        controlsPanel.setBackground(Color.blue);

        //Difficulty section setup (right)
        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
        difficultyPanel.setBackground(Color.green);
    }

    /**
     * Add objects to panels.
     */
    private void addAll(){
        controlsPanel.add(Box.createRigidArea(spacing));
        controlsPanel.add(play);
        controlsPanel.add(Box.createRigidArea(spacing));
        controlsPanel.add(exit);
        controlsPanel.add(Box.createRigidArea(spacing));
        controlsPanel.add(looser);
        controlsPanel.add(winner);

        difficultyPanel.add(Box.createRigidArea(spacing));
        difficultyPanel.add(easy);
        difficultyPanel.add(Box.createRigidArea(spacing));
        difficultyPanel.add(intermediate);
        difficultyPanel.add(Box.createRigidArea(spacing));
        difficultyPanel.add(difficult);
        difficultyPanel.add(Box.createRigidArea(spacing));
    }


    /**
     * change look and feel of tiles when user clicks on no-bomb tile.
     * @param target clicked JPanel
     */
    private void positiveClickOnTile(JPanel target) {
        points++;
        target.setBackground(Color.CYAN);
        title = "Chasing Bombs ab2323   points: " + points;
        super.setTitle(title);
    }

    /**
     * Sets up JPanels for the new game, resets points and messages.
     */
    private void newGame() {
        for(JPanel jp : squares) jp.setBackground(Color.red);
        points = 0;
        looser.setVisible(false);
        title = "Chasing Bombs ab2323   points: " + points;
        super.setTitle(title);
    }
}