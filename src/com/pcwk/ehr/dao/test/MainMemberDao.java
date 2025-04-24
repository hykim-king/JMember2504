/**
 * Package Name : com.pcwk.ehr.dao.test <br/>
 * Class Name: MainMemberDao.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ──────────────────────────────────────────<br/>
 * 최초 생성일 : 2025-04-18<br/>
 *
 * ──────────────────────────────────────────<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.dao.test;

import java.util.List;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.member.dao.MemberDao;
import com.pcwk.ehr.member.vo.MemberVO;

public class MainMemberDao implements PLog {

	private MemberVO member01;
	private MemberVO member02;
	private MemberVO member03;
	MemberDao dao;

	public MainMemberDao() {
		// pcwk05,이상무05,4321a,jamesol@paran.com,0,2025-04-22,관리자
		member01 = new MemberVO("pcwk06", "이상무06", "4321a", "jamesol06@paran.com", 0, "2025-04-22", "일반");
		member02 = new MemberVO("pcwk07", "이상무07", "4321a", "jamesol07@paran.com", 0, "2025-04-22", "관리자");
		member03 = new MemberVO("pcwk08", "이상무08", "4321a", "jamesol08@paran.com", 0, "2025-04-22", "일반");

		dao = new MemberDao();
	}

	// 회원가입
	public void doSave() {

		LOG.debug("┌───────────────────────┐");
		LOG.debug("│doSave()               │");
		LOG.debug("└───────────────────────┘");
		int flag = dao.doSave(member01);
		if (flag == 0) {
			System.out.println("등록 실패");
		} else if (flag == 2) {
			System.out.println("기존회원이 존재 합니다.");
		} else {
			System.out.println("등록 성공");
		}

	}

	public void isExistsMember() {
		LOG.debug("┌───────────────────────┐");
		LOG.debug("│isExistsMember()       │");
		LOG.debug("└───────────────────────┘");		
		MemberVO param = new MemberVO();

		param.setMemberId("pcwk01");
		boolean flag = dao.isExistsMember(param);
		if (flag == true) {
			System.out.println("회원존재 확인 성공");
		} else {
			System.out.println("회원존재 확인 실패");
		}
	}

	// 회원 단건 조회
	public void doSelectOne() {
		LOG.debug("┌───────────────────────┐");
		LOG.debug("│doSelectOne()          │");
		LOG.debug("└───────────────────────┘");	
		MemberVO param = new MemberVO();
		param.setMemberId("pcwk01");

		MemberVO outVO = dao.doSelectOne(param);

		if (null != outVO) {
			System.out.println("회원조회 성공" + outVO);
		} else {
			System.out.println("회원조회 실패");
		}

	}

	public void doDelete() {
		LOG.debug("┌───────────────────────┐");
		LOG.debug("│doDelete()             │");
		LOG.debug("└───────────────────────┘");			
		MemberVO param = new MemberVO();
		param.setMemberId("pcwk01");

		int flag = dao.doDelete(param);

		if (0 == flag) {
			System.out.println("회원삭제 실패");
		} else if (1 == flag) {
			System.out.println("회원삭제 성공");
		} else if (2 == flag) {
			System.out.println("삭제 회원이 없습니다.");
		}

	}

	public void doUpdate() {
		LOG.debug("┌───────────────────────┐");
		LOG.debug("│doUpdate()             │");
		LOG.debug("└───────────────────────┘");			
		MemberVO param = new MemberVO("pcwk01", "이상무01_U", "4321a_U", "jamesol06@paran.com_U", 0, "2025-04-25", "일반_U");

		int flag = dao.doUpdate(param);

		if (0 == flag || 1 == flag) {
			System.out.println("회원수정 실패");
		} else if (2 == flag) {
			System.out.println("회원수정 성공");
		} else if (9 == flag) {
			System.out.println("수정 회원이 없습니다.");
		}

	}

	public void doRetrieve() {
		LOG.debug("┌───────────────────────┐");
		LOG.debug("│doRetrieve()           │");
		LOG.debug("└───────────────────────┘");				
		MemberVO param = new MemberVO();
		param.setSearchDiv("10");
		param.setSearchWord("01");
		
		List<MemberVO> list = dao.doRetrieve(param);
		for (MemberVO vo : list) {
			LOG.debug(vo);
		}		
	}	
	
	public static void main(String[] args) {

		// Hello
		MainMemberDao main = new MainMemberDao();
		// 회원가입
		// main.doSave();

		// 회원존재 확인
		// main.isExistsMember();

		// 회원단건 조회
		// main.doSelectOne();

		// 회원삭제
		// main.doDelete();

		// 회원Update
		//main.doUpdate();
		
		// 회원목록 조회
		main.doRetrieve();

	}

}
