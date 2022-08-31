package topia.com.prac.entity;



public class Page {

	// 현재페이지
	// 페이지당 게시물
	// 페이지 블럭수
	int curPage =1;
	int perPage = 10;
	int perBlock = 10;
	
	// 전체페이지
	// 게시물 시작번호
	// 게시물 끝번호
	// 블럭 시작페이지
	// 블럭 끝페이지
	
	int totPage;
	int startNum;
	int endNum;
	int startPage;
	int endPage;

	int totcnt;
	




	String findkey = "";
	String findvalue="";
	String findcareer="";


	String genderSelect = ""; // 경력
	//String userInfoDataSize: "10"
	String userMaritalStatusSelect = ""; // 결혼유무
	
	

	public int getTotcnt() {
		return totcnt;
	}
	
	
	
	
	public void setTotcnt(int totcnt) {
		this.totcnt = totcnt;
	}

	public int getCurPage() {
		return curPage;
	}




	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}




	public int getPerPage() {
		return perPage;
	}




	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}




	public int getPerBlock() {
		return perBlock;
	}




	public void setPerBlock(int perBlock) {
		this.perBlock = perBlock;
	}




	public int getTotPage() {
		return totPage;
	}




	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}




	public int getStartNum() {
		return startNum;
	}




	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}




	public int getEndNum() {
		return endNum;
	}




	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}




	public int getStartPage() {
		return startPage;
	}




	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}




	public int getEndPage() {
		return endPage;
	}




	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}




	public String getFindkey() {
		return findkey;
	}




	public void setFindkey(String findkey) {
		this.findkey = findkey;
	}




	public String getFindvalue() {
		return findvalue;
	}




	public void setFindvalue(String findvalue) {
		this.findvalue = findvalue;
	}




	public String getFindcareer() {
		return findcareer;
	}




	public void setFindcareer(String findcareer) {
		this.findcareer = findcareer;
	}




	public String getGenderSelect() {
		return genderSelect;
	}




	public void setGenderSelect(String genderSelect) {
		this.genderSelect = genderSelect;
	}




	public String getUserMaritalStatusSelect() {
		return userMaritalStatusSelect;
	}




	public void setUserMaritalStatusSelect(String userMaritalStatusSelect) {
		this.userMaritalStatusSelect = userMaritalStatusSelect;
	}




	@Override
	public String toString() {
		return "Page [curPage=" + curPage + ", perPage=" + perPage + ", perBlock=" + perBlock + ", totPage=" + totPage
				+ ", startNum=" + startNum + ", endNum=" + endNum + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", findkey=" + findkey + ", findvalue=" + findvalue + ", findcareer=" + findcareer + ", genderSelect="
				+ genderSelect + ", userMaritalStatusSelect=" + userMaritalStatusSelect + "]";
	}




	public Page() {
		
	}
	
}
