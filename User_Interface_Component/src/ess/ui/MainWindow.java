package ess.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ess.data.Composite;

public class MainWindow extends JFrame implements ICompositeView {

    private static final long serialVersionUID = 1L;

    private static final int COMPONENT_PADDING = 20;
    private static final int TEXT_PADDING = 3;
    private static final int DEFAULT_FONT_SIZE = 14;

    private JPanel pnlTop, pnlInfo, pnlCenter, pnlBottom;
    private JButton btnClose;

    private Composite composite;

    public MainWindow(Composite composite) {
        this.composite = composite;
    }

    @Override
    public void display(String[] errorList) {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Roemischer Verbund");
        getContentPane().setBackground(Color.WHITE);

        createAndAddWidgets(errorList);
        addListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createAndAddWidgets(String[] errorList) {

        pnlTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlTop.setBorder(BorderFactory.createEmptyBorder(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING,
                COMPONENT_PADDING));
        pnlTop.setBackground(new Color(0, 0, 102));
        getContentPane().add(pnlTop, BorderLayout.BEFORE_FIRST_LINE);

        pnlInfo = new JPanel(new GridLayout(0, 1, TEXT_PADDING, TEXT_PADDING));
        pnlInfo.setOpaque(false);
        pnlTop.add(pnlInfo);

        if (errorList.length == 0) {
            JLabel label = new JLabel("Dies ist ein gültiger Römischer Verbund :)");
            customizeFont(label);
            pnlInfo.add(label);
        } else {
            for (int i = 0; i < errorList.length; i++) {
                JLabel label = new JLabel(errorList[i]);
                customizeFont(label);
                pnlInfo.add(label);
            }
        }

        pnlCenter = new JPanel();
        pnlCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING,
                COMPONENT_PADDING));
        pnlCenter.setOpaque(false);
        getContentPane().add(pnlCenter, BorderLayout.CENTER);

        CompositePanel cPanel = new CompositePanel(composite.getSurface());
        pnlCenter.add(cPanel, gbc);

        pnlBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setBorder(BorderFactory.createEmptyBorder(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING,
                COMPONENT_PADDING));
        pnlBottom.setBackground(new Color(0, 0, 102));
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
    }

}
