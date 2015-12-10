package ess.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ess.data.Composite;
import ess.strings.CustomErrorMessages;
import ess.strings.CustomInfoMessages;
import ess.ui.components.CompositePanel;
import ess.ui.components.CustomLabel;
import ess.ui.components.CustomPanel;

/**
 * This class is an implementation of IComposite view that displays a composite.
 * 
 * @author Monika Schrenk
 *
 */
public class MainWindow extends JFrame implements ICompositeView {

    private static final long serialVersionUID = 1L;

    // constants for sizing the application window and its contents
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double MAX_INITIAL_WINDOW_WIDTH = SCREEN_SIZE.getWidth() * 0.5;
    private static final double MAX_INITIAL_WINDOW_HEIGHT = SCREEN_SIZE.getHeight() * 0.75;
    private static final Dimension MIN_WINDOW_SIZE = new Dimension(100, 100);

    // custom background color for top and bottom area
    private static final Color DARK_BACKGROUND = new Color(0, 0, 102);

    // custom padding width
    private static final int COMPONENT_PADDING = 20;
    
    // constants for customizing font
    private static final int TEXT_PADDING = 3;
    private static final int DEFAULT_FONT_SIZE = 14;
    
    // constant for initial size of a single field in the surface
    private static final int INITIAL_FIELD_SIZE = 20;

    // window components
    private JPanel pnlTop, pnlInfo, pnlCenter, pnlBottom;
    private JScrollPane scpCenter;
    private CompositePanel pnlComposite;
    private JButton btnClose;

    // data that gets displayed
    private Composite composite;

    /**
     * Instantiates a MainWindow for displaying composite.
     * 
     * @param composite the data that gets displayed
     */
    public MainWindow(Composite composite) {
        this.composite = composite;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            setIconImage(ImageIO.read(getClass().getResourceAsStream("/resources/ic_starter.png")));
        } catch (IOException e) {
            // if application icon is missing, just inform the user, but continue everything else
            System.out.println(CustomErrorMessages.ERROR_APP_ICON);
        }
        // TODO open with best possible zoom
        // TODO view file path & different modes (d, v, s)
    }

    @Override
    public void display(List<String> errorList, String pathToSource, String execMode) {
        // show file path as title
        setTitle(pathToSource);

        // create window components and add them together
        createAndAddTopArea();
        createAndAddInfoLabels(errorList, execMode);
        createAndAddCenterArea();
        createAndAddBottomArea();
        addListeners();

        // calculate measurements of window and its components
        pack();
        
        // window should not get too small if composite is very small
        setMinimumSize(MIN_WINDOW_SIZE);
        
        // center window on screen
        setLocationRelativeTo(null);
        
        // scpCenter needs focus for recognizing keystrokes (zooming),
        // otherwise the close button would have the focus
        scpCenter.requestFocusInWindow();
        
        // show window
        setVisible(true);
    }

    // construct center area of window (data)
    private void createAndAddCenterArea() {
        pnlComposite = new CompositePanel(composite.getSurface(), INITIAL_FIELD_SIZE);
        pnlCenter = new CustomPanel(COMPONENT_PADDING);
        pnlCenter.setLayout(new GridBagLayout());
        JLabel lblZoomInfo = new CustomLabel("Zum Zoomen + und - verwenden.", DEFAULT_FONT_SIZE, Color.BLACK);
        
        // use GridBagLayout to easily center content of pnlCenter horizontally & vertically
        // add lblZoomInfo on top of center area
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        pnlCenter.add(lblZoomInfo, gbc);
        
        // add pnlCenter below lblZoomInfo
        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.weighty = 1;
        pnlCenter.add(pnlComposite, gbc);

        // make content of center scrollable to be able to display very large composites
        scpCenter = new JScrollPane(pnlCenter);
        getContentPane().add(scpCenter, BorderLayout.CENTER);
        
        // if the composite is large, open a smaller window with scroll bars
        scpCenter.setPreferredSize(new Dimension(
                (int) Math.min(MAX_INITIAL_WINDOW_WIDTH, pnlCenter.getPreferredSize().getWidth() + COMPONENT_PADDING), 
                (int) Math.min(MAX_INITIAL_WINDOW_HEIGHT, pnlCenter.getPreferredSize().getHeight() + COMPONENT_PADDING)));
    }

    // construct bottom section of window (close button)
    private void createAndAddBottomArea() {
        pnlBottom = new CustomPanel(DARK_BACKGROUND, COMPONENT_PADDING);
        btnClose = new JButton("Schließen");
        
        pnlBottom.add(btnClose);
        getContentPane().add(pnlBottom, BorderLayout.AFTER_LAST_LINE);
    }

    // construct top section of window (info about composite)
    private void createAndAddTopArea() {
        pnlTop = new CustomPanel(DARK_BACKGROUND, COMPONENT_PADDING);
        pnlTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlInfo = new CustomPanel(0);
        pnlInfo.setLayout(new GridLayout(0, 1, TEXT_PADDING, TEXT_PADDING));

        pnlTop.add(pnlInfo);
        getContentPane().add(pnlTop, BorderLayout.BEFORE_FIRST_LINE);
    }

    // create labels that hold info messages, depending on application mode
    private void createAndAddInfoLabels(List<String> errorList, String execMode) {
        pnlInfo.add(new CustomLabel(execMode, DEFAULT_FONT_SIZE, Color.LIGHT_GRAY));
        if (errorList == null) {
            return;
        }
        if (errorList.size() == 0) {
            JLabel label = new CustomLabel(CustomInfoMessages.INFO_VALID_COMPOSITE, DEFAULT_FONT_SIZE, Color.LIGHT_GRAY);
            pnlInfo.add(label);
        } else {
            for (int i = 0; i < errorList.size(); i++) {
                JLabel label = new CustomLabel(errorList.get(i), DEFAULT_FONT_SIZE, Color.LIGHT_GRAY);
                pnlInfo.add(label);
            }
        }
    }

    private void addListeners() {
        // Exit application when clicking on close button
        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Enable zooming on composite with + and - keys
        scpCenter.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyChar()) {
                    case '+':
                        pnlComposite.increaseFieldSize();
                        break;
                    case '-':
                        pnlComposite.decreaseFieldSize();
                        break;
                    default:
                        // do nothing when other keys are pressed
                }
            }
        });
    }

}
