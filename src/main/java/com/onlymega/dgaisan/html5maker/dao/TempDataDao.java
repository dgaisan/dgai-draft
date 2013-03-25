package com.onlymega.dgaisan.html5maker.dao;

import com.onlymega.dgaisan.html5maker.model.TempData;

public interface TempDataDao {
	void saveData(TempData data) throws Exception;
	
	TempData getDataByToken(String token) throws Exception;

	TempData getDataById(Long tempDataId);
}
