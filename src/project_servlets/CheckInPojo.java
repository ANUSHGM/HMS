package project_servlets;

public class CheckInPojo {
	String roomNo;
	String fullName;
	String age;
	String mobileNumber;
	String payment;
	public CheckInPojo() {
	
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "CheckInPojo [roomNo=" + roomNo + ", fullName=" + fullName + ", age=" + age + ", mobileNumber="
				+ mobileNumber + ", payment=" + payment + "]";
	}
	
	
	
	
	
	


}
