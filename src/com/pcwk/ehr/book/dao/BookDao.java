/**
 * Package Name : com.pcwk.ehr.book.dao <br/>
 * Class Name: BookDao.java <br/>
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
package com.pcwk.ehr.book.dao;

import java.util.List;

import com.pcwk.ehr.book.vo.BookVO;
import com.pcwk.ehr.cmn.Workdiv;

/**
 * 
 */
public class BookDao implements Workdiv<BookVO> {

	@Override
	public int doSave(BookVO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BookVO doSelectOne(BookVO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> doRetrieve(BookVO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdate(BookVO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(BookVO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
