import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;


@SuppressWarnings("serial")
public class ControlWindow extends JFrame {

	private JPanel contentPane;
	
	private static JTextArea textAreaOutput = new JTextArea();
	private JRadioButton rdbtnAddNode;
	private JRadioButton rdbtnAddBeam;
	private final ButtonGroup modeSelect = new ButtonGroup();
	private JPanel panelModeSelect;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelNode;
	private JPanel panelBeam;
	private JPanel panel_3;
	private Panel panelDraw;
	private JPanel panelNodeCoords;
	private JLabel lblX;
	private JLabel lblY;
	private static JSpinner spinX;
	private static JSpinner spinY;
	private JLabel lblNode;
	private static JSpinner spinNode;
	private JPanel panelNodeSelect;
	
	SpinnerModel spinModelDoubleX = new SpinnerNumberModel(new Double(0), null, null, 1);
	SpinnerModel spinModelDoubleY = new SpinnerNumberModel(new Double(0), null, null, 1);
	private JLabel lblForce;
	private JLabel lblAngle;
	private static JSpinner spinForce;
	private static JSpinner spinAngle;
	private JPanel panelNodeMovement;
	private static JRadioButton rdbtnFixed;
	private static JRadioButton rdbtnFixedX;
	private static JRadioButton rdbtnFixedY;
	private static JRadioButton rdbtnFree;
	private final ButtonGroup nodeMovement = new ButtonGroup();
	private JButton btnDelete;
	private JPanel panel;
	private JLabel lblBeam;
	private JSpinner spinner;
	private JButton btnSimulate;

	//begin methods
	
	public static void startControlGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlWindow frame = new ControlWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void print(String stuffToPrint) {
		textAreaOutput.append("\n"+stuffToPrint);
		textAreaOutput.setCaretPosition(textAreaOutput.getDocument().getLength());
	}
	
	public static void setMovementSelect(int mode) {
		switch (mode) {
		case Simulator.TYPE_FREE_NODE:
			ControlWindow.rdbtnFree.doClick();
			break;
			
		case Simulator.TYPE_FIXED_NODE:
			ControlWindow.rdbtnFixed.doClick();
			break;
			
		case Simulator.TYPE_FIXED_X_NODE:
			ControlWindow.rdbtnFixedX.doClick();
			break;
			
		case Simulator.TYPE_FIXED_Y_NODE:
			ControlWindow.rdbtnFixedY.doClick();
			break;

		default:
			break;
		}
	}
	
	public static void setSpinNode(int node) {
		spinNode.setValue(new Integer(node));
	}
	
	public static void setSpinX(double x) {
		spinX.setValue(new Double(x));
	}
	
	public static void setSpinY(double y) {
		spinY.setValue(new Double(y));
	}

	public static void setSpinForceY(double forceY) {
		spinAngle.setValue(new Double(forceY));
	}
	
	public static void setSpinForceX(double forceX) {
		spinForce.setValue(new Double(forceX));
	}

