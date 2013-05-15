package com.camline.xml.equipment;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.camline.xml.equipment.Equipments.Equipment;

/**
 * @author marton
 *
 */
public class Parameters {
	
	public static final String TIME = "Time";
	public static final String PROCESS_PHASE = "Process_Phase";
	private static Map<String, Parameter> params = null;
	
	/**
	 * Initialize Space parameters
	 * @param equipment 
	 */
	public static void init(Equipment equipment) {
		params = new HashMap<String, Parameter>();
		
		// Equipment specific Space Parameters
		//params.putAll(equipment.getParams());
		
		// Raw value
		Parameter p1 = new Parameter(TIME, null);
		p1.setRawValue(true);
		params.put("Time", p1);
		
		// Space parameters
		params.put("Pressure", new Parameter("Pressure", "mTorr"));
		params.put("RF1 Power", new Parameter("RF1_Power", "W"));
		params.put("RF1 Reflected", new Parameter("RF1_Reflected", "W"));
		params.put("RF Peak", new Parameter("RF_Peak", "U"));
		params.put("RF Bias", new Parameter("RF_Bias", "U"));
		params.put("RF1 Load", new Parameter("RF1_Load", "%"));
		params.put("RF1 Tune", new Parameter("RF1_Tune", "%"));
		params.put("RF2 Power", new Parameter("RF2_Power", "W"));
		params.put("RF2 Reflected", new Parameter("RF2_Reflected", "W"));
		params.put("RF2 Load", new Parameter("RF2_Load", "%"));
		params.put("RF2 Tune", new Parameter("RF2_Tune", "%"));
		params.put("APC Angle", new Parameter("APC_Angle", "%"));
		params.put("EPD Phase", new Parameter("EPD_Phase", "%"));
		params.put("EPD Value", new Parameter("EPD_Value", "%"));
		params.put("He Leakup Rate", new Parameter("He_Leakup_Rate", "mTorr/min"));
		params.put("He Pressure", new Parameter("He_Pressure", "mTorr"));
		params.put("He Flow", new Parameter("He_Flow", "sccm"));
		
		// Raw value
		p1 = new Parameter(PROCESS_PHASE, null);
		p1.setRawValue(true);
		params.put("Process Phase", p1);
	}
	
	/**
	 * @param name
	 * @return the equipment
	 */
	public static Parameter getParamter(String column) {
		int start = column.indexOf("[");
		int end = column.indexOf("]");
		if(start > -1 && end > 1) {
			column = column.substring(column.indexOf("["), column.indexOf("]") + 1);
		}
		return params.get(column);
	}
	
	/**
	 * @return the params
	 */
	public static Map<String, Parameter> getParams() {
		return params;
	}

	static class Parameter {
		
		private boolean rawValue = false;
		private Vector<Object> values = null;
		private String name;
		private String unit;
		
		public Parameter(String name, String unit) {
			this.name = name;
			this.unit = unit;
			values = new Vector<Object>();
		}
		
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * @return the unit
		 */
		public String getUnit() {
			return unit;
		}
		
		public void addValue(Object value) {
			values.add(value);
		}
		
		/**
		 * @return the rawValue
		 */
		public boolean isRawValue() {
			return rawValue;
		}
		
		/**
		 * @param rawValue the rawValue to set
		 */
		public void setRawValue(boolean rawValue) {
			this.rawValue = rawValue;
		}
		
		/**
		 * @return the values
		 */
		public Vector<Object> getValues() {
			return values;
		}
		
	}
	
}