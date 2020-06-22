import javax.swing.*;
import java.awt.*;


/**
 * @date 19/03/2020
 * @author Aleksej Bratkovskij (ab2323)
 *
 * Workshop for Week 19-20 please mark them both, I've missed one lab last week.
 */


public class FOOP_JFrame extends JFrame {

    //local variables
    private JPanel header, name, pizza, buttons, pTotal;
    private JCheckBox cbSizeSmall, cbSizeMedium, cbSizeLarge, cbTopping1, cbTopping2, cbTopping3;
    private JLabel lDesc, lPromptName, lCheckSizeOfPizza, lCheckToppings, lTotal;
    private JTextField tfNameInput = new JTextField();
    private Button bCalculate;
    private Color bg = Color.CYAN;

    /**
    Window constructor.
     */
    public FOOP_JFrame(){
        super("Pizzeria");

        //JFrame setup
        setLayout(new GridLayout(5,1));
        setBounds(350,325,600,125);
        setBackground(bg);

        //create all objects
        createAll();

        //add all objects to the frame
        addAll();


        add(header,BorderLayout.CENTER);
        add(name, BorderLayout.CENTER);
        add(pizza, BorderLayout.CENTER);
        add(buttons, BorderLayout.CENTER);
        add(pTotal, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);


    }

    /**
     * Sets up all objects
     */
    private void createAll(){
        //panel setup
        header = new JPanel();
        header.setBackground(bg);
        name = new JPanel(new FlowLayout());
        name.setBackground(bg);
        pizza = new JPanel(new GridLayout(2,1));
        pizza.setBackground(bg);
        buttons = new JPanel();
        buttons.setBackground(bg);
        pTotal = new JPanel();
        pTotal.setBackground(bg);

        //checkbox setup
        cbSizeSmall = new JCheckBox("Small size", false);
        cbSizeSmall.setBackground(bg);
        cbSizeMedium = new JCheckBox("Medium size", false);
        cbSizeMedium.setBackground(bg);
        cbSizeLarge = new JCheckBox("Large size", false);
        cbSizeLarge.setBackground(bg);
        cbTopping1 = new JCheckBox("Ham", false);
        cbTopping1.setBackground(bg);
        cbTopping2 = new JCheckBox("Mushrooms", false);
        cbTopping2.setBackground(bg);
        cbTopping3 = new JCheckBox("Pineapple", false);
        cbTopping3.setBackground(bg);

        //label setup
        lDesc = new JLabel("We do only Pineapple pizzas! Even if you order something else.");
        lPromptName = new JLabel("Please enter your name: ");
        lCheckSizeOfPizza = new JLabel("What size pizza are we making?");
        lCheckToppings = new JLabel("What extra toppings do you want on your pizza?");
        lTotal = new JLabel();
        lTotal.setPreferredSize(new Dimension(300,20));
        lTotal.setVisible(false);

        //button setup
        bCalculate = new Button("Calculate Cost of Pizza");
        bCalculate.setPreferredSize(new Dimension(150,40));

        //button action with LAMBDA expression
        bCalculate.addActionListener(source ->{
            lTotal.setText("Total: £" + calculation());
            lTotal.setVisible(true);
                });
        //name text field setup
        tfNameInput.setPreferredSize(new Dimension(100,20));


    }

    /**
     * adds all objects to the frame
     */
    private void addAll(){
        header.add(lDesc);

        name.add(lPromptName);
        name.add(tfNameInput);

        pizza.add(lCheckSizeOfPizza);
        pizza.add(cbSizeSmall);
        pizza.add(cbSizeMedium);
        pizza.add(cbSizeLarge);
        pizza.add(lCheckToppings);
        pizza.add(cbTopping1);
        pizza.add(cbTopping2);
        pizza.add(cbTopping3);

        buttons.add(bCalculate);
        buttons.add(lTotal);

        pTotal.add(lTotal);

    }

    /**
     * Calculates total cost of the order based on checked boxes
     * @return total value of checked boxes
     */
    private float calculation(){
        float total = 0;
        if(cbSizeSmall.isSelected()) total+= 7.5;
        if(cbSizeMedium.isSelected()) total+= 9.75;
        if(cbSizeLarge.isSelected()) total+= 11.50;
        if(cbTopping1.isSelected()) total+= 0.5;
        if(cbTopping2.isSelected()) total += 0.6;
        if(cbTopping3.isSelected()) total += 0.75;
        return total;
    }

}

