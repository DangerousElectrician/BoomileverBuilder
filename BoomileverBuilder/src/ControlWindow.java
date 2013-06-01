import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
public class ControlWindow extends JPanel {

	private final JPanel contentPane;

	private final  JTextArea textAreaOutput = new JTextArea();
	private final JRadioButton rdbtnAddNode;
	private final JRadioButton rdbtnAddBeam;
	private final ButtonGroup modeSelect = new ButtonGroup();
	private final JPanel panelModeSelect;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel panelNode;
	private final JPanel panelBeam;
	private final JPanel panelActionLog;
	private final JPanel panelDraw;
	private final JPanel panelNodeCoords;
	private final JLabel lblX;
	private final JLabel lblY;
	private final  JSpinner spinX;
	private final  JSpinner spinY;
	private final JLabel lblNode;
	private final  JSpinner spinNode;
	private final JPanel panelNodeSelect;

	SpinnerModel spinModelDoubleX = new SpinnerNumberModel(new Double(0), null, null, 1);
	SpinnerModel spinModelDoubleY = new SpinnerNumberModel(new Double(0), null, null, 1);
	private final JLabel lblForce;
	private final JLabel lblAngle;
	private final  JSpinner spinForce;
	private final  JSpinner spinAngle;
	private final JPanel panelNodeMovement;
	private final  JRadioButton rdbtnFixed;
	private final  JRadioButton rdbtnFixedX;
	private final  JRadioButton rdbtnFixedY;
	private final  JRadioButton rdbtnFree;
	private final ButtonGroup nodeMovement = new ButtonGroup();
	private final JButton btnDelete;
	private final JPanel panel;
	private final JLabel lblBeam;
	private final JSpinner spinner;
	private final JButton btnSimulate;
	private final JPanel nodeInfo;
	private final JPanel infoDisp;
	private final JLabel lblX_1;
	private final  JLabel lblXinfo;
	private final JLabel lblY_1;
	private final  JLabel lblYinfo;
	private final JLabel lblForce_1;
	private final JLabel lblAngle_1;
	private final  JLabel lblForceInfo;
	private final  JLabel lblAngleInfo;
	private final JLabel lblNode_1;
	private final  JLabel lblNodeInfo;


	//begin methods

