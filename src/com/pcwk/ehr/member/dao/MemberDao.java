/**
 * Package Name : com.pcwk.ehr.member.dao <br/>
 * Class Name: MemberDao.java <br/>
 * Description: 회원 DAO <br/>
 * Modification imformation : <br/> 
 * ──────────────────────────────────────────<br/>
 * 최초 생성일 : 2025-04-18<br/>
 *
 * ──────────────────────────────────────────<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.member.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.Workdiv;
import com.pcwk.ehr.member.vo.MemberVO;

public class MemberDao implements Workdiv<MemberVO>, PLog {

	// 구글 docs
	public static final String MEMBER_URL = "https://drive.google.com/file/d/1el8BQlmrkGj6_tEZmtv67ViH3Hw3Zy0Z/view?usp=sharing";

	public static final String MEMBER_DATA = ".\\data\\member.csv";
	public static List<MemberVO> members = new ArrayList<MemberVO>();

	// 파일에서 회원정보 읽기
	public MemberDao() {

		getMemberReadFile(MEMBER_DATA);
		// getGoogleDocsMemberReadFile();
		displayMembers();
	}
	public void displayMembers() {
		LOG.debug("┌───────────────────────┐");
		LOG.debug("│List<MemberVO>         │");
		LOG.debug("└───────────────────────┘");

		for (MemberVO vo : members) {
			LOG.debug(vo);
		}
	}
	public int writeFile() {

		int count = 0; // 저장 건수
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(MEMBER_DATA))) {
			// List<MemberVO> -> MemberVO
			String filedSeparator = ",";
			// pcwk01,이상무01,4321,jamesol@paran.com,1,0,2024/10/17 14:33:00,일반

			for (MemberVO vo : members) {
				++count;
				bw.write(vo.voToString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		LOG.debug("저장 회원수:"+count);
		return count;
	}

	/**
	 * 회원 존재확인
	 * 
	 * @param dto
	 * @return true(존재)/false(없음)
	 */
	public boolean isExistsMember(MemberVO dto) {
		boolean flag = false;

		for (MemberVO vo : members) {
			if (dto.getMemberId().equals(vo.getMemberId())) {
				flag = true;
				break;
			}
		}

		return flag;
	}

	/**
	 * 회원정보 파일 to List<MemberVO>
	 * 
	 * @param paths
	 * @return
	 */
	public List<MemberVO> getGoogleDocsMemberReadFile() {

		// 1. file read
		// 2. 읽은 한줄 -> MemberVO
		// 3. List<MemberVO> members에 추가

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new URL(MEMBER_URL).openStream(), StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// LOG.debug(line);
				// pcwk01,이상무01,4321a,jamesol@paran.com,0,2025-04-18,일반 to MemberVO
				if (line.trim().isEmpty())
					continue;

				String[] dataArr = line.split("\t");

				String memberId = dataArr[0];// 회원ID
				System.out.println("memberId:" + memberId);
				String name = dataArr[1];// 이름
				String password = dataArr[2];// 비밀번호
				String email = dataArr[3];// 이메일
				int loginCount = Integer.parseInt(dataArr[4]);// 로그인 횟수
				String regDt = dataArr[5];// 가입일
				String role = dataArr[6];// 권한

				MemberVO memberVO = new MemberVO(memberId, name, password, email, loginCount, regDt, role);
				// LOG.debug(memberVO);
				members.add(memberVO);
			}
			LOG.debug("┌───────────────────────┐");
			LOG.debug("│List<MemberVO>         │");
			LOG.debug("└───────────────────────┘");

			for (MemberVO vo : members) {
				LOG.debug(vo);
			}

		} catch (FileNotFoundException e) {
			LOG.debug("FileNotFoundException:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.debug("IOException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOG.debug("Exception:" + e.getMessage());
			e.printStackTrace();
		}

		return members;
	}

	/**
	 * 회원정보 파일 to List<MemberVO>
	 * 
	 * @param paths
	 * @return
	 */
	public List<MemberVO> getMemberReadFile(String path) {

		// 1. file read
		// 2. 읽은 한줄 -> MemberVO
		// 3. List<MemberVO> members에 추가

		try (BufferedReader reader = new BufferedReader(new FileReader(path));) {
			String line;
			while ((line = reader.readLine()) != null) {
				// LOG.debug(line);
				// pcwk01,이상무01,4321a,jamesol@paran.com,0,2025-04-18,일반 to MemberVO

				String[] dataArr = line.split(",");

				String memberId = dataArr[0];// 회원ID
				String name = dataArr[1];// 이름
				String password = dataArr[2];// 비밀번호
				String email = dataArr[3];// 이메일
				int loginCount = Integer.parseInt(dataArr[4]);// 로그인 횟수
				String regDt = dataArr[5];// 가입일
				String role = dataArr[6];// 권한

				MemberVO memberVO = new MemberVO(memberId, name, password, email, loginCount, regDt, role);
				// LOG.debug(memberVO);
				members.add(memberVO);
			}


		} catch (FileNotFoundException e) {
			LOG.debug("FileNotFoundException:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.debug("IOException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOG.debug("Exception:" + e.getMessage());
			e.printStackTrace();
		}

		return members;
	}

	@Override
	public int doSave(MemberVO dto) {
		int flag = 0;
		// members
		LOG.debug("1. param:" + dto);

		// 기존 회원이 존재 하면
		if (isExistsMember(dto) == true) {
			flag = 2;
			return flag;
		}

		flag = this.members.add(dto) ? 1 : 0;
		LOG.debug("2. flag:" + flag);

		return flag;
	}

	@Override
	public MemberVO doSelectOne(MemberVO dto) {
		MemberVO outVO = null;
		LOG.debug("1. param:" + dto);
		for (MemberVO vo : members) {
			if (vo.getMemberId().equals(dto.getMemberId())) {
				outVO = vo;
				break;
			}
		}

		LOG.debug("2. outVO:" + outVO);

		return outVO;
	}

	@Override
	public List<MemberVO> doRetrieve(MemberVO param) {
		DTO dto = param;
		List<MemberVO> list = new ArrayList<MemberVO>();
		LOG.debug("1. param:" + param.getSearchDiv());
		LOG.debug("2. param:" + param.getSearchWord());

		if (dto.getSearchDiv().equals("전체") || dto.getSearchDiv().equals("ALL")) {
			return members;
			// 회원ID
		} else if (dto.getSearchDiv().equals("10")) {
			for (MemberVO vo : members) {
				if (vo.getMemberId().contains(dto.getSearchWord())) {
					list.add(vo);
				}
			}
			// 이름
		} else if (dto.getSearchDiv().equals("20")) {
			for (MemberVO vo : members) {
				if (vo.getName().contains(dto.getSearchWord())) {
					list.add(vo);
				}
			}
			// 이메일
		} else if (dto.getSearchDiv().equals("30")) {
			for (MemberVO vo : members) {
				if (vo.getEmail().contains(dto.getSearchWord())) {
					list.add(vo);
				}
			}
			// 전체
		} else {
			return members;
		}

		return list;
	}

	@Override
	public int doUpdate(MemberVO dto) {
		int flag = 0;

		// 기존 회원이 존재 하지 않음면. return 3
		if (isExistsMember(dto) == false) {
			flag = 9;
			return flag;
		}

		// Delete, Insert
		flag = doDelete(dto);

		if (flag == 1) {
			flag += doSave(dto);
		}

		return flag;
	}

	@Override
	public int doDelete(MemberVO dto) {
		int flag = 0;
		// members
		LOG.debug("1. param:" + dto);

		// 기존 회원이 존재 하지 않음면. return 2
		if (isExistsMember(dto) == false) {
			flag = 2;
			return flag;
		}

		for (int i = 0; i < members.size(); i++) {
			MemberVO vo = members.get(i);
			if (vo.getMemberId().equals(dto.getMemberId())) {
				members.remove(i);
				flag = 1;
				break;
			}
		}

		LOG.debug("2. flag:" + flag);

		return flag;
	}

}
