import javax.swing.JFrame;

import view.StartPanel;



public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(400, 100);
		window.setTitle("Start Screen");
        var start = new StartPanel(window);
		start.init();
		window.pack();
		window.setVisible(true);
	}
	
}