	public  void startControlGUI(final Simulator sim) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ControlWindow frame = new ControlWindow(sim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public  void print(String stuffToPrint) {
		textAreaOutput.append("\n"+stuffToPrint);
		textAreaOutput.setCaretPosition(textAreaOutput.getDocument().getLength());
	}

	public  void setMovementSelect(int mode) {
		switch (mode) {
		case Simulator.TYPE_FREE_NODE:
			this.rdbtnFree.doClick();
			break;

		case Simulator.TYPE_FIXED_NODE:
			this.rdbtnFixed.doClick();
			break;

		case Simulator.TYPE_FIXED_X_NODE:
			this.rdbtnFixedX.doClick();
			break;

		case Simulator.TYPE_FIXED_Y_NODE:
			this.rdbtnFixedY.doClick();
			break;

		default:
			break;
		}
	}

	public  void setSpinNode(int node) {
		spinNode.setValue(new Integer(node));
	}

	public  void setSpinX(double x) {
		spinX.setValue(new Double(x));
	}

	public  void setSpinY(double y) {
		spinY.setValue(new Double(y));
	}

	public  void setSpinAngle(double angle) {
		spinAngle.setValue(new Double(Math.toDegrees(angle)));
	}

	public  void setSpinForceX(double force) {
		spinForce.setValue(new Double(force));
	}

	public  void setInfoX(double x) {
		lblXinfo.setText(Double.toString(x));
	}

	public  void setInfoY(double x) {
		lblYinfo.setText(Double.toString(x));
	}

	public  void setInfoForce(double x) {
		lblForceInfo.setText(Double.toString(x));
	}

	public  void setInfoAngle(double x) {
		lblAngleInfo.setText(Double.toString(x));
	}

	public  void setInfoNode(int node) {
		lblNodeInfo.setText(Integer.toString(node));
	}

	public ControlWindow(final Simulator sim) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1300, 700);
		setBounds(0, 0, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {978, -30, 0, 59, 35, 37, 66};
		gbl_contentPane.rowHeights = new int[] {0, 58, 61, 38, 101, 48, 0, 15, -79, 100};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
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
		gbl_panelNode.rowHeights = new int[] {10, 28, 0, 49, 57};
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
				sim.setNodeLink(OutputTest.selectNode, Simulator.X ,((Double) spinX.getModel().getValue()).doubleValue());
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
				sim.setNodeLink(OutputTest.selectNode, Simulator.FORCE,((Double) spinForce.getModel().getValue()).doubleValue());
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
				sim.setNodeLink(OutputTest.selectNode, Simulator.Y, ((Double) spinY.getModel().getValue()).doubleValue());
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
		spinAngle.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		spinAngle.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sim.setNodeLink(OutputTest.selectNode, Simulator.ANGLE, (Math.toRadians((Double) spinAngle.getModel().getValue())));
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
				sim.setNodeLinkType(OutputTest.selectNode, Simulator.TYPE_FREE_NODE);
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
				sim.setNodeLinkType(OutputTest.selectNode, Simulator.TYPE_FIXED_NODE);
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
				sim.setNodeLinkType(OutputTest.selectNode, Simulator.TYPE_FIXED_X_NODE);
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
				sim.setNodeLinkType(OutputTest.selectNode, Simulator.TYPE_FIXED_Y_NODE);
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
				sim.deleteNode(OutputTest.selectNode);
				panelDraw.repaint();
			}
		});
		btnDelete.setMnemonic('d');
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
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

		panelActionLog = new JPanel();
		tabbedPane.addTab("Log", null, panelActionLog, null);
		GridBagLayout gbl_panelActionLog = new GridBagLayout();
		gbl_panelActionLog.columnWidths = new int[]{222, 0};
		gbl_panelActionLog.rowHeights = new int[]{57, 0};
		gbl_panelActionLog.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelActionLog.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelActionLog.setLayout(gbl_panelActionLog);


		JScrollPane scrollPaneOutput = new JScrollPane(textAreaOutput);
		GridBagConstraints gbc_scrollPaneOutput = new GridBagConstraints();
		gbc_scrollPaneOutput.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneOutput.gridx = 0;
		gbc_scrollPaneOutput.gridy = 0;
		panelActionLog.add(scrollPaneOutput, gbc_scrollPaneOutput);
		scrollPaneOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textAreaOutput.setRows(3);
		textAreaOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaOutput.setEditable(false);

		panelModeSelect = new JPanel();
		rdbtnAddNode = new JRadioButton("Add Node");
		rdbtnAddBeam = new JRadioButton("Add Beam");
				contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabbedPane, panelModeSelect, rdbtnAddNode, rdbtnAddBeam, scrollPaneOutput, textAreaOutput}));

		btnSimulate = new JButton("Simulate");
		btnSimulate.setMnemonic('a');
		btnSimulate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sim.simulate();

			}
		});
		GridBagConstraints gbc_btnSimulate = new GridBagConstraints();
		gbc_btnSimulate.insets = new Insets(0, 0, 5, 5);
		gbc_btnSimulate.gridx = 3;
		gbc_btnSimulate.gridy = 6;
		contentPane.add(btnSimulate, gbc_btnSimulate);

		panelModeSelect.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelModeSelect = new GridBagConstraints();
		gbc_panelModeSelect.anchor = GridBagConstraints.NORTH;
		gbc_panelModeSelect.gridwidth = 5;
		gbc_panelModeSelect.insets = new Insets(0, 0, 5, 0);
		gbc_panelModeSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelModeSelect.gridx = 2;
		gbc_panelModeSelect.gridy = 7;
		contentPane.add(panelModeSelect, gbc_panelModeSelect);

		rdbtnAddNode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelModeSelect.add(rdbtnAddNode);
		modeSelect.add(rdbtnAddNode);

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

		nodeInfo = new JPanel();
		nodeInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_nodeInfo = new GridBagConstraints();
		gbc_nodeInfo.gridwidth = 6;
		gbc_nodeInfo.gridheight = 2;
		gbc_nodeInfo.fill = GridBagConstraints.BOTH;
		gbc_nodeInfo.gridx = 1;
		gbc_nodeInfo.gridy = 8;
		contentPane.add(nodeInfo, gbc_nodeInfo);
		GridBagLayout gbl_nodeInfo = new GridBagLayout();
		gbl_nodeInfo.columnWidths = new int[]{0, 0, 0, 0};
		gbl_nodeInfo.rowHeights = new int[]{0, 0, 0};
		gbl_nodeInfo.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_nodeInfo.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		nodeInfo.setLayout(gbl_nodeInfo);

		infoDisp = new JPanel();
		infoDisp.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_infoDisp = new GridBagConstraints();
		gbc_infoDisp.insets = new Insets(0, 0, 5, 5);
		gbc_infoDisp.gridx = 1;
		gbc_infoDisp.gridy = 0;
		nodeInfo.add(infoDisp, gbc_infoDisp);
		GridBagLayout gbl_infoDisp = new GridBagLayout();
		gbl_infoDisp.columnWidths = new int[]{80, 35, 36, 32, 0};
		gbl_infoDisp.rowHeights = new int[]{0, 14, 0, 0};
		gbl_infoDisp.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_infoDisp.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		infoDisp.setLayout(gbl_infoDisp);

		lblNode_1 = new JLabel("Node: ");
		lblNode_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNode_1 = new GridBagConstraints();
		gbc_lblNode_1.anchor = GridBagConstraints.EAST;
		gbc_lblNode_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNode_1.gridx = 0;
		gbc_lblNode_1.gridy = 0;
		infoDisp.add(lblNode_1, gbc_lblNode_1);

		lblNodeInfo = new JLabel("0");
		lblNodeInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNodeInfo = new GridBagConstraints();
		gbc_lblNodeInfo.anchor = GridBagConstraints.WEST;
		gbc_lblNodeInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNodeInfo.gridx = 1;
		gbc_lblNodeInfo.gridy = 0;
		infoDisp.add(lblNodeInfo, gbc_lblNodeInfo);

		lblX_1 = new JLabel("X: ");
		lblX_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblX_1 = new GridBagConstraints();
		gbc_lblX_1.anchor = GridBagConstraints.EAST;
		gbc_lblX_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_1.gridx = 0;
		gbc_lblX_1.gridy = 1;
		infoDisp.add(lblX_1, gbc_lblX_1);

		lblXinfo = new JLabel("0");
		lblXinfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblXinfo = new GridBagConstraints();
		gbc_lblXinfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblXinfo.anchor = GridBagConstraints.WEST;
		gbc_lblXinfo.gridx = 1;
		gbc_lblXinfo.gridy = 1;
		infoDisp.add(lblXinfo, gbc_lblXinfo);

		lblForce_1 = new JLabel("Force: ");
		lblForce_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblForce_1 = new GridBagConstraints();
		gbc_lblForce_1.anchor = GridBagConstraints.EAST;
		gbc_lblForce_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblForce_1.gridx = 2;
		gbc_lblForce_1.gridy = 1;
		infoDisp.add(lblForce_1, gbc_lblForce_1);

		lblForceInfo = new JLabel("0");
		lblForceInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblForceInfo = new GridBagConstraints();
		gbc_lblForceInfo.anchor = GridBagConstraints.WEST;
		gbc_lblForceInfo.insets = new Insets(0, 0, 5, 0);
		gbc_lblForceInfo.gridx = 3;
		gbc_lblForceInfo.gridy = 1;
		infoDisp.add(lblForceInfo, gbc_lblForceInfo);

		lblY_1 = new JLabel("Y: ");
		lblY_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblY_1 = new GridBagConstraints();
		gbc_lblY_1.anchor = GridBagConstraints.EAST;
		gbc_lblY_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblY_1.gridx = 0;
		gbc_lblY_1.gridy = 2;
		infoDisp.add(lblY_1, gbc_lblY_1);

		lblYinfo = new JLabel("0");
		lblYinfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblYinfo = new GridBagConstraints();
		gbc_lblYinfo.anchor = GridBagConstraints.WEST;
		gbc_lblYinfo.insets = new Insets(0, 0, 0, 5);
		gbc_lblYinfo.gridx = 1;
		gbc_lblYinfo.gridy = 2;
		infoDisp.add(lblYinfo, gbc_lblYinfo);

		lblAngle_1 = new JLabel("Angle: ");
		lblAngle_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAngle_1 = new GridBagConstraints();
		gbc_lblAngle_1.anchor = GridBagConstraints.EAST;
		gbc_lblAngle_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblAngle_1.gridx = 2;
		gbc_lblAngle_1.gridy = 2;
		infoDisp.add(lblAngle_1, gbc_lblAngle_1);

		lblAngleInfo = new JLabel("0");
		lblAngleInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAngleInfo = new GridBagConstraints();
		gbc_lblAngleInfo.anchor = GridBagConstraints.WEST;
		gbc_lblAngleInfo.gridx = 3;
		gbc_lblAngleInfo.gridy = 2;
		infoDisp.add(lblAngleInfo, gbc_lblAngleInfo);
	}

}