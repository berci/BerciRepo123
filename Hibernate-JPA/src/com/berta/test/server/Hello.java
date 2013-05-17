package com.berta.test.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.berta.api.jaxb.EventType;
import com.berta.api.jaxb.GetAllEventRequest;
import com.berta.api.jaxb.GetAllEventResponse;
import com.berta.server.impl.HelloImpl;
import com.berta.test.entity.Event;
import com.berta.test.entity.br.EventOperations;

public class Hello extends HelloImpl {

	private static final long serialVersionUID = 1L;

	public Hello() throws RemoteException {
		super();
	}
	
	@Override
	public Serializable xmlCall(Serializable in) {
		Serializable response = null;
		try {
			if(in instanceof GetAllEventRequest) {
				
				List<Event> all = new EventOperations().getAll();
				List<EventType> allType = new ArrayList<EventType>();
				for(Event e : all) {
					EventType et = new EventType();
					et.setName(e.getEventName());
					allType.add(et);
				}
				response = new GetAllEventResponse();
				((GetAllEventResponse) response).getEvents().addAll(
						allType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
		
	}
}
