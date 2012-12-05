package com.itesm.movil.models;

import java.util.ArrayList;

public class Course {

	private String code;
	private String group;
	private String name;
	private String p1;
	private String p2;
	private String p3;
	private String fnl;
	private String f1;
	private String f2;
	private String f3;
	private String f4;
	private ArrayList<Professor> Professors;
	private boolean newData = false;

	public Course() {

	}

	public Course(String code, String group, String name, String p1, String p2,
			String p3, String fnl, String f1, String f2, String f3, String f4,
			ArrayList<Professor> Professors) {
		super();
		this.code = code;
		this.group = group;
		this.name = name;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.fnl = fnl;
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
		this.f4 = f4;
		this.Professors = Professors;
	}

	public ArrayList<Professor> getProfessors() {
		return Professors;
	}

	public void setProfessors(ArrayList<Professor> Professors) {
		this.Professors = Professors;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getFnl() {
		return fnl;
	}

	public void setFnl(String fnl) {
		this.fnl = fnl;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF4() {
		return f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}

	/** Calculates the average of a group of grades given an array of strings
	 * @param grades 
	 * @return average (sum/count).
	 */
	public static int calculateAverage(String[] grades) {

		double count = 0;
		double sum = 0;

		for (int i = 0; i < grades.length; i++) {
			if (isWord(grades[i])) {
			} else {
				if (Double.parseDouble(grades[i]) != 0) {
					count++;
				}
				sum += Integer.parseInt(grades[i]);
			}

		}

		return (int) (sum / count);

	}

	/**
	 * Gets the total number of absences during the semester
	 * @param f1 first period absences
	 * @param f2 second period absences
	 * @param f3 third period absences
	 * @param f4 fourth period in case the campus has 3 midterms for the period between 3rd midterm and finals
	 * @return 
	 * 		total number of absences
	 */
	public static int fTotal(String f1, String f2, String f3, String f4) {

		int F1 = Integer.parseInt(f1);
		int F2 = Integer.parseInt(f2);
		int F3 = Integer.parseInt(f3);
		int F4 = Integer.parseInt(f4);

		return F1 + F2 + F3 + F4;
	}

	public boolean isNewData() {
		return newData;
	}

	public void setNewData(boolean newData) {
		this.newData = newData;
	}

	/**
	 * 
	 * Checks if grade is a word such as SC, NP, IN
	 * 
	 * @param word
	 *            grade to check if it is in the dictionary.
	 * @return true if word in dictionary. false. if word it's not in dictionary
	 * 
	 * */

	public static boolean isWord(String word) {

		String[] dictionary = { "A", "CP", "SD", "NA", "DA", "SC", "NP", "IN" };
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets the last grade available.
	 * 
	 * @param grades
	 *            array of grades to be checked.
	 * 
	 * @return <b>current</b> current period grade.
	 * 
	 * */

	public String getCurrent(String[] grades) {

		String current = null;
		String[] periodsNames = { "P1", "P2", "P3" };

		for (int i = 0; i < grades.length; i++) {
			// current = grades[i];
			if (!grades[i].equals("0")) {
				current = periodsNames[i];
			}

		}

		return current;

	}

	/**
	 * 
	 * Creates a sum of the grades taking into consideration if there's a word.
	 * 
	 * */

	public int lameChecksum(String[] grades) {

		int result = 0;

		for (int i = 0; i < grades.length; i++) {
			if (isWord(grades[i])) {
			} else {
				if (Double.parseDouble(grades[i]) != 0) {
					result += Integer.parseInt(grades[i]);
				}

			}
		}

		return result;
	}

	public String[] gradesArray() {

		String [] grades = new String[3];
		grades[0] = getP1();
		grades[1] = getP2();
		grades[2] = getP3();
		return grades;
	}

}
