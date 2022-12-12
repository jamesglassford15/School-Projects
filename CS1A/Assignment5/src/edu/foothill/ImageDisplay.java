package edu.foothill;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageDisplay extends JPanel
{
   private JFrame jFrame;
   public String imagePath = "2503476233_debd858e3a.jpg";

   private int x = 600;
   private int y = 900;

   /**
    * @return the imagePath
    */

   public String getImagePath()
   {
      return imagePath;
   }

   /**
    * @param imagePath
    */
   public void setImagePath(String imagePath)
   {
      this.removeAll();
      this.imagePath = imagePath;
      this.repaint();
      changeSize(x + 1, y + 1);
      changeSize(x - 1, y - 1);
   }

   public void paint(Graphics g)
   {
      Toolkit t = Toolkit.getDefaultToolkit();
      Image i = t.getImage(imagePath);
      g.drawImage(i, 0, 0, this);
   }

   public void changeSize(int x, int y)
   {
      jFrame.setSize(x, y);
      this.x = x;
      this.y = y;
   }

   public void run()
   {
      jFrame = new JFrame();
      jFrame.add(this);
      jFrame.setSize(600, 900);
      // jFrame.setLayout(null);
      jFrame.setVisible(true);
      // Change from the default loading.gif to display a movie poster
      setImagePath("2503476233_debd858e3a.jpg");
   }

}
