package topia.com.prac.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserInfoHealth {
	Integer Useridx;
	String HealthBlood;
	String HealthWeight;
	String HealthHight;
	String HealthEyeLeft;
	String HealthEyeRight;
	
	public Integer getUseridx() {
		return Useridx;
	}
	public void setUseridx(Integer useridx) {
		Useridx = useridx;
	}
	public String getHealthBlood() {
		return HealthBlood;
	}
	public void setHealthBlood(String healthBlood) {
		HealthBlood = healthBlood;
	}
	public String getHealthWeight() {
		return HealthWeight;
	}
	public void setHealthWeight(String healthWeight) {
		HealthWeight = healthWeight;
	}
	public String getHealthHight() {
		return HealthHight;
	}
	public void setHealthHight(String healthHight) {
		HealthHight = healthHight;
	}
	public String getHealthEyeLeft() {
		return HealthEyeLeft;
	}
	public void setHealthEyeLeft(String healthEyeLeft) {
		HealthEyeLeft = healthEyeLeft;
	}
	public String getHealthEyeRight() {
		return HealthEyeRight;
	}
	public void setHealthEyeRight(String healthEyeRight) {
		HealthEyeRight = healthEyeRight;
	}
	
	public UserInfoHealth(Integer useridx, String healthBlood, String healthWeight, String healthHight,
			String healthEyeLeft, String healthEyeRight) {
	
		Useridx = useridx;
		HealthBlood = healthBlood;
		HealthWeight = healthWeight;
		HealthHight = healthHight;
		HealthEyeLeft = healthEyeLeft;
		HealthEyeRight = healthEyeRight;
	}
	
	public UserInfoHealth() {
		
	}
}
