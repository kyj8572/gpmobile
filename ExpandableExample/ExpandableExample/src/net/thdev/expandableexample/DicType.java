package net.thdev.expandableexample;

import java.util.List;

public class DicType {
	String Type;
	List<DicName> Array;
	
	public DicType(String type, List<DicName> array) {
		Type = type;
		Array = array;
	}
}
