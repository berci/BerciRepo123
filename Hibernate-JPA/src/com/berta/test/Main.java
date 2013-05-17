package com.berta.test;

import java.util.ArrayList;
import java.util.List;

import com.berta.test.dao.DAOManager;
import com.berta.test.entity.Event;
import com.berta.test.entity.br.EventOperations;

public class Main {

	public static void main(String[] args) {
		
		// init
		DAOManager.getDAOManager();
		
		System.out.println();
		List<Event> all = new ArrayList<Event>();
		Event event = null;
		try {
			all = new EventOperations().getAll();
			event = new EventOperations().getItemByName("zzCL_Info");
			
			event = new EventOperations().getItemByName("zzCL_Error");
			
			event = new EventOperations().getItemByName("zzCL_Warning");
			
			event = new EventOperations().getItemByName("zzCL_DPResponse");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
