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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.Workdiv;
import com.pcwk.ehr.member.vo.MemberVO;

public class MemberDao implements Workdiv<MemberVO>, PLog {

	// 구글 docs
	public static final String MEMBER_URL = "https://drive.google.com/file/d/1el8BQlmrkGj6_tEZmtv67ViH3Hw3Zy0Z/view?usp=sharing";

	public static final String MEMBER_DATA = ".\\data\\member.csv";
	private List<MemberVO> members = new ArrayList<MemberVO>();

	// 파일에서 회원정보 읽기
	public MemberDao() {

		 getMemberReadFile(MEMBER_DATA);
		//getGoogleDocsMemberReadFile();
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

	@Override
	public int doSave(MemberVO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO doSelectOne(MemberVO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> doRetrieve(MemberVO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdate(MemberVO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(MemberVO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
