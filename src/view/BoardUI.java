package view;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import controller.GameController;
import controller.MousePressListener;
import controller.UserManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BoardUI extends JFrame {

	private JPanel contentPane;
	private ArrayList<Square>squares;
	private JLabel label;
	private GameController gc;
	private UserManager um;
	private Timer wTimer;
	private Timer bTimer;
	private int wTime;
	private int bTime;
	private int interval;
	private JLabel labelB;
	private JLabel labelW;
	
	public BoardUI(GameController gc) {
		labelW = new JLabel();
		labelW.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelB = new JLabel();
		labelB.setFont(new Font("Tahoma", Font.PLAIN, 22));
		this.gc=gc;
		setTimers();
		squares=new ArrayList<>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 721, 815);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(204, 102, 0), 3));
		panel.setBounds(42, 62, 600, 600);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(8,8));	
		label = new JLabel(gc.whoseTurn());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		label.setBounds(178, 666, 329, 45);
		contentPane.add(label);
		
		JButton btnConcieve = new JButton("Concieve");
		btnConcieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean color=gc.getGame().getIsWhitesTurn();
				gc.setWinner(!color);
			}
		});
		btnConcieve.setBounds(42, 678, 137, 29);
		contentPane.add(btnConcieve);
		
		JButton btnSaveExit = new JButton("Save & Exit");
		btnSaveExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getUm().getOngoingGames().add(gc.getGame());
				dispose();
			}
		});
		btnSaveExit.setBounds(505, 678, 137, 29);
		contentPane.add(btnSaveExit);
		
		JLabel lblWhite = new JLabel("White");
		lblWhite.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWhite.setBounds(167, 16, 77, 29);
		contentPane.add(lblWhite);
		
		labelW.setBounds(226, 16, 90, 30);
		contentPane.add(labelW);
		
		JLabel lblBlack = new JLabel("Black");
		lblBlack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBlack.setBounds(386, 15, 68, 29);
		contentPane.add(lblBlack);
		
		labelB.setBounds(438, 16, 90, 30);
		contentPane.add(labelB);
		{		
		for(int i=0;i<64;i++){
			int r=i/8;
			int c=i%8;
			squares.add(new Square());
			
			if ((r+c)%2==0){
				squares.get(i).setBackground(new Color(255, 204, 153));
				squares.get(i).setBorder(new LineBorder(new Color(255, 204, 153), 2));
				
			}		
			else{
				squares.get(i).setBackground(new Color(204, 102, 0));
				squares.get(i).setBorder(new LineBorder(new Color(204, 102, 0), 2));
			}
			panel.add(squares.get(i));
			Square sq=squares.get(i);
			sq.getPiece().setPos(i);
			squares.get(i).addMouseListener(new MousePressListener(gc, sq));	
			}
		}
		ActionListener ae1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				wTime+=interval;
				if(wTime==0)
					gc.setWinner(false);
				int mins=wTime/60;
				int secs=wTime-mins*60;
				String s=String.format("%2d:%2d",mins,secs);
				labelW.setText(s);
			}
		};
		wTimer=new Timer(1000, ae1);
		wTimer.setRepeats(true);
		wTimer.start();
		
		ActionListener ae2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bTime+=interval;
				if(bTime==0)
					gc.setWinner(true);
				int min=bTime/60;
				int sec=bTime-min*60;
				String s=String.format("%2d:%2d",min,sec);
				labelB.setText(s);
			}
		};
		bTimer=new Timer(1000, ae2);
		bTimer.setRepeats(true);	
	}
	public void switchTimer(){
		if(wTimer.isRunning()){
			wTimer.stop();
			bTimer.start();
		}else if(bTimer.isRunning()){
			bTimer.stop();
			wTimer.start();
		}
	}
	public void setTimers(){
		Boolean limited=gc.getGame().getTimeLimited();
		if(limited){
			bTime = 600;
			wTime = 600;
			interval=-1;
			labelB.setText("10:00");
			labelW.setText("10:00");
		}else if(!limited){
			bTime = 0;
			wTime = 0;
			interval=1;
			labelB.setText("0: 0");
			labelW.setText("0: 0");
		}
	}
	public void stopTimers(){
		wTimer.stop();
		bTimer.stop();
	}

	public void updateTurnText(){
		label.setText(gc.whoseTurn());
	}
	public void setWinnerText(){
		label.setText(gc.winner());
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
	}
	public UserManager getUm() {
		return um;
	}
	public void setUm(UserManager um) {
		this.um = um;
	}
}
