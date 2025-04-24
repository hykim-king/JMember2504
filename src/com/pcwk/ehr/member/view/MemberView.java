/**
 * Package Name : com.pcwk.ehr.member.view <br/>
 * Class Name: MemberView.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ──────────────────────────────────────────<br/>
 * 최초 생성일 : 2025-04-24<br/>
 *
 * ──────────────────────────────────────────<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.member.view;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.member.dao.MemberDao;
import com.pcwk.ehr.member.vo.MemberVO;

/**
 * 
 */
public class MemberView implements PLog {
	private Scanner scanner;

	private MemberDao dao;

	public MemberView(Scanner scanner) {
		this.scanner = scanner;
		String memberId = "";// 회원ID
		dao = new MemberDao();

		//
		while (true) {
			System.out.println(menu());
			System.out.print("메뉴 번호를 입력하세요: ");
			MemberVO inVO = new MemberVO();

			String input = scanner.nextLine().trim();
			int flag = 0;
			
			switch (input) {
			case "1":
				doRetrieve();
				break;
			case "2":
				doSelectOne();
				break;
			case "3":
				flag = doSave();
				if(1==flag)dao.writeFile();
				break;
			case "4":
				flag =doUpdate();
				if(2==flag)dao.writeFile();
				break;
			case "5":
				flag = doDelete();
				if(2==flag)dao.writeFile();
				break;

			case "6":
				dao.writeFile();
				System.out.println("Main Menu로 이동 합니다.");
				return;

			default:
				System.out.println("※ 잘못된 입력입니다. 1~6 사이의 숫자를 입력해주세요.");
			}

			//등록,수정,삭제시 파일에 기록
			if( input.equals("3") || input.equals("4")|| input.equals("5")) {
				dao.writeFile();
			}
			
			
			System.out.println(); // 줄바꿈
		}
	}

	private int doUpdate() {
		String memberId = "";
		String name = "";
		String password = "";
		String email = "";
		int loginCount = 0;
		String regDt = "";
		String role = "";

		MemberVO inVO = new MemberVO();

		while (true) {
			System.out.print("수정할 회원 ID를 입력하세요: ");
			memberId = scanner.nextLine().trim();

			if (memberId.isEmpty()) {
				System.out.println("※ [오류] ID는 필수 입력입니다.");
				continue;
			}
			inVO.setMemberId(memberId);
			break;
		}

		while (true) {
			System.out.print("수정할 회원 이름를 입력하세요: ");
			name = scanner.nextLine().trim();

			if (name.isEmpty()) {
				System.out.println("※ [오류] 이름은 필수 입력입니다.");
				continue;
			}
			inVO.setName(name);
			break;
		}

		while (true) {
			System.out.print("수정할 회원 password를 입력하세요: ");
			password = scanner.nextLine().trim();

			if (password.isEmpty()) {
				System.out.println("※ [오류] password은 필수 입력입니다.");
				continue;
			}
			inVO.setPassword(password);
			break;
		}

		while (true) {
			System.out.print("수정할 회원 email를 입력하세요: ");
			email = scanner.nextLine().trim();

			if (email.isEmpty()) {
				System.out.println("※ [오류] email은 필수 입력입니다.");
				continue;
			}
			inVO.setEmail(email);
			break;
		}

		inVO.setLoginCount(loginCount);

		inVO.setRegDt(toDateYMD(Calendar.getInstance()));

		while (true) {
			System.out.print("수정할 회원 role를 입력하세요: ");
			role = scanner.nextLine().trim();

			if (role.isEmpty()) {
				System.out.println("※ [오류] role은 필수 입력입니다.");
				continue;
			}
			inVO.setRole(role);
			break;
		}

		int flag = dao.doUpdate(inVO);
		if (3 != flag) {
			System.out.println("※ [오류] 수정오류.");
		} else {
			System.out.println("**********************************************");
			System.out.println("2. 회원 수정 :");
			System.out.println("**********************************************");

			dao.displayMembers();
		}
		return flag;
	}

	private void doRetrieve() {
		String searchDiv = "";// 검색구분
		String searchWord = "";// 검색어
		System.out.println("전체   :전체,ALL");
		System.out.println("회원ID :10,pcwk");
		System.out.println("이름   :20,이상무");
		System.out.println("이메일  :30,jamesol@paran.com");
		MemberVO inVO = new MemberVO();

		while (true) {
			System.out.print("검색 구분을 입력하세요: ");
			searchDiv = scanner.nextLine().trim();

			if (searchDiv.isEmpty()) {
				System.out.println("※ [오류] 검색 구분은 필수 입력입니다.");
				continue;
			}
			inVO.setSearchDiv(searchDiv);
			break;
		}

		while (true) {
			System.out.print("검색어를 입력하세요: ");
			searchWord = scanner.nextLine().trim();

			if (!searchDiv.equals("전체")) {
				if (searchWord.isEmpty()) {
					System.out.println("※ [오류] 검색어는 필수 입력입니다.");
					continue;
				}
			}
			inVO.setSearchWord(searchWord);
			break;
		}

		List<MemberVO> list = dao.doRetrieve(inVO);
		if (null == list || list.size() == 0) {
			System.out.println("※ [오류] 해당 조건의 데이터가 존재하지 않습니다.");
		} else {
			System.out.println("**********************************************");
			System.out.println("2. doRetrieve() :");
			System.out.println("**********************************************");
			for (MemberVO vo : list) {
				LOG.debug(vo);
			}
			
		}

	}

