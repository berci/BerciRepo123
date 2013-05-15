package com.camline.xml.equipment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.camline.xml.equipment.Equipments.Equipment;
import com.camline.xml.equipment.Parameters.Parameter;
import com.camline.xml.jaxb.EquipmentConfig;
import com.camline.xml.jaxb.EquipmentType;
import com.camline.xml.jaxb.ParamType;

public class EquipmentTest {
	
	private static final Map<String, Equipment> eqs = new HashMap<String, Equipment>();
	
	public static void main(String[] args) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("com.camline.xml.jaxb");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			EquipmentConfig equipmentConfig = (EquipmentConfig) unmarshaller.unmarshal(new FileInputStream("xml/equipmentConfig.xml"));
			
			for(EquipmentType eq : equipmentConfig.getEquipment()) {
				Map<String, Parameter> params = new HashMap<String, Parameter>();
				for(ParamType param : eq.getParam()) {
					params.put(param.getKey(), new Parameter(param.getName(), param.getUnit()));
				}
				eqs.put(eq.getId(), new Equipment(eq.getName(), params));
			}
			
			System.out.println();
		} catch (JAXBException  e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
