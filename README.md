# Documentation

Documentation for Midterm Lab Quiz#2.

## Groupmates

Colston Bod-oy - Coding  
Jean Delmari Bernal - Documentation

## Output

![Screenshot_1](https://user-images.githubusercontent.com/75562733/158065155-1dd50ff3-b7ef-4ef8-b0af-f83708e0a5af.png)

* Contains 1 main class named SciCard and 5 additional classes (SciLabelPanel, SciRadioPanel, SciCheckPanel, SciTextPanel, SciButton).
* Assets: cyberspace.ttf, emblem.png.
```
Whole project with assets: https://github.com/ColstonBod-oy/sci-card
```

### SciCard.java

![Screenshot_2](https://user-images.githubusercontent.com/75562733/158066263-fe8dc116-1c10-49a2-b246-1bc2243980d5.png)

* The main class of the program that instantiates SciLabelPanel, SciRadioPanel, SciCheckPanel, SciTextPanel, and SciButton.
* Stores the score array that would be passed as an argument when creating quiz item instances.
* Stores the texts for the multiple choice questions as a 2D array named choices.
* Has a JPanel named cardPanel that uses a CardLayout and adds all the quiz items.
* Has a JPanel named mainPanel that uses a GridBagLayout to add the emblem and cardPanel.
```
Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
```
Allows image to automatically adjust to the label's dimensions using a Buffered Image object. 

### SciLabelPanel.java

![Screenshot_3](https://user-images.githubusercontent.com/75562733/158067111-063d2b63-3191-4518-acd8-1a5727e8da21.png)
![Screenshot_4](https://user-images.githubusercontent.com/75562733/158067124-831a5195-b493-4124-a5b2-91b6852f0a66.png)
![Screenshot_5](https://user-images.githubusercontent.com/75562733/158067128-665a3d13-990c-4ad9-8e4f-3424b7cffee1.png)

* Extends the JPanel class and implements ActionListener.
* Instantiates custom JTextPanes along with their own next button.
* Uses a GridBagLayout and adds the text and next button.
* Displays the final score when it was the last card and the next button was clicked. 
```
int selected = JOptionPane.showConfirmDialog(text, "Score: " + finalScore + "/" + 
      (cardPanel.getComponentCount() - 2) + "\nWould you like to try again?", "Message", 
      JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
if (selected == 0) {
   score[0] = 0;
   cardLayout.first(cardPanel);
}
            
else {
   System.exit(0);
}
```
Displays a confirm dialog and asks the user if they wanted to retake the quiz and if the yes button was clicked, the passed score array was reset to 0 and flips to the first card of the cardPanel, otherwise, the program exits. 

### SciRadioPanel.java

![Screenshot_6](https://user-images.githubusercontent.com/75562733/158068273-c5796eb2-3a12-4fbb-b7f4-f76447f55c3f.png)
![Screenshot_7](https://user-images.githubusercontent.com/75562733/158068281-7cf9e549-2797-48e8-a56c-468835124bff.png)
![Screenshot_8](https://user-images.githubusercontent.com/75562733/158068288-ee0bc7b4-39d0-4361-8d1b-9a11c961538a.png)
![Screenshot_9](https://user-images.githubusercontent.com/75562733/158068294-904d6934-6d9d-4d04-be1e-ff81a4fa904c.png)
![Screenshot_10](https://user-images.githubusercontent.com/75562733/158068298-774980a9-6621-4023-ac61-adf6d7dd34fb.png)

* Extends the JPanel class and implements ActionListener.
* Instantiates custom JRadioButtons along with their own next button.
* Uses Graphics2D to create custom JRadioButtons with custom color.
* Uses a GridBagLayout and adds the question, container panel for the radio buttons, and next button.
* Displays an error message dialog when none of the radio buttons was selected. 
```
if (i % 2 == 0) {
   leftPanelY.add(Box.createVerticalStrut(10));
   leftPanelY.add(this.choices[i]);
}
            
else {
   rightPanelY.add(Box.createVerticalStrut(10));
   rightPanelY.add(this.choices[i]);
}
```
Adds vertical spacing and separates the radio buttons into 2 sides which would then be added to the container panel. 

### SciCheckPanel.java

![Screenshot_11](https://user-images.githubusercontent.com/75562733/158072867-69403646-441f-44f7-adf3-0a34baf91dc8.png)
![Screenshot_12](https://user-images.githubusercontent.com/75562733/158072872-60b1ad2a-578c-46d0-a530-6fd1b5c67942.png)
![Screenshot_13](https://user-images.githubusercontent.com/75562733/158072876-6c4649f6-771d-4ceb-98e2-85014d35990e.png)
![Screenshot_14](https://user-images.githubusercontent.com/75562733/158072882-2711dc29-4f37-4117-bb0c-3232ecc7edad.png)
![Screenshot_15](https://user-images.githubusercontent.com/75562733/158072885-290fbbd9-5033-49c9-82ea-204f053565b9.png)

* Extends the JPanel class and implements ActionListener.
* Instantiates custom JCheckBoxes along with their own next button.
* Uses Graphics2D to create custom JCheckBoxes with custom color.
* Uses a GridBagLayout and adds the question, container panel for the check boxes, and next button.
* Displays an error message dialog when none of the check boxes was selected.
* Same layout as SciRadioPanel.

### SciTextPanel.java

![Screenshot_16](https://user-images.githubusercontent.com/75562733/158073236-c1b66702-3d9f-44f6-96c0-718c38e4dccb.png)
![Screenshot_17](https://user-images.githubusercontent.com/75562733/158073243-f462e4e1-d3eb-4727-b9dc-a31a9b51322c.png)
![Screenshot_18](https://user-images.githubusercontent.com/75562733/158073246-46d4e79e-7b1c-4399-a4f0-3be0b420ddff.png)
![Screenshot_19](https://user-images.githubusercontent.com/75562733/158073250-4fee105a-ad84-46ef-9a19-ebdbdd6f9cf7.png)
![Screenshot_20](https://user-images.githubusercontent.com/75562733/158073899-240b33f8-7916-414b-be59-80edbf8b38e8.png)
![Screenshot_21](https://user-images.githubusercontent.com/75562733/158073905-20e71537-b57a-4a08-b373-fce89497feca.png)

* Extends the JPanel class and implements ActionListener.
* Instantiates custom JTextFields along with their own next button.
* Creates a hint text inside the text field using the passed string argument. 
* Uses Graphics2D to create custom JTextFields with custom color.
* Uses a GridBagLayout and adds the question, text field, and next button.
* Displays an error message dialog when the text field was left with just spaces or was unanswered.
```
class RoundedBorder extends AbstractBorder {
   @Override
   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      super.paintBorder(c, g, x, y, width, height);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(12));
      g2d.setColor(Color.decode("#d1f7ff"));
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
   }
}
```
Creates the rounded corners of the text fields.

### SciButton.java

![Screenshot_23](https://user-images.githubusercontent.com/75562733/158071426-41434aa6-3a5f-43d6-ad90-2f0f7a312a43.png)
![Screenshot_24](https://user-images.githubusercontent.com/75562733/158071431-14a91ff1-149f-4c12-9a3f-98622c8f735e.png)

* Extends the JButton class.
* Instantiates custom JButtons.
```
class TriangularBorder extends AbstractBorder {
   @Override
   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      super.paintBorder(c, g, x, y, width, height);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(12));
      g2d.setColor(Color.decode("#d1f7ff"));
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.drawRoundRect(x, y, width - 1, height - 1, 50, 50);
   }
}
```
Creates triangular corners instead of rounded ones.

### End of Documentation
