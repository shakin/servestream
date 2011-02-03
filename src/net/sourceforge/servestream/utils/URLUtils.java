/*
 * ServeStream: A HTTP stream browser/player for Android
 * Copyright 2010 William Seemann
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sourceforge.servestream.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class URLUtils {
	
	public final static int DIRECTORY = 100;
	public final static int MEDIA_FILE = 200;
	public final static int NOT_FOUND = -1;
	
	private final static String MIME_HTML = "text/html";
	
	/*
	 * Default constructor
	 */
    public URLUtils() {
    	
    }
	
	public static int getContentTypeCode(URL url) {
		
		boolean contentFound = false;
		int contentTypeCode = NOT_FOUND;
	    HttpURLConnection conn = null;
		
		try {
			
			if (url == null)
				return contentTypeCode;

		    String contentType = null;
			
        	if (url.getProtocol().equals("http")) {
        		conn = (HttpURLConnection) url.openConnection();
        	} else if (url.getProtocol().equals("https")) {
        		conn = (HttpsURLConnection) url.openConnection();        		
        	}
			
        	if (conn == null)
        		return NOT_FOUND;
        	
    		conn.setConnectTimeout(6000);
    		conn.setReadTimeout(6000);
	        conn.setRequestMethod("GET");
	    
	        if ((contentType = conn.getContentType()) != null)
	        	contentFound = true;
            
            if (contentFound) {
            	//TODO fix this
            	if (contentType.contains(MIME_HTML)) {
        			contentTypeCode = DIRECTORY;
                } else {
        			contentTypeCode = MEDIA_FILE;
                }
            }
            
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
		    ex.printStackTrace();
		} finally {
			closeHttpConnection(conn);
		}
		
		return contentTypeCode;
	}
	
	public static String getContentType(URL url) {
		
		String contentType = null;
	    HttpURLConnection conn = null;
		
		try {
			
			if (url == null)
				return contentType;
			
        	if (url.getProtocol().equals("http")) {
        		conn = (HttpURLConnection) url.openConnection();
        	} else if (url.getProtocol().equals("https")) {
        		conn = (HttpsURLConnection) url.openConnection();        		
        	}
			
        	if (conn == null)
        		return contentType;
        	
    		conn.setConnectTimeout(6000);
    		conn.setReadTimeout(6000);
	        conn.setRequestMethod("GET");
	    
	        contentType = conn.getContentType();
            
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
		    ex.printStackTrace();
		} finally {
			closeHttpConnection(conn);
		}
		
		return contentType;
	}
	
	public static String getContentType(String path) {
		
		HashMap<String, String> mimeTypes = new HashMap<String, String>();
		
		mimeTypes.put("amr","audio");
		mimeTypes.put("awb","audio");
		mimeTypes.put("amr","audio");
		mimeTypes.put("awb","audio");
		mimeTypes.put("axa","audio");
		mimeTypes.put("au","audio");
		mimeTypes.put("snd","audio");
		mimeTypes.put("flac","audio");
		mimeTypes.put("mid","audio");
		mimeTypes.put("midi","audio");
		mimeTypes.put("kar","audio");
		mimeTypes.put("mpga","audio");
		mimeTypes.put("mpega","audio");
		mimeTypes.put("mp2","audio");
		mimeTypes.put("mp3","audio");
		mimeTypes.put("m4a","audio");
		mimeTypes.put("m3u","audio");
		mimeTypes.put("oga","audio");
		mimeTypes.put("ogg","audio");
		mimeTypes.put("spx","audio");
		mimeTypes.put("sid","audio");
		mimeTypes.put("aif","audio");
		mimeTypes.put("aiff","audio");
		mimeTypes.put("aifc","audio");
		mimeTypes.put("gsm","audio");
		mimeTypes.put("wma","audio");
		mimeTypes.put("wmx","audio");
		mimeTypes.put("ra","audio");
		mimeTypes.put("rm","audio");
		mimeTypes.put("ram","audio");
		mimeTypes.put("pls","audio");
		mimeTypes.put("sd2","audio");
		mimeTypes.put("wav","audio");
		mimeTypes.put("text", "html");
		mimeTypes.put("3gp","video");
		mimeTypes.put("axv","video");
		mimeTypes.put("dl","video");
		mimeTypes.put("dif","video");
		mimeTypes.put("dv","video");
		mimeTypes.put("fli","video");
		mimeTypes.put("gl","video");
		mimeTypes.put("mpeg","video");
		mimeTypes.put("mpg","video");
		mimeTypes.put("mpe","video");
		mimeTypes.put("mp4","video");
		mimeTypes.put("qt","video");
		mimeTypes.put("mov","video");
		mimeTypes.put("ogv","video");
		mimeTypes.put("mxu","video");
		mimeTypes.put("flv","video");
		mimeTypes.put("lsf","video");
		mimeTypes.put("lsx","video");
		mimeTypes.put("mng","video");
		mimeTypes.put("asf","video");
		mimeTypes.put("asx","video");
		mimeTypes.put("wm","video");
		mimeTypes.put("wmv","video");
		mimeTypes.put("wmx","video");
		mimeTypes.put("avi","video");
		mimeTypes.put("movie","video");
		mimeTypes.put("mpv","video");
		mimeTypes.put("mkv","video");
		
    	int index = 0;
    	
    	if (path == null)
    	    return null;
    	
        index = path.lastIndexOf(".");
    		
    	if (index == -1) {
    		index = path.lastIndexOf("/");

    		if (index == path.length() - 1) {
    			return "text";
    		} else {
    			return null;
    		}
    	}
    	
    	return mimeTypes.get(path.substring(index + 1, path.length()));
	}
	
	/**
	 * Closes a HttpURLConnection
	 * 
	 * @param conn The connection to close
	 */
    private static void closeHttpConnection(HttpURLConnection conn) {
    	
    	if (conn == null)
    		return;
    	
    	conn.disconnect();
    }
}
