package edu.miu.cs.cs425.demo.studentsmgmtapp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.miu.cs.cs425.demo.studentsmgmtapp.model.Students;

/**
 * Hello world!
 *
 */

public class MyStudentsRecordsMgmtApp {
	public static void main(String[] args) {

		Students[] students = { new Students(110001, "Dave", LocalDate.of(1951, 11, 18)),
				new Students(110002, "Anna", LocalDate.of(1990, 12, 07)),
				new Students(110003, "Erica", LocalDate.of(1974, 01, 31)),
				new Students(110004, "Carlos", LocalDate.of(2009, 8, 22)),
				new Students(110005, "Bob", LocalDate.of(1990, 05, 03)) };

		System.out.println();
		System.out.println("*****List of Students *****");
		printListOfStudents(students);
		System.out.println("");
		
		
		  System.out.println(); System.out.
		  println("****PlatinumAlumni student is a student who gained admission into the University at least 30 years ago****"
		  ); System.out.println(); List<Students>
		  studs=PrintListOfPlatinumAlumniStudents(students);
		  studs.forEach(System.out::println);
		 

		System.out.println("***Multiple of 5 and 7 :- ***");
		int[] test = { 50, 28, 35, 25, 30, 14 };
		printHelloWorld(test);

		int[] arr = { 1, 2, 3, 4, 5 };
		int[] arr1 = { 19, 9, 11, 0, 12 };
		System.out.println("*****Second Highest number*****");
		findSecondBiggest(arr);
		findSecondBiggest(arr1);

	}

	public static void printListOfStudents(Students[] stu) {
		Arrays.stream(stu).forEach(System.out::println);

	}

	public static List<Students> PrintListOfPlatinumAlumniStudents(Students[] students) {
		return Arrays.stream(students)
				.filter(x -> (LocalDate.now().getYear()) - (x.getDateOfAdmission().getYear()) > 30)
				.sorted(Comparator.comparing(Students::getDateOfAdmission)).collect(Collectors.toList());

	}

	public static void printHelloWorld(int[] array) {

		for (int i = 0; i < array.length; i++)
			if (array[i] % 5 == 0 && array[i] % 7 == 0) {
				System.out.println("Hello World");
			} else if (array[i] % 5 == 0) {
				System.out.println("Hello");
			} else if (array[i] % 7 == 0) {
				System.out.println("World");
			}

	}

	private static void findSecondBiggest(int[] arr) {
		int temp, size;
		size = arr.length;

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {

				if (arr[i] > arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println("The second largest number is: " + arr[size - 2]);
	}

}
