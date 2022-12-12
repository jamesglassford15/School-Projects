package edu.foothill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ControlsFrame extends JFrame
{
   private JLabel lblName = new JLabel("Choose a poster from the dropdown.");
   private JPanel panelMain = new JPanel();
   private JPanel panelCenter = new JPanel();
   private String[] posterNames =
   { "Pineapple Express", "Indiana Jones", "The Dark Knight", "The Mummy", "Don't Mess With the Zohan", "The Strangers",
         "2001: A Space Odyssey", "The Matrix", "Wreck It Ralph", "Tron" };
   private JComboBox<String> cmboPosters = new JComboBox<>(posterNames);
   private ImageDisplay imageDisplay = new ImageDisplay();
   private JLabel widthLabel = new JLabel("width:");
   private JTextField widthTextArea = new JTextField(5);
   private JLabel heightLabel = new JLabel("height:");
   private JTextField heightTextArea = new JTextField(5);
   private JButton setSizeButton = new JButton("Set size");
   private int posterIndex = 0;
   private String[] posterImagePaths =
   { "2503476233_debd858e3a.jpg", "2503476489_fec9f8a6c5.jpg", "2504307320_cdbccb0a99.jpg", "2504307876_ecfbfdd38a.jpg",
         "2504308276_609790f8d4.jpg", "2504342800_833bf41267.jpg", "5579112932_6a462df364_b.jpg",
         "17072638696_f871731849_b.jpg", "RBTI.jpg", "4859886671_cef0598bf3_b.jpg" };
   int xVal = 600;
   int yVal = 900;

   class itemListener implements ItemListener
   {

      public void itemStateChanged(ItemEvent e)
      {
         for (int i = 0; i < posterNames.length; i++)
         {

            if (e.getItem() == (posterNames[i]))
            {
               posterIndex = i;
               imageDisplay.setImagePath(posterImagePaths[posterIndex]);
            }
         }
      }

   }

   class setSizeListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         imageDisplay.changeSize(Integer.parseInt(widthTextArea.getText()), Integer.parseInt(heightTextArea.getText()));
      }

   }

   public ControlsFrame(String title)
   {
      super(title);

      FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);
      setLayout(layout);
      add(panelMain);
      panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.X_AXIS));
      panelMain.add(lblName);
      add(panelCenter);
      panelCenter.setBorder(BorderFactory.createTitledBorder("Movie poster"));
      panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));
      panelCenter.add(cmboPosters);
      panelCenter.add(widthLabel);
      panelCenter.add(widthTextArea);
      panelCenter.add(heightLabel);
      panelCenter.add(heightTextArea);
      panelCenter.add(setSizeButton);
      imageDisplay.run();
      setSizeListener posterSelection = new setSizeListener();
      setSizeButton.addActionListener(posterSelection);
      cmboPosters.addItemListener(new itemListener());

   }
}
