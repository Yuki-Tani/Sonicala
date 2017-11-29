package sonicala.model.song;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import sonicala.app.Constants;

public class SongInformation {
	private Path songInfo;
	private String fileName;
	private SongInformationDetails details;
	
	public SongInformation(Path informationFile) {
		this.songInfo = informationFile;
		fileName = songInfo.getFileName().toString()
				.replaceAll(Constants.RESOURCE_INFORMATION_FILE_EXTENSION,"");
		SongInformationFileReader reader = new SongInformationFileReader(songInfo);
		details = reader.read();
	}
	
	public SongInformation(URL informationFileLocation) {
		try {
			songInfo = Paths.get(informationFileLocation.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 楽曲情報ファイル内の各種情報を取得
	 * 当該情報が登録されていない場合は空文字列を返す
	 * @param index 楽曲情報ファイル内に記述された項目名
	 * @return 楽曲情報ファイルに記述された項目要素
	 */
	public String get(String index) {
		String value = details.get(index);
		if(value == null) value = "";
		return value;
	}
	
	/**
	 * 楽曲情報ファイルの場所を取得
	 * @return 楽曲情報ファイルのURL または　null (ファイルが見つからない時)
	 */
	public URL getInformationFileURL() {
		return getClass().getClassLoader().getResource(
				Constants.RESOURCE_DIRECTORY_PATH_INFORMATION
				+ fileName
				+ Constants.RESOURCE_INFORMATION_FILE_EXTENSION);
	}

	/**
	 * 音楽ファイルの場所を取得
	 * @return 音楽ファイルのURL または　null (ファイルが見つからない時)
	 */
	public URL getMusicFileURL() {
		return getClass().getClassLoader().getResource(
				Constants.RESOURCE_DIRECTORY_PATH_MUSIC
				+ fileName
				+ Constants.RESOURCE_MUSIC_FILE_EXTENSION);
	}

	/**
	 * 楽譜ファイルの場所を取得
	 * @return 楽譜ファイルのURL または　null (ファイルが見つからない時)
	 */
	public URL getScoreFileURL() {
		return getClass().getClassLoader().getResource(
				Constants.RESOURCE_DIRECTORY_PATH_SCORE
				+ fileName
				+ Constants.RESOURCE_SCORE_FILE_EXTENSION);
	}

	/**
	 * 歌詞ファイルの場所を取得
	 * @return 歌詞ファイルのURL または　null (ファイルが見つからない時)
	 */
	public URL getLyricsFileURL() {
		return getClass().getClassLoader().getResource(
				Constants.RESOURCE_DIRECTORY_PATH_LYRICS
				+ fileName
				+ Constants.RESOURCE_LYRICS_FILE_EXTENSION);
	}
}
