
package test.java;

import java.io.IOException;
import java.util.*;

import main.java.PhoneDirectory;

import java.io.*;
import java.lang.*;

public class main {
	public static void main(String[] args) {
		PhoneDirectory phone = new PhoneDirectory();
		phone.addEntry("Sam", "2022038441");
		phone.addEntry("Anuraaag", "2022038552");
		phone.changeEntry("Anuraag", "3734480009");
		phone.addEntry("Ashok", "2022038441");
		phone.getNumber("Ashok");
		phone.deleteEntry("Sam");
		

	}
}
