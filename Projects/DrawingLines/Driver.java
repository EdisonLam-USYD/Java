/*  Created by Edison Lam starting on the 30/11/2022
 *  made to gain a better understanding of how to implement Java's Abstract Window Toolkit (AWT)
 * 
 */

package Projects.DrawingLines;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.JFrame;

import javax.swing.event.MouseInputListener;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Driver implements Runnable, MouseInputListener{
    private Canvas canvas;
    private LinkedList<Line2D.Float> lines;
    private int WIDTH = 1680, HEIGHT = 900, BORDER = 10;
    private int mouseX, mouseXc;
    private int mouseY, mouseYc;
    private boolean mouse_clicked;

    public Driver() {
        JFrame frame = new JFrame("Line Drawer"); // creates a container
        canvas = new Canvas();
        canvas.setSize(WIDTH, HEIGHT);
        // cordLabel = new JLabel("....");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.setSize(WIDTH, HEIGHT+10*BORDER);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        lines = lines_init();
        
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            tick();
            render();

        }
    }

    private void tick() {
    }
    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        // drawing background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); // starting top left to bottom right

        // drawing lines
        g.setColor(Color.GREEN);
        for (Line2D.Float line: this.lines) {
            g.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
        }

        g.setColor(Color.ORANGE);
        if (mouse_clicked){
            g.drawLine(mouseX, mouseY, mouseXc, mouseYc);
        }

        g.dispose();
        bs.show();

    }

    // drawing the lines
    private LinkedList<Line2D.Float> lines_init() {
        // first is the border or the screen
        lines = new LinkedList<>();
        lines.add(new Line2D.Float(BORDER, BORDER, BORDER, canvas.getHeight()-BORDER));
        lines.add(new Line2D.Float(BORDER, canvas.getHeight()-BORDER, canvas.getWidth()-BORDER, canvas.getHeight()-BORDER));
        lines.add(new Line2D.Float(canvas.getWidth()-BORDER, canvas.getHeight()-BORDER, canvas.getWidth()-BORDER, BORDER));
        lines.add(new Line2D.Float(canvas.getWidth()-BORDER, BORDER, BORDER, BORDER));

        return lines;
    }

    // mouse inputs (only care about clicked, released, pressed?, dragged to draw lines)
    public void mouseExited(MouseEvent e){
        this.mouse_clicked = false;
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseMoved(MouseEvent e){    }
    
    public void mouseReleased(MouseEvent e){
        this.mouse_clicked = false;
        this.lines.add(new Line2D.Float(mouseX, mouseY, e.getX(), e.getY()));
        System.out.printf("this.lines.add(new Line2D.Float(%d, %d, %d, %d));\n", mouseX, mouseY, e.getX(), e.getY());
    }
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){
        if (!mouse_clicked) {
            // System.out.println("Mouse Pressed!");
            mouse_clicked = true;
            this.mouseX = e.getX();
            this.mouseY = e.getY();
            this.mouseXc = e.getX();
            this.mouseYc = e.getY();
        }
    }
    public void mouseDragged(MouseEvent e){
        if (!mouse_clicked) {
            mouse_clicked = true;
            this.mouseX = e.getX();
            this.mouseY = e.getY();
        }
        this.mouseXc = e.getX();
        this.mouseYc = e.getY();
    }

    public static void main(String[] args) {
        new Driver();
    }
}
