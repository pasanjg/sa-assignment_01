package administrator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.osgi.framework.ServiceReference;

import carservice.CarService;
import driverService.DriverService;
public class GUIBuilder {

	public static void AdministratorCar(ArrayList<String> carList, CarService carService) {
		JFrame frame = new JFrame("Welcome to Administrator");
		frame.getContentPane().setLayout(new GridLayout(1, 0,50,50));
		JPanel mainPanel = new JPanel(new GridLayout(1, 0,0,0));
		final JButton btnAdd = new JButton("Add a Car");
		mainPanel.setSize(400,400);
		mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Welcome to Rent a Car", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,24), Color.BLACK));
		
		JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
	//	detailPanel.setSize(390,390);
		detailPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Registered Cars:"));
		
		JLabel tableLable = new JLabel("to remove a car click on  the relative table row ", null, SwingConstants.LEFT);
		JButton b=new JButton("Check"); 	String col[] = {"Available Cars"};
		DefaultTableModel model = new DefaultTableModel( null,col );
		JTable table = new JTable(model);
		
        table.setPreferredScrollableViewportSize(new Dimension(300,60));
        table.setFillsViewportHeight(true);

        JScrollPane js=new JScrollPane(table);
        js.setVisible(true);
        //add(js);
		
		for (int i = 0; i < (carList.size() ); i++) {
		      model.addRow(new Object[] { String.valueOf(carList.get(i)),
		          String.valueOf(carList.get( i) ) });
		    }
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
               //tableLable.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
               int opcion = JOptionPane.showConfirmDialog(null, "Are you sure, do you want to remove the Car?", "Remove Car", JOptionPane.YES_NO_OPTION);
               if (opcion == 0) { 
            	   carService.removeCar(table.getValueAt(table.getSelectedRow(), 0).toString());
               } else {
               }


            }
        });
		
		 btnAdd.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
        		GUIBuilder.addCar(carService);
           }
       });
		//.setBorder( BorderFactory.createEmptyBorder(-5, 0, -5, 0) );
		detailPanel.add(tableLable);
		detailPanel.add(js);
		detailPanel.add(btnAdd);
		
		mainPanel.add(detailPanel);
		
		frame.add(mainPanel);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
	}
