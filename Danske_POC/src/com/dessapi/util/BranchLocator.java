package com.dessapi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.dessapi.bean.BranchLocatorBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.MappingStrategy;

public class BranchLocator {

	private List<BranchLocatorBean> csvTOList() throws IOException{

		URL url = getClass().getResource("Danske_Branch_Atm.csv");

		CSVReader reader = new CSVReader(new FileReader(url.getPath()), ',');

		List<BranchLocatorBean> blbl = new ArrayList<BranchLocatorBean>();

		String[] record = null;

		reader.readNext();

		while((record = reader.readNext()) != null){
			BranchLocatorBean blb = new BranchLocatorBean();
			blb.setName(record[0]);
			blb.setAddress(record[1]);
			blb.setZipcode(record[2]);
			blb.setCity(record[3]);
			blb.setPhoneNumber(record[4]);
			blb.setType(record[5]);

			blbl.add(blb);
		}

		reader.close();

		return blbl;


	}


	
		
	
	public List<BranchLocatorBean> findBranch(String zipCode,String type) throws IOException{


		List<BranchLocatorBean> branchLocationList = new ArrayList<BranchLocatorBean>();


		Iterator<BranchLocatorBean> itr = this.csvTOList().iterator();

		while (itr.hasNext()) {
			BranchLocatorBean branchLocatorBean = (BranchLocatorBean) itr.next();

			if(branchLocatorBean.getZipcode().equalsIgnoreCase(zipCode) )
			{
				if(type.equalsIgnoreCase("ATM") && branchLocatorBean.getType().equalsIgnoreCase("ATM"))
				{
					branchLocationList.add(branchLocatorBean);	
				}else if(type.equalsIgnoreCase("Branch") && branchLocatorBean.getType().equalsIgnoreCase("Branch"))
				{

					branchLocationList.add(branchLocatorBean);
				}else if(type.equalsIgnoreCase("Both"))
				{
					branchLocationList.add(branchLocatorBean);
				}


			}else if(zipCode.equalsIgnoreCase("All"))
			{
				if(type.equalsIgnoreCase("ATM") && branchLocatorBean.getType().equalsIgnoreCase("ATM"))
				{
					branchLocationList.add(branchLocatorBean);	
				}else if(type.equalsIgnoreCase("Branch") && branchLocatorBean.getType().equalsIgnoreCase("Branch"))
				{

					branchLocationList.add(branchLocatorBean);
				}else if(type.equalsIgnoreCase("Both"))
				{
					branchLocationList.add(branchLocatorBean);
				}
			}
		}


		return branchLocationList;
	}


}

































