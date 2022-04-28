package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Images.ImageStore;

public class StartPanel {

    private JFrame window;
    
    public StartPanel(JFrame window) {
        this.window = window;
        window.setLocation(400, 100);
		window.setTitle("Start Screen");
        window.setPreferredSize(new Dimension(500, 500));
    }

    public void init() {
        Container cp = window.getContentPane();

        JPanel startImage = new JPanel();

        JLabel kirbyPhoto = new JLabel(new ImageIcon(ImageStore.kirby));

        startImage.add(kirbyPhoto);
        cp.add(startImage, BorderLayout.CENTER);

        Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
                    window.getContentPane().removeAll();
					var menu = new MenuPanel(window);
                    menu.init();
                    window.pack();
                    window.setVisible(true);
                    timer.cancel();
                    timer.purge();
				}
			}, 7000);
    }
}