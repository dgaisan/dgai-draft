package com.onlymega.dgaisan.html5maker.common;

public interface CommonData {
	
	// Session objects:
	
	/*package*/ static final String LOGGED_IN = "loggedin";
	/*package*/ static final String USER_OBJECT = "theUser";
	/*package*/ static final String DATA_ID = "data_ids";
	/*package*/ static final String MULTIPLE_LOGIN_ATTEMPTS = "login_attempts";

	// Result types:

	/*package*/ static final String ACTION_RESULT_LOGIN = "login_result";
	/*package*/ static final String ADMIN = "admin";
	/*package*/ static final String SAVE_BANNERS = "save_banners";

	// Misc data:

	/*package*/ static final int BUFFER_SIZE = 1024;
	/*package*/ static final String TEMP_FOLDER = "temp_uploads";
	/*package*/ static final String ASSETS_PREFIX = "html5maker_assets_";
	/*package*/ static final String DEFAULT_CONTEXT = "ROOT";
	/*package*/ static final String FREE_MEMBERSHIP = "Free";
	/*package*/ static final String USER_ONLINE_TOKEN = "_user_online_token_";
	
}
