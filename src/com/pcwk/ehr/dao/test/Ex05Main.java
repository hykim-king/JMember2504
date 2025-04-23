/**
 * Package Name : com.pcwk.ehr.dao.test <br/>
 * Class Name: Ex05Main.java <br/>
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

/**
 * 
 */
public class Ex05Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message = "pcwk01,이상무01,4321a|pcwk02,이상무02,4321a|pcwk03,이상무03,4321a";
		
		String[] arrayStr = message.split("\\|");
		for(String str   : arrayStr) {
			//System.out.println(str);
			String[] tmpStrArr = str.split(",");
			for(String tmpStr   :tmpStrArr) {
				System.out.print(tmpStr+" ");
			}
			System.out.println();
		}
		

	}

}
