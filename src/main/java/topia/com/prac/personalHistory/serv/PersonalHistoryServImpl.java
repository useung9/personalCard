package topia.com.prac.personalHistory.serv;

import java.io.*;
import java.security.KeyStore.Entry;

import org.apache.poi.POIDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.*;

import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.*;

import topia.com.prac.*;
import topia.com.prac.entity.*;
import topia.com.prac.personalHistory.dao.*;
import topia.com.util.*;

@Service
public class PersonalHistoryServImpl implements PersonalHistoryServ {

	@Autowired
	AbstractDAO dbCon;
	@Autowired
	private UserInfoCareerDao cDao;
	@Autowired
	private UserInfoEduDao eDao;
	@Autowired
	private UserInfoLicenDao lDao;
	@Autowired
	private UserInfoQualifiDao qDao;
	@Autowired
	private UserInfoSkillDao sDao;
	@Autowired
	private UserInfoTrainingDao tDao;
	@Autowired
	private UserInfoHealthDao hdao;
	@Autowired
	private ImageDao imgDao;
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Override
	/**
	 * 湲곗〈 �벑濡앸맂 媛쒖씤 �씠�젰移대뱶 媛��졇�삤湲�
	 */
	public HashMap<String, Object> userList(HashMap<String, Object> reqMap) {
		log.info("SERVICE==========ReqMap=========");
//		for(String key: reqMap.keySet()) {
//			log.info("{}: {}",key, reqMap.get(key));
//		}
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		ArrayList<Object> list;
		String totalCnt = "";
		String userCareerLength = ((String[]) reqMap.get("userCareerLength"))[0];
		//String userGender = ((String[]) reqMap.get("userGender"))[0];
		String userGender = "";
		String keywords = ((String[]) reqMap.get("keywords"))[0];
		String userMaritalStatus = "";
		try {
			
			 if (userCareerLength.equals("")) {
				  userCareerLength = null;
				  reqMap.put("userCareerLength", userCareerLength);
			 }
			 
			 if (keywords.equals("")) {
				 keywords = null;
				 reqMap.put("keywords", keywords);
				 
			}else {
				reqMap.put("keywords", keywords.split(","));
			}
			 
			 // 결혼 유무
			 if(!userMaritalStatus.equals("")) {
				 userMaritalStatus = ((String[])reqMap.get("userMaritalStatus"))[0];
				 reqMap.put("userMaritalStatus", userMaritalStatus );
			 }
			 
			if (!userGender.equals("")) {
				userGender = ((String[]) reqMap.get("userGender"))[0];
				reqMap.put("userGender", userGender);
			}
			
			for(String key: reqMap.keySet()) {
				log.info("{}: {}",key, reqMap.get(key));
			}
			
	
			list = (ArrayList<Object>) dbCon.selectList("personalHistory.userList", reqMap);
			totalCnt = String.valueOf(dbCon.selectOne("personalHistory.userListCount", reqMap));
			
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
			
			resMap.put("list", list);
			resMap.put("totalCnt", totalCnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("==================={}", resMap);
		return resMap;
	}

	/**
	 * �깉濡� �옉�꽦�븳 媛쒖씤 �씠�젰移대뱶 �벑濡�
	 * 
	 * @param Object inputdata : map �삎�깭�쓽 怨좎젙�뜲�씠�꽣�� json string �삎�깭�쓽 �쑀�룞�뜲�씠�꽣 �룷�븿
	 * @return statusNum �벑濡� �꽦怨듭뿬遺�
	 */
	@Override
	@Transactional
	public HashMap<String, Object> registerUser(Object inputdata) {

		int statusNum = 0;
		HashMap<String, Object> resMap = new HashMap<String, Object>();

		try {

			HashMap<String, Object> map = (HashMap<String, Object>) inputdata;
			String socialNum = ((String[]) map.get("userSocialSecunum"))[0]; // 二쇰�쇰벑濡앸쾲�샇 �븫�샇�솕

			String encodedSocialNum = Sha256.encrypt(socialNum);

			map.replace("userSocialSecunum", encodedSocialNum);

			// ------------------------------------ 以묐났 �뿬遺� �솗�씤

			ArrayList<Object> duplitedList = (ArrayList<Object>) dbCon.selectList("personalHistory.findDuplitedUserInfo", map);

			System.out.println("size : " + duplitedList.size());

			// ------------------------------------ 以묐났 �뿬遺� �솗�씤
			if (duplitedList.size() > 0) {

				resMap.put("errorCode", "1001");
				resMap.put("errorMsg", "以묐났�맂 �궗�슜�옄�엯�땲�떎.");

				return resMap; // 以묐났�씪 寃쎌슦 �뿉�윭肄붾뱶 由ы꽩
			} else {
				// 작성자 개인정보 입력
				statusNum = (Integer) dbCon.insert("personalHistory.registerUser", map);
				// 혈액형 테이블 등록
				 int cnt = hdao.insert(map); 
				//int statusNum2 = (Integer)dbCon.insert("UserInfohealth.insert", map);
				// �쑀�룞�삎 �뜲�씠�꽣 json String -> ArrayList<HashMap<String, Object>> parsing

				String[] strList = (String[]) map.get("flexibleData");
				String listJsonStr = strList[0];

				int userIdx = (Integer) map.get("userIdx"); // 怨좎젙 �뜲�씠�꽣濡� �벑濡앸맂 pk瑜� �쑀�룞�뜲�씠�꽣�쓽 fk濡� �궗�슜
				log.info("=======================userIdx{}", userIdx);
				
				
				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				ObjectMapper mapper = new ObjectMapper();
				list = mapper.readValue(listJsonStr, ArrayList.class);

				// 가변 테이블 데이터 처리
				log.info("=======================list{}", list);
				for (int i = 0; i < list.size(); i++) {
					String key = (String) list.get(i).get("tbName");
					
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.putAll(list.get(i));
					paramMap.put("userIdx",userIdx);
					
					if(key.equals("edu")) {
						// if문 작성전에는 학교 졸업날짜 null로 보낼경우 에러발생 
						String edudate = (String)paramMap.get("eduDate");
						if(edudate != null && edudate.length() !=0) {
						String year = edudate.substring(0, 4);
						String month = edudate.substring(5,7);
						
						// edu Date -> 달력형태 처리
//						System.out.println("########## edu getDate : "+paramMap);
//						System.out.println("################# edudate : " + edudate);
//						System.out.println("############### year and month : " + year +"/" +month);
						paramMap.put("eduYear", year);
						paramMap.put("eduMonth", month);
						}
						//System.out.println("######################################## paramMap : " + paramMap);
						eDao.insert(paramMap);
					}else if(key.equals("qualifi")) {
						qDao.insert(paramMap);
					}else if(key.equals("career")) {
						String career = (String)paramMap.get("career");
						cDao.insert(paramMap);
					}else if(key.equals("training")) {
						tDao.insert(paramMap);
					}else if(key.equals("licen")) {
						lDao.insert(paramMap);
					}else if(key.equals("skill")) {
						sDao.insert(paramMap);
					}
				}
				
				resMap.put("userIdx", userIdx);

			}

		} catch (Exception e) {
			System.out.println("ERROR registerUserDAOImpl : " + e);
			e.printStackTrace();
		}
		log.info("============SERVICE RESMAP:{}",resMap);
		return resMap;
	}

	/**
	 * 湲곗〈 媛쒖씤�씠�젰移대뱶 �벑濡앷굔�뿉 ���븳 �닔�젙泥섎━
	 * 
	 * @HashMap<String,Object> inputdata : map �삎�깭�쓽 怨좎젙�뜲�씠�꽣�� json string �삎�깭�쓽 �쑀�룞�뜲�씠�꽣 �룷�븿
	 * 
	 * @return statusNum �벑濡� �꽦怨듭뿬遺�
	 */
	@Override
	public int registerUserUpdate(HashMap<String, Object> inputdata) {

		int statusNum = 0;

		try {
			
			statusNum = (Integer) dbCon.update("personalHistory.registerUserUpdate", inputdata);

			String[] strList = (String[]) inputdata.get("flexibleData");
			String listJsonStr = strList[0];

			String[] userIdxArr = (String[]) inputdata.get("userIdx");
			String userIdx = userIdxArr[0];

			inputdata.replace("userIdx", userIdx);

			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			ObjectMapper mapper = new ObjectMapper();
			list = mapper.readValue(listJsonStr, ArrayList.class);

			// �쑀�룞�뜲�씠�꽣 �궘�젣泥섎━
			// �닔�젙嫄댁뿉 ���빐�꽌留� 濡쒖쭅泥섎━濡� 蹂�寃쏀븯硫� 醫뗭쓣寃�
			// �쁽�옱 濡쒖쭅 : 怨좎젙�뜲�씠�꽣 �뾽�뜲�씠�듃, �쑀�룞�뜲�씠�꽣 �쟾泥댁궘�젣 �썑 �옱�벑濡�
			dbCon.delete("personalHistory.deleteCareerData", inputdata);
			dbCon.delete("personalHistory.deleteEduData", inputdata);
			dbCon.delete("personalHistory.deleteLicenData", inputdata);
			dbCon.delete("personalHistory.deleteQualifiData", inputdata);
			dbCon.delete("personalHistory.deleteSkillData", inputdata);
			dbCon.delete("personalHistory.deleteTrainingData", inputdata);
			// health 테이블 데이터 삭제.
			int result = hdao.delete(inputdata);
			System.out.println("health 테이블 데이터삭제 "+result);
			
			//데이터 등록
			hdao.insert(inputdata);
			// �쑀�룞�뜲�씠�꽣 �옱�벑濡�
			for (int i = 0; i < list.size(); i++) {
				String key = (String) list.get(i).get("tbName");
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.putAll(list.get(i));
				paramMap.put("userIdx",userIdx);
				
				if(key.equals("edu")) {
					// if문 작성전에는 학교 졸업날짜 null로 보낼경우 에러발생 
					String edudate = (String)paramMap.get("eduDate");
					if(edudate != null && edudate.length() !=0) {
					String year = edudate.substring(0, 4);
					String month = edudate.substring(5,7);
					
					// edu Date -> 달력형태 처리
//					System.out.println("########## edu getDate : "+paramMap);
//					System.out.println("################# edudate : " + edudate);
//					System.out.println("############### year and month : " + year +"/" +month);
					paramMap.put("eduYear", year);
					paramMap.put("eduMonth", month);
					}
					eDao.insert(paramMap);
				}else if(key.equals("qualifi")) {
					qDao.insert(paramMap);
				}else if(key.equals("career")) {
					cDao.insert(paramMap);
				}else if(key.equals("training")) {
					tDao.insert(paramMap);
				}else if(key.equals("licen")) {
					lDao.insert(paramMap);
				}else if(key.equals("skill")) {
					sDao.insert(paramMap);
				}
			
			}

		} catch (Exception e) {
			System.out.println("ERROR registerUserDAOImpl : " + e);
		}

		return statusNum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getRegisterData(Map<String, Object> reqMap)
			throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		log.info("SERVICE==========ReqMap=========");
		for(String key: reqMap.keySet()) {
			log.info("{}: {}",key, reqMap.get(key));
		}
		String[] strList = (String[]) reqMap.get("tbNames");
		String listJsonStr = strList[0];
		String[] userIdxList = (String[]) reqMap.get("userIdx");
		String userIdx = userIdxList[0];
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ----------------"+userIdx);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ObjectMapper mapper = new ObjectMapper();
		list = mapper.readValue(listJsonStr, ArrayList.class);
		log.info("=======================LIST{}",list);
		// ---------------------------------------- 怨좎젙 而щ읆 �뜲�씠�꽣 泥섎━
		HashMap<String, Object> fixedMap = new HashMap<String, Object>();

		fixedMap.put("userIdx", userIdx);

		ArrayList<Object> arr = (ArrayList<Object>) dbCon.selectList("personalHistory.getRegisterData", fixedMap);
		resMap.put("fixedData", arr);
		arr = null;
		arr = (ArrayList<Object>) hdao.selectone(userIdx);
		resMap.put("fixedData2", arr);
		
		System.out.println(arr);
		
		int intUserIdx = Integer.parseInt(userIdx);
			
		resMap.put("edu", eDao.list(intUserIdx));		
		resMap.put("qualifi", qDao.list(intUserIdx));
		resMap.put("career", cDao.list(intUserIdx));
		resMap.put("training", tDao.list(intUserIdx));
		resMap.put("licen", lDao.list(intUserIdx));
		resMap.put("skill", sDao.list(intUserIdx));

		return resMap;
	}

	@Override
	public int imgInsert(MultipartFile file, String userName, Image image) throws IllegalStateException, IOException {
		String path = "c:/upload/image";
		log.info("+-----OriginalFilename------+");
		log.info("{}", file.getOriginalFilename());
		log.info("+---------------------------+");

		int lastOfDot = file.getOriginalFilename().lastIndexOf(".");

		String extention = file.getOriginalFilename().substring(lastOfDot + 1);

		String savedName = file.getOriginalFilename().toString();

		
		log.info(savedName);
		File picture = new File(path, savedName);
		file.transferTo(picture);
		image.setImgName(savedName);
		return imgDao.imgInsert(image);
	}

	@Override
	public int imgUpdate(MultipartFile file, String userName, Image image) throws IllegalStateException, IOException {
		String path = "c:/upload/image";
		log.info("+-----OriginalFilename------+");
		log.info("FILE :{}", file);
		log.info("{}", file.getOriginalFilename());
		log.info("+---------------------------+");
		
		int lastOfDot = file.getOriginalFilename().lastIndexOf(".");

		String extention = file.getOriginalFilename().substring(lastOfDot + 1);

		String savedName = userName + "." + extention;

		log.info("���옣�븷 �씠由� :{}",savedName);
		File picture = new File(path, savedName);
		file.transferTo(picture);
		image.setImgName(savedName);
		
		int userIdx = image.getUserIdx();
		Image result = imgDao.getUserImg(userIdx);	// �씠誘몄�瑜� 議고쉶�빐�꽌
		if(result==null) {							// 寃곌낵媛� null�씠硫�
			return imgDao.imgInsert(image);			// �닔�젙�씠 �븘�땲�씪 �궫�엯�쓣 �빐以��떎
		}
		return imgDao.imgUpdate(image);
	}

	public Image getUserImg(Integer userIdx) {
		return imgDao.getUserImg(userIdx);
	}

	public List<String> getUserCountByCareerDate() {
		return dbCon.selectList("personalHistory.getUserCountByCareerDate");
	}
	
	public HashMap<String, Object> excelDownload(){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String,Object>> list = (List<HashMap<String,Object>>) dbCon.selectList("personalHistory.getinfo");
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row;
		HSSFCell cell;
		// �궎媛� �솗�씤
		for(String key: list.get(0).keySet()) {
			System.out.println(key);
		}
		
		if(list.size()>0) {
			
			for(int i=0;i<list.size();i++) {
				row=sheet.createRow(i);
				cell=row.createCell(0);
				cell.setCellValue(String.valueOf(list.get(i).get("USER_IDX")));
				cell=row.createCell(1);
				cell.setCellValue(String.valueOf(list.get(i).get("USER_NAME")));
				cell=row.createCell(2);
				cell.setCellValue(String.valueOf(list.get(i).get("USER_COMP")));
				cell=row.createCell(3);
				cell.setCellValue(String.valueOf(list.get(i).get("USERREGISTERDATE")));
				cell=row.createCell(4);
				cell.setCellValue(String.valueOf(list.get(i).get("USER_SEX")));
				cell=row.createCell(5);
				cell.setCellValue(String.valueOf(list.get(i).get("CAREER_DATE")));
				
				
			}
			
			File file = new File("C:\\test\\testWrite.xls");
			if(!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FileOutputStream fos = null;
			
			try {
				fos = new FileOutputStream(file);
				workbook.write(fos);
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(workbook != null) workbook.close();
					if(fos != null) fos.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			map.put("success", "Y");
		}else {
			map.put("success", "N");
		}
		
		return map;
	}
	
	
}
