package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import exceptions.ContactException;

/**
 * Class used to represent all of the entries in the agenda. Each of the entries in this agenda is a student. Stores general info of them.
 * @author Jhon Edward Mora - A00355710 - Universidad ICESI
 * @version 1.0 of February/2020
 */
public class Student {

	//Attributes
	/**Name of this student.*/
	private String name;
	
	/**Personal address of this contact.*/
	private String address;
	
	/**Personal phone number of this contact.*/
	private String phoneNumber;
	
	/**Student code of this contact.*/
	private String code;
	
	/**Number of the semester this student is currently coursing.*/
	private int semester;
	
	/**Age of this student, in years.*/
	private int age;
	
	/**Career this student is on.*/
	private String career;
	
	/**URL to the picture this student is identifying himself with.*/
	private String picture;
	
	/**The birth date of this student.*/
	private String birthdate;
	
	/**The email of this student.*/
	private String email;
	
	//Relations
	/**Represents the courses this student is enrolled in.*/
	private List<Course> courses;
	
	//Methods
	/**
	 * Constructor method. Initializes an instance of class Student given the information. This information may be empty or non-existent.
	 * @param n The name of the student.
	 * @param p The phone number of the student.
	 * @param e the email
	 * @param b A String representation of the student's birth date.
	 * @param a The address of the student.
	 * @param c The code of the student.
	 * @param s The semester the student is in.
	 * @param cr The career the student is in.
	 * @param pc The URL to the student's picture.
	 * @throws ContactException 
	 */
	public Student(String n, String p, String e, String b, String a, String c, String s, String cr, String pc) throws ContactException {
		if(n == null) {
			throw new ContactException(ContactException.name);
		}
			else
			name = n;
		if(p == null) {
			throw new ContactException(ContactException.number);
		}
		else
			phoneNumber = p;
		if(a == null)
			address = "";
		else
			address = a;
		if(c == null)
			code = "";
		else
			code = c;
		if(s.isEmpty())
			semester = 0;
		else
			semester = Integer.parseInt(s);
		if(cr == null)
			career = cr;
		else
			career = cr;
		if(pc == null)
			picture = "https://www.sackettwaconia.com/wp-content/uploads/default-profile.png";
		else
			picture = pc;
		if(b == null)
			birthdate = null;
		else
			calculateBirthdate(b);
		if(e==null)
			email = "";
		else
			email = e;
		courses = new ArrayList<Course>();
	}
	
	/**
	 * Creates a LocalDate with the birth date of this student and assigns it to the <code>birthdate</code> field.
	 * @param b A String representation of the birth date of this student in the format "DD/MM/YYYY"
	 */
	private void calculateBirthdate(String b) {
		birthdate = b;
//		String[] parts = b.split("/");
//		int days = Integer.parseInt(parts[0]);
//		int months = Integer.parseInt(parts[1]);
//		int year = Integer.parseInt(parts[2]);
//		LocalDate l = LocalDate.of(year, months, days);
//		birthdate = l;
//
//		if(birthdate != null)
//			age = Period.between(birthdate, LocalDate.now()).getYears();
//		else
//			age = 0;
	}
	
	public int creditsNumber() {
		int count = 0;
		
		for(Course course : courses)
			count += course.getCredits();
		
		return count;
	}
	
	public void deleteCourse(Course courseToDelete) {
		courses.remove(courseToDelete);		
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", code=" + code
				+ ", semester=" + semester + ", age=" + age + ", career=" + career + ", picture=" + picture
				+ ", birthdate=" + birthdate + ", email=" + email + ", courses=" + courses + "]";
	}

	/**Returns the given name of this student.
	 * @return The value of the field name. May be an empty field, but never null.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the address of this student.
	 * @return The value of the field address. May be an empty field, but never null.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Returns the phone number of this student.
	 * @return The value of the field phoneNumber. May be an empty field, but never null.
	 */
	public String phoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Returns the unique student code of this student.
	 * @return The value of the field student. May be an empty field, but never null.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Returns the semester this student is currently in.
	 * @return The value of the field semester. Values of 0 indicate empty/non valid fields.
	 */
	public int getSemester() {
		return semester;
	}
	
