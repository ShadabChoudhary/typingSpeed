import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;


public class Typing extends JFrame implements KeyListener, ActionListener{
	
	String passage="";
	String typedChar="";//stores the character we are typing
	String message="";

	int typed=0;//stores the number of char which we are typing
	int count=0;
	int WPM;
	int wrongTyped;
	int DELAY = 100;
	
	double startTime;//stores the time count down 
	double endTime;// end time
	double elapsed;
	double seconds;
	
	JLabel label = new JLabel();
	JButton btn = new JButton();
	
	Random r = new Random();
	
	Timer timer;

	boolean running = true;

	
	Typing(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,350);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null); 
		
		label.setText("Click the button to start");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("san serif",Font.BOLD,30));
		label.setForeground(Color.BLACK);
		
		btn.setText("New");
		btn.setFont(new Font("san serif",Font.BOLD,30));
		btn.setForeground(Color.BLACK);
		btn.setVisible(true);
		btn.addActionListener(this);
		btn.setFocusable(false);
		
		this.add(label,BorderLayout.CENTER);
		this.add(btn, BorderLayout.SOUTH);
		this.getContentPane().setBackground(Color.WHITE);
		this.setResizable(false);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.revalidate(); 
		this.setVisible(true);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			this.remove(label);
			btn.setVisible(false);
			passage = randomPassage();
			
			timer = new Timer(DELAY, this);
			timer.start();
			
			running= true;
			System.out.println(running);
			
			typedChar = "";
			message = "";
			
			typed=0;
			count=0;
			
			if(running) {
				repaint();
			}
			if(!running) {
				repaint();
			}
		}
	}
	
	public String randomPassage() {
		ArrayList<String> passageArray = new ArrayList<>(Arrays.asList(
				"Many touch typists also use keyboard shortcuts or hotkeys when typing on a computer. This allows them to edit their document without having to take their hands off the keyboard to use a mouse. An example of a keyboard shortcut is pressing the Ctrl key plus the S key to save a",
				"A virtual assistant (typically abbreviated to VA) is generally self-employed and provides professional administrative, technical, or creative assistance to clients remotely from a home office. Many touch typists also use keyboard shortcuts or hotkeys when typing on a computer",
				"Frank Edward McGurrin, a court stenographer from Salt Lake City, Utah who taught typing classes, reportedly invented touch typing in 1888. On a standard keyboard for English speakers the home row keys are: \"ASDF\" for the left hand and \"JKL;\" for the right hand. The keyboar",
				"Income before securities transactions was up 10.8 percent from $13.49 million in 1982 to $14.95 million in 1983. Earnings per share (adjusted for a 10.5 percent stock dividend distributed on August 26) advanced 10 percent to $2.39 in 1983 from $2.17 in 1982. Earnings may rise ",
				"Historically, the fundamental role of pharmacists as a healthcare practitioner was to check and distribute drugs to doctors for medication that had been prescribed to patients. In more modern times, pharmacists advise patients and health care providers on the selection, dosage",
				"Proofreader applicants are tested primarily on their spelling, speed, and skill in finding errors in the sample text. Toward that end, they may be given a list of ten or twenty classically difficult words and a proofreading test, both tightly timed. The proofreading test will o",
				"In one study of average computer users, the average rate for transcription was 33 words per minute, and 19 words per minute for composition. In the same study, when the group was divided into \"fast\", \"moderate\" and \"slow\" groups, the average speeds were 40 wpm, 35 wpm, an",
				"A teacher's professional duties may extend beyond formal teaching. Outside of the classroom teachers may accompany students on field trips, supervise study halls, help with the organization of school functions, and serve as supervisors for extracurricular activities. In some e",
				"Web designers are expected to have an awareness of usability and if their role involves creating mark up then they are also expected to be up to date with web accessibility guidelines. The different areas of web design include web graphic design; interface design; authoring, i",
				"A data entry clerk is a member of staff employed to enter or update data into a computer system. Data is often entered into a computer from paper documents using a keyboard. The keyboards used can often have special keys and multiple colors to help in the task and speed up th"
				));
	
		int pass = r.nextInt(10);
		passage =  passageArray.get(pass).substring(0,250);
		return(passage);
	}
	
	@Override
	public void paint(Graphics g) { 
		super.paint(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.setFont(new Font("Consolas", Font.BOLD,25));
		
		if(running) {
			if(passage.length()>1) {
				g.drawString(passage.substring(0,50), g.getFont().getSize(), g.getFont().getSize()*3);
				g.drawString(passage.substring(50,100), g.getFont().getSize(), g.getFont().getSize()*5);
				g.drawString(passage.substring(100,150), g.getFont().getSize(), g.getFont().getSize()*7);
				g.drawString(passage.substring(150,200), g.getFont().getSize(), g.getFont().getSize()*9);
				g.drawString(passage.substring(200,passage.length()), g.getFont().getSize(), g.getFont().getSize()*11);
			}
			
			//printing the typed character
			g.setColor(Color.GREEN);
//			
			if(typedChar.length() > 0) {
				if(typed>0 && typed<=50) {
					g.drawString(typedChar.substring(0,typed), g.getFont().getSize(), g.getFont().getSize()*3);
				}else {
					g.drawString(typedChar.substring(0,50), g.getFont().getSize(), g.getFont().getSize()*3);
				}
			}
			
			if(typedChar.length()>50) {
				if(typed>50 && typed <=100) {
					g.drawString(typedChar.substring(50, typed), g.getFont().getSize(), g.getFont().getSize()*5);
				}else {
					g.drawString(typedChar.substring(50, 100), g.getFont().getSize(), g.getFont().getSize()*5);
				}
			}
			
			if(typedChar.length()>100) {
				if(typed<150) {
					g.drawString(typedChar.substring(100, typed), g.getFont().getSize(), g.getFont().getSize()*7);
				}
				else {
					g.drawString(typedChar.substring(100, 150), g.getFont().getSize(), g.getFont().getSize()*7);
				}
			}
			
			if(typedChar.length()>150) {
				if(typed<200) {
					g.drawString(typedChar.substring(150, typed), g.getFont().getSize(), g.getFont().getSize()*9);
				}else {
					g.drawString(typedChar.substring(150, 200), g.getFont().getSize(), g.getFont().getSize()*9);
				}
			}
			
			if(typedChar.length()>200) {
				if(typed<250) {
					g.drawString(typedChar.substring(200, typed), g.getFont().getSize(), g.getFont().getSize()*11);
				}else {
					g.drawString(typedChar.substring(200, 250), g.getFont().getSize(), g.getFont().getSize()*11);
				}
			}
		}
		
		if(!running) {
			System.out.println(running);
			if(WPM <=40) {
				message="You are an Average Typist";
			
			}else if(WPM>40 && WPM<=60 ) {
				message="You are Good Typist";
			
			}else if(WPM>60 && WPM <=80) {
				message="You are an Excellent Typist";
			
			}else {
				message="You are a PRO Typist";
			}
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.setColor(Color.black);
			g.drawString("Typing test completed",(800-metrics.stringWidth("Typing test completed"))/2, g.getFont().getSize()*3);
			
			g.setColor(Color.blue);
			g.drawString("Typing Speed: "+WPM+" Words Per Minute",(800-metrics.stringWidth("Typing Speed: "+WPM+" Words Per Minute"))/2, g.getFont().getSize()*5);
			
			g.setColor(Color.red);
			g.drawString("Wrong Typed Characters: "+wrongTyped,(800-metrics.stringWidth("Wrong Typed Characters: "+wrongTyped))/2, g.getFont().getSize()*7);
			
			g.setColor(Color.black);
			g.drawString(message, (800-metrics.stringWidth(message))/2, g.getFont().getSize()*9);
			btn.setVisible(true);
			timer.stop();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

		if(passage.length()>1) {
			if(typed==0) {
				startTime = LocalTime.now().toNanoOfDay();
				
			}else if(typed == passage.length()) {
				endTime = LocalTime.now().toNanoOfDay();
				elapsed = endTime-startTime;
				seconds = elapsed/1000000000;
				
				WPM = (int) (((passage.length()/5)/seconds)*60);//gives the words per second;
				running = false;
			}
			
			//checking and storing the match of characters we typed
			char[] passChar = passage.toCharArray();
			if(typed < passage.length()) {
				running = true;
				
				if(e.getKeyChar()==passChar[typed]) {
					typedChar +=  passChar[typed];
					typed++;
					count++;
					System.out.println(typed+": "+typedChar);
				}else {
					wrongTyped++;
					System.out.println("You enetred wrong character");
				}
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