	public ControlWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1300, 700);
		setBounds(0, 0, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {978, -30, 42, 55, 37, 31, 43};
		gbl_contentPane.rowHeights = new int[] {0, 58, 61, 38, 91, 57, 135, 23, 40, 101};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		panelDraw = new OutputTest();
		panelDraw.setBackground(Color.white);
		panelDraw.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				OutputTest.WindowHeight=getHeight();
				OutputTest.WindowWidth=getWidth();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				
			}
		});
		GridBagConstraints gbc_panelDraw = new GridBagConstraints();
		gbc_panelDraw.fill = GridBagConstraints.BOTH;
		gbc_panelDraw.gridheight = 9;
		gbc_panelDraw.insets = new Insets(0, 0, 0, 5);
		gbc_panelDraw.gridx = 0;
		gbc_panelDraw.gridy = 1;
		contentPane.add(panelDraw, gbc_panelDraw);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridheight = 5;
		gbc_tabbedPane.gridwidth = 6;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 1;
		contentPane.add(tabbedPane, gbc_tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent c) {
				switch (tabbedPane.getSelectedIndex()) {
				case 0: //node
					try {	//weird null pointer exceptions happen here
						rdbtnAddNode.doClick();
					} catch (Exception e) {
						//nothing
					}					
					break;
					
				case 1: //beam
					rdbtnAddBeam.doClick();
					break;

				default:
					break;
				}
			}
		});
		panelNode = new JPanel();
		tabbedPane.addTab("Node", null, panelNode, null);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_N);
		GridBagLayout gbl_panelNode = new GridBagLayout();
		gbl_panelNode.columnWidths = new int[] {16, 54, 43, 20, 20, 20};
		gbl_panelNode.rowHeights = new int[] {10, 28, 0, 49, 57, 30, 30};
		gbl_panelNode.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		gbl_panelNode.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		panelNode.setLayout(gbl_panelNode);
		
		panelNodeSelect = new JPanel();
		panelNodeSelect.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelNodeSelect = new GridBagConstraints();
		gbc_panelNodeSelect.insets = new Insets(0, 0, 5, 5);
		gbc_panelNodeSelect.fill = GridBagConstraints.BOTH;
		gbc_panelNodeSelect.gridx = 1;
		gbc_panelNodeSelect.gridy = 1;
		panelNode.add(panelNodeSelect, gbc_panelNodeSelect);
		GridBagLayout gbl_panelNodeSelect = new GridBagLayout();
		gbl_panelNodeSelect.columnWidths = new int[]{25, 29, 0};
		gbl_panelNodeSelect.rowHeights = new int[]{20, 0};
		gbl_panelNodeSelect.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelNodeSelect.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelNodeSelect.setLayout(gbl_panelNodeSelect);
		
		lblNode = new JLabel("Node");
		lblNode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNode = new GridBagConstraints();
		gbc_lblNode.anchor = GridBagConstraints.WEST;
		gbc_lblNode.insets = new Insets(0, 0, 0, 5);
		gbc_lblNode.gridx = 0;
		gbc_lblNode.gridy = 0;
		panelNodeSelect.add(lblNode, gbc_lblNode);
		
		spinNode = new JSpinner();
		spinNode.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				OutputTest.selectNodePrint(((Integer) spinNode.getValue()).intValue(), true);
				
			}
		});
		GridBagConstraints gbc_spinNode = new GridBagConstraints();
		gbc_spinNode.fill = GridBagConstraints.BOTH;
		gbc_spinNode.gridx = 1;
		gbc_spinNode.gridy = 0;
		panelNodeSelect.add(spinNode, gbc_spinNode);
		
		panelNodeCoords = new JPanel();
		panelNodeCoords.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelNodeCoords = new GridBagConstraints();
		gbc_panelNodeCoords.gridwidth = 4;
		gbc_panelNodeCoords.insets = new Insets(0, 0, 5, 5);
		gbc_panelNodeCoords.fill = GridBagConstraints.BOTH;
		gbc_panelNodeCoords.gridx = 1;
		gbc_panelNodeCoords.gridy = 2;
		panelNode.add(panelNodeCoords, gbc_panelNodeCoords);
		GridBagLayout gbl_panelNodeCoords = new GridBagLayout();
		gbl_panelNodeCoords.columnWidths = new int[] {23, 49, 46, 40};
		gbl_panelNodeCoords.rowHeights = new int[] {20, 20};
		gbl_panelNodeCoords.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gbl_panelNodeCoords.rowWeights = new double[]{0.0, 0.0};
		panelNodeCoords.setLayout(gbl_panelNodeCoords);
		
		lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.fill = GridBagConstraints.BOTH;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 0;
		gbc_lblX.gridy = 0;
		panelNodeCoords.add(lblX, gbc_lblX);
		
		spinX = new JSpinner(spinModelDoubleX);
		spinX.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Simulator.setNodeLink(OutputTest.selectNode, Simulator.X ,((Double) spinX.getModel().getValue()).doubleValue());
				panelDraw.repaint();
			}
		});
		GridBagConstraints gbc_spinX = new GridBagConstraints();
		gbc_spinX.fill = GridBagConstraints.BOTH;
		gbc_spinX.insets = new Insets(0, 0, 5, 5);
		gbc_spinX.gridx = 1;
		gbc_spinX.gridy = 0;
		panelNodeCoords.add(spinX, gbc_spinX);
		
		lblForce = new JLabel("Force");
		lblForce.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblForce = new GridBagConstraints();
		gbc_lblForce.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblForce.insets = new Insets(0, 0, 5, 5);
		gbc_lblForce.gridx = 2;
		gbc_lblForce.gridy = 0;
		panelNodeCoords.add(lblForce, gbc_lblForce);
		
		spinForce = new JSpinner();
		spinForce.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		spinForce.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Simulator.setNodeLink(OutputTest.selectNode, Simulator.FORCE,((Double) spinForce.getModel().getValue()).doubleValue());
				/*if (foo[4] == Simulator.FREE_NODE && foo[2] != 0) {
					rdbtnFixed.doClick();
				}*/
				
			}
		});
		GridBagConstraints gbc_spinForce = new GridBagConstraints();
		gbc_spinForce.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinForce.insets = new Insets(0, 0, 5, 0);
		gbc_spinForce.gridx = 3;
		gbc_spinForce.gridy = 0;
		panelNodeCoords.add(spinForce, gbc_spinForce);
		
		lblY = new JLabel("Y");
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblY = new GridBagConstraints();
		gbc_lblY.fill = GridBagConstraints.BOTH;
		gbc_lblY.insets = new Insets(0, 0, 0, 5);
		gbc_lblY.gridx = 0;
		gbc_lblY.gridy = 1;
		panelNodeCoords.add(lblY, gbc_lblY);
		
		spinY = new JSpinner(spinModelDoubleY);
		spinY.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Simulator.setNodeLink(OutputTest.selectNode, Simulator.Y, ((Double) spinY.getModel().getValue()).doubleValue());
				panelDraw.repaint();
			}
		});
		GridBagConstraints gbc_spinY = new GridBagConstraints();
		gbc_spinY.insets = new Insets(0, 0, 0, 5);
		gbc_spinY.fill = GridBagConstraints.BOTH;
		gbc_spinY.gridx = 1;
		gbc_spinY.gridy = 1;
		panelNodeCoords.add(spinY, gbc_spinY);
		
		lblAngle = new JLabel("Angle");
		lblAngle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAngle = new GridBagConstraints();
		gbc_lblAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAngle.insets = new Insets(0, 0, 0, 5);
		gbc_lblAngle.gridx = 2;
		gbc_lblAngle.gridy = 1;
		panelNodeCoords.add(lblAngle, gbc_lblAngle);
		
		spinAngle = new JSpinner();
		spinAngle.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(0.01745329251)));
		spinAngle.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Simulator.setNodeLink(OutputTest.selectNode, Simulator.ANGLE, ((Double) spinAngle.getModel().getValue()).doubleValue());
			}
		});
		GridBagConstraints gbc_spinAngle = new GridBagConstraints();
		gbc_spinAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinAngle.gridx = 3;
		gbc_spinAngle.gridy = 1;
		panelNodeCoords.add(spinAngle, gbc_spinAngle);
		
		panelNodeMovement = new JPanel();
		panelNodeMovement.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelNodeMovement = new GridBagConstraints();
		gbc_panelNodeMovement.gridwidth = 2;
		gbc_panelNodeMovement.insets = new Insets(0, 0, 5, 5);
		gbc_panelNodeMovement.fill = GridBagConstraints.BOTH;
		gbc_panelNodeMovement.gridx = 1;
		gbc_panelNodeMovement.gridy = 3;
		panelNode.add(panelNodeMovement, gbc_panelNodeMovement);
		panelNodeMovement.setLayout(new GridLayout(0, 2, 0, 0));
		
		rdbtnFree = new JRadioButton("Free");
		rdbtnFree.setMnemonic('f');
		rdbtnFree.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Simulator.setNodeLinkType(OutputTest.selectNode, Simulator.TYPE_FREE_NODE);
				panelDraw.repaint();
			}
		});
		rdbtnFree.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnFree.doClick();
		nodeMovement.add(rdbtnFree);
		panelNodeMovement.add(rdbtnFree);
		
		rdbtnFixed = new JRadioButton("Fixed");
		rdbtnFixed.setMnemonic('x');
		rdbtnFixed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Simulator.setNodeLinkType(OutputTest.selectNode, Simulator.TYPE_FIXED_NODE);
				panelDraw.repaint();				
			}
		});
		rdbtnFixed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nodeMovement.add(rdbtnFixed);
		panelNodeMovement.add(rdbtnFixed);
		
		rdbtnFixedX = new JRadioButton("Fixed X");
		rdbtnFixedX.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Simulator.setNodeLinkType(OutputTest.selectNode, Simulator.TYPE_FIXED_X_NODE);
				panelDraw.repaint();				
			}
		});
		rdbtnFixedX.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nodeMovement.add(rdbtnFixedX);
		panelNodeMovement.add(rdbtnFixedX);
		
		rdbtnFixedY = new JRadioButton("Fixed Y");
		rdbtnFixedY.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Simulator.setNodeLinkType(OutputTest.selectNode, Simulator.TYPE_FIXED_Y_NODE);
				panelDraw.repaint();
			}
		});
		rdbtnFixedY.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nodeMovement.add(rdbtnFixedY);
		panelNodeMovement.add(rdbtnFixedY);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Simulator.deleteNode(OutputTest.selectNode);
				panelDraw.repaint();
			}
		});
		btnDelete.setMnemonic('d');
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 1;
		gbc_btnDelete.gridy = 4;
		panelNode.add(btnDelete, gbc_btnDelete);
		
		panelBeam = new JPanel();
		tabbedPane.addTab("Beam", null, panelBeam, null);
		GridBagLayout gbl_panelBeam = new GridBagLayout();
		gbl_panelBeam.columnWidths = new int[]{64, 104, 50, 54, 0};
		gbl_panelBeam.rowHeights = new int[]{73, 87, 60, 67, 0};
		gbl_panelBeam.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBeam.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBeam.setLayout(gbl_panelBeam);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		panelBeam.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{42, 29, 0};
		gbl_panel.rowHeights = new int[]{27, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblBeam = new JLabel("Beam");
		GridBagConstraints gbc_lblBeam = new GridBagConstraints();
		gbc_lblBeam.insets = new Insets(0, 0, 0, 5);
		gbc_lblBeam.gridx = 0;
		gbc_lblBeam.gridy = 0;
		panel.add(lblBeam, gbc_lblBeam);
		
		spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 0;
		panel.add(spinner, gbc_spinner);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_B);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		
		btnSimulate = new JButton("Simulate");
		btnSimulate.setMnemonic('a');
		btnSimulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Simulator.simulate();
				
			}
		});
		GridBagConstraints gbc_btnSimulate = new GridBagConstraints();
		gbc_btnSimulate.insets = new Insets(0, 0, 5, 5);
		gbc_btnSimulate.gridx = 2;
		gbc_btnSimulate.gridy = 7;
		contentPane.add(btnSimulate, gbc_btnSimulate);
		
		panelModeSelect = new JPanel();
		panelModeSelect.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelModeSelect = new GridBagConstraints();
		gbc_panelModeSelect.gridwidth = 5;
		gbc_panelModeSelect.insets = new Insets(0, 0, 5, 5);
		gbc_panelModeSelect.fill = GridBagConstraints.BOTH;
		gbc_panelModeSelect.gridx = 1;
		gbc_panelModeSelect.gridy = 8;
		contentPane.add(panelModeSelect, gbc_panelModeSelect);
		
		rdbtnAddNode = new JRadioButton("Add Node");
		rdbtnAddNode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelModeSelect.add(rdbtnAddNode);
		modeSelect.add(rdbtnAddNode);
		
		rdbtnAddBeam = new JRadioButton("Add Beam");
		rdbtnAddBeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelModeSelect.add(rdbtnAddBeam);
		modeSelect.add(rdbtnAddBeam);		
		rdbtnAddBeam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OutputTest.mode=2;
				
			}
		});
		rdbtnAddNode.doClick();
		rdbtnAddNode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OutputTest.mode=1;
				
			}
		});
		

		JScrollPane scrollPaneOutput = new JScrollPane(textAreaOutput);
		scrollPaneOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPaneOutput = new GridBagConstraints();
		gbc_scrollPaneOutput.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneOutput.gridwidth = 6;
		gbc_scrollPaneOutput.gridx = 1;
		gbc_scrollPaneOutput.gridy = 9;
		contentPane.add(scrollPaneOutput, gbc_scrollPaneOutput);
		textAreaOutput.setRows(3);
		textAreaOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaOutput.setEditable(false);

		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabbedPane, panelModeSelect, rdbtnAddNode, rdbtnAddBeam, scrollPaneOutput, textAreaOutput}));
	}

}