	private int doSave() {
		String memberId = "";
		String name = "";
		String password = "";
		String email = "";
		int loginCount = 0;
		String regDt = "";
		String role = "";

		MemberVO inVO = new MemberVO();

		while (true) {
			System.out.print("등록할 회원 ID를 입력하세요: ");
			memberId = scanner.nextLine().trim();

			if (memberId.isEmpty()) {
				System.out.println("※ [오류] ID는 필수 입력입니다.");
				continue;
			}
			inVO.setMemberId(memberId);
			break;
		}

		while (true) {
			System.out.print("등록할 회원 이름를 입력하세요: ");
			name = scanner.nextLine().trim();

			if (name.isEmpty()) {
				System.out.println("※ [오류] 이름은 필수 입력입니다.");
				continue;
			}
			inVO.setName(name);
			break;
		}

		while (true) {
			System.out.print("등록할 회원 password를 입력하세요: ");
			password = scanner.nextLine().trim();

			if (password.isEmpty()) {
				System.out.println("※ [오류] password은 필수 입력입니다.");
				continue;
			}
			inVO.setPassword(password);
			break;
		}

		while (true) {
			System.out.print("등록할 회원 email를 입력하세요: ");
			email = scanner.nextLine().trim();

			if (email.isEmpty()) {
				System.out.println("※ [오류] email은 필수 입력입니다.");
				continue;
			}
			inVO.setEmail(email);
			break;
		}

		inVO.setLoginCount(loginCount);

		inVO.setRegDt(toDateYMD(Calendar.getInstance()));

		while (true) {
			System.out.print("등록할 회원 role를 입력하세요: ");
			role = scanner.nextLine().trim();

			if (role.isEmpty()) {
				System.out.println("※ [오류] role은 필수 입력입니다.");
				continue;
			}
			inVO.setRole(role);
			break;
		}

		int flag = dao.doSave(inVO);
		if (1 != flag) {
			System.out.println("※ [오류] 해당 ID의 회원이 존재하지 않습니다.");
		} else {
			System.out.println("**********************************************");
			System.out.println("2. 회원 등록 :");
			System.out.println("**********************************************");

			dao.displayMembers();
		}
		
		return flag;
	}

	public String toDateYMD(Calendar calendar) {

		return String.format("%02d/%02d/%02d", calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1),
				calendar.get(Calendar.DAY_OF_MONTH));
	}

	private int doDelete() {
		String memberId = "";
		MemberVO inVO = new MemberVO();
		int flag = 0;
		while (true) {
			System.out.print("삭제할 회원 ID를 입력하세요: ");
			memberId = scanner.nextLine().trim();

			if (memberId.isEmpty()) {
				System.out.println("※ [오류] ID는 필수 입력입니다.");
				continue;
			}

			inVO.setMemberId(memberId);
			flag = dao.doDelete(inVO);

			if (1 != flag) {
				System.out.println("※ [오류] 해당 ID의 회원이 존재하지 않습니다.");
			} else {
				System.out.println("**********************************************");
				System.out.println("2. 회원 삭제 :");
				System.out.println(memberId + " 삭제 성공");
				System.out.println("**********************************************");

				dao.displayMembers();
			}
			break;
		}
		
		return flag;
	}

	/**
	 * 단건조회
	 */
	private void doSelectOne() {
		String memberId = "";
		MemberVO inVO = new MemberVO();

		while (true) {
			System.out.print("조회할 회원 ID를 입력하세요: ");
			memberId = scanner.nextLine().trim();

			if (memberId.isEmpty()) {
				System.out.println("※ [오류] ID는 필수 입력입니다.");
				continue;
			}

			inVO.setMemberId(memberId);
			MemberVO outVO = dao.doSelectOne(inVO);

			if (null == outVO) {
				System.out.println("※ [오류] 해당 ID의 회원이 존재하지 않습니다.");
			} else {
				System.out.println("**********************************************");
				System.out.println("2. 회원 단건 조회 :");
				System.out.println(outVO + " 조회 성공");
				System.out.println("**********************************************");
			}
			break;
		}
	}

	public String menu() {
		StringBuilder sb = new StringBuilder(2000);
		sb.append("  *** 회원 관리 프로그램 ***               \n");
		sb.append("  1. 회원 목록 조회:                     \n");
		sb.append("  2. 회원 단건 조회:                     \n");
		sb.append("  3. 회원 단건 저장:                     \n");
		sb.append("  4. 회원 수정:                        \n");
		sb.append("  5. 회원 삭제:                        \n");
		sb.append("  6. 종료 :                           \n");
		return sb.toString();
	}

}