	/**
	 * Returns the time in years that has passed since the student's birth.
	 * @return The value of the field age. Values of 0 indicate empty/non valid fields.
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Returns the career this student is coursing.
	 * @return The value of the field career. May be an empty value, but never null.
	 */
	public String getCareer() {
		return career;
	}
	
	/**
	 * Returns the URL where the student's picture is in.
	 * @return The value of the field picture. When this field is empty, a default picture URL is assigned.
	 */
	public String getPictureURL() {
		return picture;
	}
	
	/**
	 * Returns the birth date of this student.
	 * @return Either a birthday in the format "DD of MM", or an empty string if the birthdate value is empty.
	 */
	public String getBirthdate() {
return birthdate;
		//		if(birthdate != null) {
//			int days = birthdate.getDayOfMonth();
//			String month = birthdate.getMonth().name();
//			return days + " of " + month;
//		}else {
//			return "";
//		}
	}
	
	/**
	 * Returns the list of courses this student is enrolled in.
	 * @return The list element of the field courses.
	 */
	public List<Course> getCourses(){
		return courses;
	}
	
	/**
	 * Returns the number of credits this student has enrolled.
	 * @return The sum of all credits in each Course that exist in the list <code>courses</code>
	 */
	public int getCredits() {
		int r = 0;
		for(Course c:courses) {
			r += c.getCredits();
		}
		return r;
	}

	/**
	 * Returns the email this student has registered. 
	 * @return The value of field email. If this value was not given, an empty string will be used instead.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the name of this student to the given as parameter.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the address of this student to the given as parameter.
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Sets the phone number to the one given as parameter.
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Sets the code of this student to the one given as parameter.
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Sets the semester this student is to the one given as parameter. Only numbers over 0 are valid, invalid values are set to default value (1).
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		if(semester <0) {
			this.semester = 1;
		}else {
			this.semester = semester;
		}
	}

	/**
	 * Sets the career of this student to the one given as parameter.
	 * @param career the career to set
	 */
	public void setCareer(String career) {
		this.career = career;
	}

	
	/**Sets the URL of this picture to the one given as parameter.
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**Sets the birth date of this student to the one in parameter.
	 * @param birthdate A String representation of this student's birth date in the format "DD/MM/YYYY"
	 */
	public void setBirthdate(String birthdate) {
		calculateBirthdate(birthdate);
	}

	/**
	 * Sets the email of this student to the one in parameter.
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Registers this student in the given course.
	 * @param c The given course for this student to be registered in.
	 */
	public void addClass(Course c) {
		courses.add(c);
	}

	/**
	 * Converts all of the information pertinent to this student's info into a single String to be stored in a external persistent file.
	 * @return A String representation of the information in the following format:<br>
	 * name;phoneNumber;email;birthday;address;code;semester;career;photo;courses<br>
	 * Field birthday is in the format (DD-MM-YYYY)<br>
	 * Courses field stores each of the student's enrolled course's NRC, such as 10112/129412/12341/
	 */
	public String persist() {
		//Header = name;phoneNumber;email;birthday(DD-MM-YYYY);address;code;semester;career;photo;course1/course2
		String ret = "";
		//First fields
		ret+=name+";"+phoneNumber+";"+email+";";
		//Birth date field
		ret+=birthdate+";";
//		ret+=birthdate.getDayOfMonth()+"/"+birthdate.getMonthValue()+"/"+birthdate.getYear()+";";
		//Last fields
		ret+=address+";"+code+";"+semester+";"+career+";"+picture+";";
		//Courses
		
		if(courses.size() > 0) {			
			for(Course c:courses) {
				ret+=c.getNRC()+",";
			}
		}else
			ret+= " ";
		
		return ret;
	}
	
	/**
	 * Deletes this student from any course it has been enrolled before.
	 */
	public void unenroll() {
		for(Course c : courses) {
			c.unenroll(this);
		}
	}
}
