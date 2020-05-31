import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class SnakeGame {
	
	public static JLabel lbl_yilan;
	public static JLabel lbl_elma;
	
	static int score = 0;
	static int x = 0;
	static int y = 0;
	static int x2 = 0;
	static int y2 = 0;
	static JLabel skor;
	AudioInputStream stream;
	Clip clip;
	
	public static void up() {
		y-=5;
		lbl_yilan.setBounds(x,y,50,50);	
		kazan();
	}
	public static void down() {
		y+=5;
		lbl_yilan.setBounds(x,y,50,50);	
		kazan();
	}
	public static void right() {
		x+=5;
		lbl_yilan.setBounds(x,y,50,50);	
		kazan();
	}
	public static void left() {
		x-=5;
		lbl_yilan.setBounds(x,y,50,50);	
		kazan();
	}
	
	public static void kazan() {
		
		if(lbl_elma.getLocation().equals(lbl_yilan.getLocation())) {

			score++;
			skor.setText("SKOR: " + String.valueOf(score));
			Random random = new Random();
			
			x2 = 5 * random.nextInt(100); 
			y2 = 5 * random.nextInt(100); 
			lbl_elma.setBounds(x2,y2,50,50);
		}	
	}
	
	Random random = new Random();
	private JFrame frmSnakeGame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnakeGame window = new SnakeGame();
					window.frmSnakeGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SnakeGame() throws LineUnavailableException, IOException {
		initialize();
		
	}

	private void initialize() throws LineUnavailableException, IOException {
		
		
		try {
			stream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Sefa\\eclipse-workspace\\SnakeGame\\media\\oyunmusic.wav"));
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		frmSnakeGame = new JFrame();
		frmSnakeGame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sefa\\eclipse-workspace\\SnakeGame\\media\\yilan1.png"));
		frmSnakeGame.getContentPane().setBackground(new Color(30, 144, 255));
		frmSnakeGame.setTitle("Snake Game");
		frmSnakeGame.setVisible(true);
		frmSnakeGame.setBounds(100, 100, 700, 700);
		frmSnakeGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSnakeGame.getContentPane().setLayout(null);
		frmSnakeGame.setFocusable(true);
		
		
		ImageIcon image = new ImageIcon("C:\\Users\\Sefa\\eclipse-workspace\\SnakeGame\\media\\yilan1.png");
		lbl_yilan = new JLabel(image);
		lbl_yilan.setBounds(x, y, 50, 50);
		frmSnakeGame.getContentPane().add(lbl_yilan);
		
		
		ImageIcon image2 = new ImageIcon("C:\\Users\\Sefa\\eclipse-workspace\\SnakeGame\\media\\elma1.png");
		lbl_elma = new JLabel(image2);
		lbl_elma.setVisible(false);
		x2 = 5 * random.nextInt(100);  
		y2 = 5 * random.nextInt(100); // RANDOM SORUNSUZ

		lbl_elma.setBounds(x2,y2,50,50);

		lbl_elma.setVisible(true);
		System.out.println("X2 Deðeri: " + x2);
		System.out.println("Y2 Deðeri: " + y2);
		frmSnakeGame.getContentPane().add(lbl_elma);
		
		skor = new JLabel("SKOR: ");
		skor.setFont(new Font("Tahoma", Font.BOLD, 20));
		skor.setBounds(566, 625, 108, 25);
		skor.setText("SKOR: " + String.valueOf(score));
		frmSnakeGame.getContentPane().add(skor);
		
		JLabel lblNewLabel = new JLabel("Press M for Mute!");
		lblNewLabel.setBounds(10, 634, 118, 14);
		frmSnakeGame.getContentPane().add(lblNewLabel);

		//Adding key listener ************
		frmSnakeGame.addKeyListener(new KeyListener() {

			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				switch(keycode) {
					case KeyEvent.VK_UP:
						up();
						
						break;
					case KeyEvent.VK_DOWN:
						down();

						break;
					case KeyEvent.VK_LEFT:
						left();

						break;
					case KeyEvent.VK_RIGHT:
						right();

						break;
						
					case KeyEvent.VK_M:
						clip.stop();
						break;
					}
				
				}
			});	
		}
	}


