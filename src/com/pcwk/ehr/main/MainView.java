/**
 * Package Name : com.pcwk.ehr.main <br/>
 * Class Name: MainView.java <br/>
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
package com.pcwk.ehr.main;

import java.util.Scanner;

import com.pcwk.ehr.member.view.MemberView;

/**
 * 
 */
public class MainView {

	public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);
        MainView app = new MainView();
		
        while (true) {
            System.out.println(app.mainMenu());
            System.out.print("메뉴 번호를 입력하세요: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                	MemberView  memberView=new MemberView(scanner);
                	
                    break;
                case "2":
                	System.out.println("프로그램 개발 진행중 입니다.");
                	
                    break;
                case "q":
                case "Q":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;

                default:
                    System.out.println("※ 잘못된 입력입니다. 1~6 사이의 숫자를 입력해주세요.");
            }

            System.out.println(); // 줄바꿈
        }

	}
	
    public String mainMenu() {
        StringBuilder sb = new StringBuilder(2000);
        sb.append(" +-+-+-+-+ +-+-+-+-+ +-+-+-+-+-+-+-+ \n");
        sb.append(" |M|i|n|i| |J|a|v|a| |P|r|o|j|e|c|t| \n");
        sb.append(" +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ \n");
        sb.append(" |2|0|2|5|.|0|4|.|1|9|               \n");
        sb.append(" +-+-+-+-+-+-+-+-+-+-+               \n");
        sb.append("  *** PCWK 프로그램 ***                 \n");
        sb.append("  1. 회원 관리:                         \n");
        sb.append("  2. 도서관리:                         \n");
        sb.append("  Q. 종료 :                           \n");
        return sb.toString();
    }

}

