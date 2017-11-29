package sonicala.model.song;

import java.util.HashMap;

/**
 * 楽曲情報の詳細を保持するMap.
 * key文字列の大文字・小文字は区別されず、空白は無視される。
 * value文字列の先頭・末端の空白は取り除かれる。
 * @author Yuki
 *
 */
@SuppressWarnings("serial")
public class SongInformationDetails extends HashMap<String,String>{
	
	public SongInformationDetails() {
		
	}
	
	@Override
	public String put(String key, String value) {
		String actualKey = key.toLowerCase().replaceAll("\\s+","");
		String actualValue = value.trim();
		System.out.println("*key*"+actualKey+" *value*"+actualValue);
		return super.put(actualKey, actualValue);
	}
	
	@Override
	public String get(Object key) {
		String actualKey = ((String)key).toLowerCase().replaceAll("\\s+","");
		return super.get(actualKey);
	}
}
