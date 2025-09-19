package nl.rug.oop.flaps.aircraft_editor.view.panels;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
@Getter
public class DataPanel {

    private final static int PANEL_WIDTH = 200;
    private final static int PANEL_HEIGHT = 650;
    private final JPanel Datapanel = new JPanel();

    /**
     * Creates the Datapanelview
     * @param cargoPanel The cargoPanel
     * @param fuelPanel The Fuelpanel
     * @param informationPanel The informationpanel
     */
    public DataPanel(JPanel cargoPanel, JPanel fuelPanel, JPanel informationPanel){

        Datapanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        Datapanel.setLayout(new BorderLayout(4,4));
        Datapanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Data Panel: "));

        //Following lines add the dataPanel components to the panel
        Datapanel.add(cargoPanel, BorderLayout.SOUTH);
        Datapanel.add(fuelPanel, BorderLayout.NORTH);
        Datapanel.add(informationPanel, BorderLayout.CENTER);
    }

}
