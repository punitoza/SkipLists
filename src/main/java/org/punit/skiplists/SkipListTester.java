package org.punit.skiplists;

public class SkipListTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SkipList list = new SkipList();
		list.insert(10);
		list.insert(5);
		list.insert(20);
		list.insert(100);
		list.insert(1);
		System.out.println(list.search(5));
		list.delete(5);
		System.out.println(list.search(5));
		
		System.out.println(list.search(10));
		list.delete(10);
		System.out.println(list.search(10));
		
		System.out.println(list.search(20));
		list.delete(20);
		System.out.println(list.search(20));
		
		System.out.println(list.search(100));
		list.delete(100);
		System.out.println(list.search(100));
		
		list.insert(111);
		System.out.println(list.search(111));
		
		System.out.println(list.search(1));
		list.delete(1);
		System.out.println(list.search(1));
	}

}
