package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

public class People{
		
		String name = null; 
		String adress = null;
		int id;
		
		public People(String name, String adress, int id){
			this.name = name;
			this.adress = adress;
			this.id = id;
		}
		public String getAdress() {
			return adress;
		}
		
		public String getName() {
			return name;
		}
		
		public int getId() {
			return id;
		}
		
		public static List<People> sortName(List<People> peoples) {
			Comparator<People> c = new NameComparator();
			
			Collections.sort(peoples, c);
			
			return peoples;
		}
		
		public static List<People> sortId(List<People> peoples) {
			Comparator<People> c = new IdComparator();
			
			Collections.sort(peoples, c);
			
			return peoples;
		}
		
		
		public static class NameComparator implements Comparator<People> {
			@Override
			public int compare(People s1, People s2) {
				return (s1.name.compareTo(s2.name));
			}
		}
		
		public static class IdComparator implements Comparator<People> {
			@Override
			public int compare(People s1, People s2) {
				return (s1.id - s2.id);
			}
		}
		
	public static void main(String[] args) {
		
		People[] t1 = {
				new People("aaa", "wwww", 0),
				new People("zzz", "wwww", 23),
				new People("vvv", "wwww", 1),
				new People("bbb", "wwww", 10)
			};
		
		// array -> list
		List<People> names = Arrays.asList(t1);
		
		// sort Name
		List<People> sortedPeopleName = People.sortName(names);
		for(People people : sortedPeopleName){
			System.out.println(people.getName());
		}
		
		// sort Id
		List<People> sortedPeopleId = People.sortId(names);
		for(People people : sortedPeopleId){
			System.out.println(people.getId() + " - " + people.getName());
		}
		
	}
	

}
