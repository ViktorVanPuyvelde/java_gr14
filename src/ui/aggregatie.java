package ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import gui.NieuweDatasourcePaneelController;
import gui.tempMvoData;

public class aggregatie {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NieuweDatasourcePaneelController d = new NieuweDatasourcePaneelController();
		ArrayList<tempMvoData> al = new ArrayList<>();
		ArrayList<tempMvoData> al2 = new ArrayList<>();
		al.add(new tempMvoData(4.0,new Date(),1.0));
		al.add(new tempMvoData(4.0,new Date(),1.0));
		al.add(new tempMvoData(4.0,new Date(),1.0));
		al2.add(new tempMvoData(2.0,new Date(),1.0));
		al2.add(new tempMvoData(2.0,new Date(),1.0));
		HashMap<Double,List<tempMvoData>> hm = new HashMap<>();
		hm.put(1.0, al );
		hm.put(2.0, al2);
		
		
		d.verwerkDataAlsSom(hm);
		d.verwerkDataAlsGem(hm);
	}

}