//		DT.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//               itemIDlbl.setText(DT.getValueAt(DT.getSelectedRow(), 0).toString());
//                 itemNamelbl.setText(DT.getValueAt(DT.getSelectedRow(), 1).toString());
//            }
//        });
		
	
	public static void addCar(CarService carService) {
		JFrame frame = new JFrame("Add New Car");
		frame.getContentPane().setLayout(new GridLayout(1, 0,50,50));
		JPanel wrapper = new JPanel(new GridLayout(0, 1,10,10));
		JPanel mainPanel = new JPanel( new FlowLayout(50, 30, FlowLayout.CENTER) );
		wrapper.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add new Car Details", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,24), Color.BLACK));
		
		
		JTextField modelText = new JTextField("Model");
	    JTextField regNoText = new JTextField("Reg No");
	    JTextField brandText = new JTextField("Brand");
	    JTextField typeText = new JTextField("Type");
	    JTextField priceText = new JTextField("1000.00");

	    modelText.setPreferredSize( new Dimension( 300, 24 ) );
	  	regNoText.setPreferredSize( new Dimension( 300, 24 ) );
	  	brandText.setPreferredSize( new Dimension( 300, 24 ) );
	  	typeText.setPreferredSize( new Dimension( 300, 24 ) );
      	priceText.setPreferredSize( new Dimension( 300, 24 ) );
      	JButton btnAdd = new JButton("Add a Car");
      	

      	
      	 btnAdd.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent event) {
            	  int opcion = JOptionPane.showConfirmDialog(null, "Are you sure, do you want to Add the Car?", "Add Car", JOptionPane.YES_NO_OPTION);
                  if (opcion == 0) { 
                    	String model = modelText.getText();
                      	String regNo = regNoText.getText();
                      	String brand = brandText.getText();
                      	String type = typeText.getText();
                      	double price = Double.parseDouble( priceText.getText() ) ;
                	  carService.addNewCar(model, regNo, brand, type, price);
               	   
                  } else {
                  }

             }
         });
      	
      
	  wrapper.add(modelText);
	  wrapper.add(brandText);
	  wrapper.add(regNoText);
	  wrapper.add(typeText);
	 // wrapper.add(priceText);
      wrapper.add(btnAdd);
	  
      wrapper.add(mainPanel);
      
      frame.add(wrapper);
      frame.setSize(400,400);
     // frame.getContentPane().add(priceText);
      frame.setVisible(true);
	}
	
	
	public static void AdministratorDriver(ArrayList<String> DriverList, DriverService driverService) {
		JFrame frame = new JFrame("Welcome to Administrator");
		frame.getContentPane().setLayout(new GridLayout(1, 0,50,50));
		JPanel mainPanel = new JPanel(new GridLayout(1, 0,0,0));
		final JButton btnAdd = new JButton("Add a Driver");
		mainPanel.setSize(400,400);
		mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Welcome to Rent a Car", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,24), Color.BLACK));
		
		JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
	//	detailPanel.setSize(390,390);
		detailPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Registered Drivers:"));
		
		JLabel tableLable = new JLabel("to remove a Driver click on  the relative table row ", null, SwingConstants.LEFT);
		JButton b=new JButton("Check"); 	String col[] = {"Registerd Drivers"};
		DefaultTableModel model = new DefaultTableModel( null,col );
		JTable table = new JTable(model);
		
        table.setPreferredScrollableViewportSize(new Dimension(300,60));
        table.setFillsViewportHeight(true);

        JScrollPane js=new JScrollPane(table);
        js.setVisible(true);
        //add(js);
		
		for (int i = 0; i < (DriverList.size() ); i++) {
		      model.addRow(new Object[] { String.valueOf(DriverList.get(i)),
		          String.valueOf(DriverList.get( i) ) });
		    }
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
               //tableLable.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
               int opcion = JOptionPane.showConfirmDialog(null, "Are you sure, do you want to remove the Driver?", "Remove Driver", JOptionPane.YES_NO_OPTION);
               if (opcion == 0) { 
            	   driverService.removeDriver(table.getValueAt(table.getSelectedRow(), 0).toString());
               } else {
               }


            }
        });
		
		 btnAdd.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
        		GUIBuilder.addDriver(driverService);
           }
       });
		//.setBorder( BorderFactory.createEmptyBorder(-5, 0, -5, 0) );
		detailPanel.add(tableLable);
		detailPanel.add(js);
		detailPanel.add(btnAdd);
		
		mainPanel.add(detailPanel);
		
		frame.add(mainPanel);
		frame.setSize(400, 400);
		frame.setVisible(true);
		

	}
	
	protected static void addDriver( DriverService driverService) {
		JFrame frame = new JFrame("Add New Driver");
		frame.getContentPane().setLayout(new GridLayout(1, 0,50,50));
		JPanel wrapper = new JPanel(new GridLayout(0, 1,10,10));
		JPanel mainPanel = new JPanel( new FlowLayout(50, 30, FlowLayout.CENTER) );
		wrapper.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add new Car Details", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,24), Color.BLACK));
		
		JTextField firstName = new JTextField("First Name");
	    JTextField lastName = new JTextField("Last Name");
	    JTextField nic = new JTextField("NIC No");
	    JTextField phoneNumber = new JTextField("Phone Number");
	    JTextField age = new JTextField("0");
	    


	    firstName.setPreferredSize( new Dimension( 300, 24 ) );
	    lastName.setPreferredSize( new Dimension( 300, 24 ) );
	    nic.setPreferredSize( new Dimension( 300, 24 ) );
	    phoneNumber.setPreferredSize( new Dimension( 300, 24 ) );
	    age.setPreferredSize( new Dimension( 300, 24 ) );
      	JButton btnAdd = new JButton("Add a Driver");
      
      	
      	 btnAdd.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent event) {
            	  int opcion = JOptionPane.showConfirmDialog(null, "Are you sure, do you want to Add the Driver?", "Add Driver", JOptionPane.YES_NO_OPTION);
                  if (opcion == 0) { 
              	    String fname = firstName.getText();
            	    String lName = lastName.getText();
            	    String NIC = nic.getText();
            	    String phone = phoneNumber.getText();
            	    int ageInt = Integer.parseInt(age.getText());
                	  driverService.addNewDriver(fname, lName, NIC, phone, ageInt);
               	   
                  } else {
                  }

             }
         });
      	
      
	  wrapper.add(firstName);
	  wrapper.add(lastName);
	  wrapper.add(nic);
	  wrapper.add(phoneNumber);
	  wrapper.add(age);
      wrapper.add(btnAdd);
	  
      wrapper.add(mainPanel);
      
      frame.add(wrapper);
      frame.setSize(400,400);
     // frame.getContentPane().add(priceText);
      frame.setVisible(true);
		
	}
	public static  void chooser(ArrayList<String> cars,ArrayList<String> drivers,CarService carService, DriverService driverService) {
		JFrame frame = new JFrame("Admin Portrel");
		frame.getContentPane().setLayout(new GridLayout(1, 0,50,50));
		JPanel wrapper = new JPanel(new GridLayout(0, 1,10,10));
		
		JButton btCar = new JButton("Car Section");
		JButton btnDriver = new JButton("Driver Section");
		
		wrapper.add(btCar);
		wrapper.add(btnDriver);
		
		btCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	GUIBuilder.AdministratorCar(cars,carService);
            }
        });
		
		btnDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	GUIBuilder.AdministratorDriver(drivers, driverService);
            }
        });
		 frame.add(wrapper);
	     frame.setSize(400,400);
	     frame.setVisible(true);
		
		
	}
	
//	public static void main(String[] Args) {
//			ArrayList<String[]> list = new ArrayList<String[]>();
//			
//			list.addAll();
//			list.add("aaaa");
//			list.add("aaaa");
//			list.add("aaaa");
//			list.add("aaaa");
//			//GUIBuilder.Administrator(list);
//			GUIBuilder.chooser(list,list);
//		}
		
	}

