package topia.com.prac.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok동작이  들쑥날쑥해서   @Data주석하고  getter,setter수동생성
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoQualifi {
	private Integer qualifiIdx;
	private Integer userIdx;
	private String qualifiName;
	private Date qualifiGetdate;
	
	public Integer getQualifiIdx() {
		return qualifiIdx;
	}
	public void setQualifiIdx(Integer qualifiIdx) {
		this.qualifiIdx = qualifiIdx;
	}
	public Integer getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(Integer userIdx) {
		this.userIdx = userIdx;
	}
	public String getQualifiName() {
		return qualifiName;
	}
	public void setQualifiName(String qualifiName) {
		this.qualifiName = qualifiName;
	}
	public Date getQualifiGetdate() {
		return qualifiGetdate;
	}
	public void setQualifiGetdate(Date qualifiGetdate) {
		this.qualifiGetdate = qualifiGetdate;
	}
	
}
