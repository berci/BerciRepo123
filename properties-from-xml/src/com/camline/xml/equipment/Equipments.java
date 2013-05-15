package com.camline.xml.equipment;

import java.util.HashMap;
import java.util.Map;

import com.camline.xml.equipment.Parameters.Parameter;

/**
 * @author marton
 *
 */
public class Equipments {
	
	private static final Map<String, Equipment> eqs = new HashMap<String, Equipment>();
	
	/**
	 * Initialize Equipments with specific Space parameters
	 */
	public static void init() {
		Map<String, Parameter> params = new HashMap<String, Parameter>();
		params.put("[1]", new Parameter("Ar", "sccm"));
		params.put("[2]", new Parameter("CF4", "sccm"));
		params.put("[3]", new Parameter("O2", "sccm"));
		params.put("[4]", new Parameter("CL2", "sccm"));
		params.put("[5]", new Parameter("HBr", "sccm"));
		params.put("[6]", new Parameter("SF6", "sccm"));
		params.put("[7]", new Parameter("C4F8", "sccm"));
		eqs.put("36543", new Equipment("STS Multiplex HP (Al-Kammer)", params));
		
		params = new HashMap<String, Parameter>();
		params.put("[1]", new Parameter("C4F8", "sccm"));
		params.put("[2]", new Parameter("SF6", "sccm"));
		params.put("[3]", new Parameter("O2", "sccm"));
		params.put("[4]", new Parameter("CF4", "sccm"));
		eqs.put("20643", new Equipment("STS Multiplex ICP (Si-Kammer)", params));
	}
	
	static class Equipment {
		
		private String name;
		Map<String, Parameter> params;
		
		public Equipment(String name, Map<String, Parameter> params) {
			this.name = name;
			this.params = params;
		}
		
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * @return the params
		 */
		public Map<String, Parameter> getParams() {
			return params;
		}
		
	}
	
}