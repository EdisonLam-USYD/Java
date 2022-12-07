/*
 * Denonstration of 2D Ray tracing (use alt to draw lines)
 * Completed 7/12/2022
 */

package Projects.RayTracing2D;

import javax.swing.JFrame;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputListener;
import java.awt.image.BufferStrategy;


public class Driver implements Runnable, MouseInputListener {

    private LinkedList<Line2DBlock> lineBlocks;
    private Canvas canvas;
    private int WIDTH = 1680, HEIGHT = 900, BORDER = 10;
    private int mouseX, mouseY;
    private int mouseX_drawLine, mouseY_drawLine;
    private PointOfReference pov;
    private boolean changeMade;

    
    Driver() {
        JFrame frame = new JFrame("2D Ray Tracing");
        canvas = new Canvas();
        canvas.setSize(WIDTH, HEIGHT);

        // frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.setSize(WIDTH, HEIGHT+10*BORDER);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        lineBlocks = lines_init();

        // set up for pov object
        mouseX = (int) WIDTH/2;
        mouseY = (int) HEIGHT/2;
        pov = new PointOfReference(lineBlocks, mouseX, mouseY, canvas.getWidth(), canvas.getHeight(), 256);
        
        new Thread(this).start();
    }

    private LinkedList<Line2DBlock> lines_init() {
        // first is the border or the screen
        lineBlocks = new LinkedList<>();
        lineBlocks.add(new Line2DBlock(BORDER, BORDER, BORDER, canvas.getHeight()-BORDER));
        lineBlocks.add(new Line2DBlock(BORDER, canvas.getHeight()-BORDER, canvas.getWidth()-BORDER, canvas.getHeight()-BORDER));
        lineBlocks.add(new Line2DBlock(canvas.getWidth()-BORDER, canvas.getHeight()-BORDER, canvas.getWidth()-BORDER, BORDER));
        lineBlocks.add(new Line2DBlock(canvas.getWidth()-BORDER, BORDER, BORDER, BORDER));
        lineBlocks.add(new Line2DBlock(BORDER, BORDER, canvas.getWidth()-BORDER, canvas.getHeight()-BORDER));

        return lineBlocks;
    }

    @Override
    public void run() {
        while (true) {
            tick();
            render();

        }        
    }

    private void tick() {
        if (changeMade) {
            changeMade = false;
            pov.setLocation(mouseX,mouseY);
            pov.calculateRays();
        }
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
        for (Line2DBlock line: this.lineBlocks) {
            g.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
        }

        g.setColor(Color.WHITE);
        // g.drawOval(mouseX, mouseY, 3, 3);
        for (Ray ray : pov.getRays()) {
            g.drawLine((int) ray.x1, (int) ray.y1, (int) ray.x2, (int) ray.y2);
        }
        g.dispose();
        bs.show();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        if (!e.isAltDown()) return;
        mouseX_drawLine = e.getX();
        mouseY_drawLine = e.getY();
    }
    public void mouseReleased(MouseEvent e) {
        if (!e.isAltDown()) return;
        lineBlocks.add(new Line2DBlock(mouseX_drawLine, mouseY_drawLine, e.getX(), e.getY()));
        changeMade = true;
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
        if (e.isAltDown()) return;
        mouseX = e.getX();
        mouseY = e.getY();
        changeMade = true;        
    }

    public static void main(String[] args) {
        new Driver();
    }
    
}
