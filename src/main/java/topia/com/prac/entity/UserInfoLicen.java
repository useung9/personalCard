package topia.com.prac.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok동작이  들쑥날쑥해서   @Data주석하고  getter,setter수동생성
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoLicen {
	private Integer licenIdx;
	private Integer UserIdx;
	private String licenName;
	private String licenSkillLevel;
	
	public Integer getLicenIdx() {
		return licenIdx;
	}
	public void setLicenIdx(Integer licenIdx) {
		this.licenIdx = licenIdx;
	}
	public Integer getUserIdx() {
		return UserIdx;
	}
	public void setUserIdx(Integer userIdx) {
		UserIdx = userIdx;
	}
	public String getLicenName() {
		return licenName;
	}
	public void setLicenName(String licenName) {
		this.licenName = licenName;
	}
	public String getLicenSkillLevel() {
		return licenSkillLevel;
	}
	public void setLicenSkillLevel(String licenSkillLevel) {
		this.licenSkillLevel = licenSkillLevel;
	}
	
}
