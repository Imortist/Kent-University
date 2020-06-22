import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class Countries extends JFrame {

    private JList<String> countriesList;
    private HashMap<String, String> countriesAndCapitals;
    private JLabel capital;

    public Countries(){
        super("Countries n Capitals");
        setLayout(new GridLayout(2,1));

        countriesAndCapitals = new HashMap<>();
        countriesAndCapitals.put("Lithuania", "Vilnius");
        countriesAndCapitals.put("Yemen", "San'a");
        countriesAndCapitals.put("Ukraine", "Kiev");
        countriesAndCapitals.put("Slovakia", "Bratislava");
        countriesAndCapitals.put("Qatar", "Doha");
        countriesAndCapitals.put("Bosnia", "Sarajevo");
        countriesAndCapitals.put("Brunei", "Bandar Seri Begawan");

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(String country : countriesAndCapitals.keySet()) listModel.addElement(country);

        countriesList = new JList<>(listModel);
        countriesList.setVisible(true);
        countriesList.setSelectionMode(1);
        countriesList.addListSelectionListener(listSelectionEvent -> capital.setText(countriesAndCapitals.get(countriesList.getSelectedValue())));
        countriesList.setAlignmentX(Component.CENTER_ALIGNMENT);

        capital = new JLabel();
        capital.setText("Select a coutnry.");


        add(new JScrollPane(countriesList));
        add(capital);


        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

