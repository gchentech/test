

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;



public class Drawing extends JFrame{
	private DrawingArea d;
	public Drawing(){
	super("Drawing");
	setSize(800,600);
	Container c = getContentPane();
	JPanel p = new JPanel();
	
	
	p.setLayout(new GridLayout(2,1));
	JButton b = new JButton("clear");
	b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			d.clear();
		}
	}
	);
	p.add(b);
	c.add(p, BorderLayout.WEST);
	d = new DrawingArea();
	c.add(d, BorderLayout.CENTER);
	addWindowListener(new MyWindowListener());
	setVisible(true);
	}
	
	public static void main(String[] args){
		new Drawing();
	}
}
class MyWindowListener extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}

class DrawingArea extends JPanel {
	private ArrayList<Line> line;
	public DrawingArea() {
		
		line = new ArrayList<Line>();
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		MyMouseListener m = new MyMouseListener();
		addMouseListener(m);
		addMouseMotionListener(m);
	}
	public void clear() {
		line.clear();
		repaint();
	}
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		for (Line l : line) {
			l.paint(g);
		}
	}

	class MyMouseListener implements MouseListener, MouseMotionListener {
		private int x,y;
		private int lastX, lastY;
		@Override public void mouseClicked(MouseEvent e) {
		}
		@Override public void mousePressed(MouseEvent e) {
			x = e.getX(); y = e.getY();
			lastX = x; lastY = y;		
		}
		@Override public void mouseReleased(MouseEvent e) {
			Graphics g = getGraphics();
			g.drawLine(x, y, e.getX() , e.getY());
			line.add(new Line(x, y, e.getX(),getY()));
		}

		@Override public void mouseEntered(MouseEvent e) {
		}

		@Override public void mouseExited(MouseEvent e) {}
		public void mouseDragged(MouseEvent e) {
			Graphics g = getGraphics();
			g.setXORMode(Color.WHITE);
			g.drawLine(x, y, lastX , lastY );
			g.drawLine(x, y, e.getX() , e.getY() );
			lastX = e.getX(); lastY = e.getY();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}
}
class Line{
	protected double x1,y1,x2,y2;
	public Line(double x1,double y1,double x2,double y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void paint(Graphics g){
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

}