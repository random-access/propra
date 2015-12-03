package ess.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ess.data.Composite;

public class MainWindow extends JFrame implements ICompositeView {

    private static final long serialVersionUID = 1L;
    
    private static final double MAX_INITIAL_WINDOW_WIDTH = 0.5;
    private static final double MAX_INITIAL_WINDOW_HEIGHT = 0.75;
    private static final Dimension MIN_WINDOW_SIZE = new Dimension(100, 100);
    
    private static final Color BLUE = new Color(0, 0, 102);

    private static final int COMPONENT_PADDING = 20;
    private static final int TEXT_PADDING = 3;
    private static final int DEFAULT_FONT_SIZE = 14;

    private JPanel pnlTop, pnlInfo, pnlCenter, pnlBottom;
    private JScrollPane scpCenter;
    private CompositePanel cPanel;
    private JButton btnClose;

    private Composite composite;

    public MainWindow(Composite composite) {
        this.composite = composite;
    }

    @Override
    public void display(List<String> errorList) {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Roemischer Verbund");
        getContentPane().setBackground(Color.WHITE);

        createAndAddWidgets(errorList);
        addListeners();

        pack();
        setMinimumSize(MIN_WINDOW_SIZE);
        setLocationRelativeTo(null);
        scpCenter.requestFocusInWindow();
        setVisible(true);
    }

    private void createAndAddWidgets(List<String> errorList) {

        pnlTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlTop.setBorder(BorderFactory.createEmptyBorder(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING,
                COMPONENT_PADDING));
        pnlTop.setBackground(BLUE);
        getContentPane().add(pnlTop, BorderLayout.BEFORE_FIRST_LINE);

        pnlInfo = new JPanel(new GridLayout(0, 1, TEXT_PADDING, TEXT_PADDING));
        pnlInfo.setOpaque(false);
        pnlTop.add(pnlInfo);

        if (errorList.size() == 0) {
            JLabel label = new JLabel("Dies ist ein gültiger Römischer Verbund :)");
            customizeFont(label);
            pnlInfo.add(label);
        } else {
            for (int i = 0; i < errorList.size(); i++) {
                JLabel label = new JLabel(errorList.get(i));
                customizeFont(label);
                pnlInfo.add(label);
            }
        }

        cPanel = new CompositePanel(composite.getSurface());
        pnlCenter = new JPanel();
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING,
                COMPONENT_PADDING));
        pnlCenter.setOpaque(false);
        pnlCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        pnlCenter.add(cPanel, gbc);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        scpCenter = new JScrollPane(pnlCenter);
        scpCenter.setPreferredSize(new Dimension((int) Math.min(screenSize.getWidth() * MAX_INITIAL_WINDOW_WIDTH, 
                pnlCenter.getPreferredSize().getWidth()), (int) Math.min(screenSize.getHeight() * MAX_INITIAL_WINDOW_HEIGHT, 
                        pnlCenter.getPreferredSize().getHeight())));
        getContentPane().add(scpCenter, BorderLayout.CENTER);

        pnlBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setBorder(BorderFactory.createEmptyBorder(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING,
                COMPONENT_PADDING));
        pnlBottom.setBackground(BLUE);
        getContentPane().add(pnlBottom, BorderLayout.AFTER_LAST_LINE);

        btnClose = new JButton("Schließen");
        pnlBottom.add(btnClose);
    }

    private void customizeFont(JLabel label) {
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, DEFAULT_FONT_SIZE));
        label.setOpaque(false);
        label.setForeground(Color.LIGHT_GRAY);
    }

    private void addListeners() {
        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        scpCenter.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '+':
                        cPanel.increaseFieldSize();
                        break;
                    case '-':
                        cPanel.decreaseFieldSize();
                        break;
                    default:
                        // do nothing
                }
            }
        });
    }
}
