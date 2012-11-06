package com.onlymega.dgaisan.html5maker.actions;

import java.util.HashMap;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.onlymega.dgaisan.html5maker.dao.TempDataDao;
import com.onlymega.dgaisan.html5maker.model.TempData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.interceptor.annotations.Before;


public class DownloadActionTest extends TestCase {
    
	private DownloadAction action;
	
	@Mock
	private TempDataDao tempDataService;
	
	@Captor
	private ArgumentCaptor<String> dataTokenCaptor;
	
	@Before
	public void setUp() {
		action = new DownloadAction();
		MockitoAnnotations.initMocks(this);
		action.setTempDataService(tempDataService);
	}
	
    public void testDownloadWithoutToken() throws Exception {
    	String result = "";
    	
    	when(tempDataService.getDataByToken(anyString())).thenReturn(null);
    	result = action.execute();
    	assertEquals(Action.ERROR, result);
    }
    
    public void testDownloadWithUnknownToken() throws Exception {
    	String token = "3432423dd";
    	String result = "";
    	
    	when(tempDataService.getDataByToken(token)).thenReturn(null);
    	
    	action.setDataToken(token);
    	result = action.execute();
    	
    	assertEquals(Action.ERROR, result);
    	verify(tempDataService).getDataByToken(dataTokenCaptor.capture());
    	assertEquals(dataTokenCaptor.getValue(), token);
	}
    
    public void testDownloadWithToken() throws Exception {
    	String token = "3432423dd";
    	String result = "";
    	
    	when(tempDataService.getDataByToken(token)).thenReturn(getTempDataObject());
    	
    	action.setDataToken(token);
    	action.setSession(new HashMap<String, Object>());
    	result = action.execute();
    	assertEquals(Action.SUCCESS, result);
	}
    
    private TempData getTempDataObject() {
    	return new TempData();
    }
}
