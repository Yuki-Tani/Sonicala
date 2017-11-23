package sonicala.app;

/*
 * 定数クラス
 */
public class Constants {
	
	public static final int
		// 分析に使用するスレッド数
		ANALYZE_DATA_MANAGER_THREAD_QUANTITY = 3,
		// 分析を開始するまでの時間(ms)
		ANALYZE_DATA_START_TIME = 50,
		// 分析を行う周期 (ms)
		ANALYZE_DATA_FREQUENCY = 80;
	
	public static final int
		// 描画に使用するスレッド数
		PAINT_BACK_THREAD_QUANTITY = 3,
		PAINT_FORWARD_THREAD_QUANTITY = 2,
		// 背景描画を開始するまでの時間(ms)
		PAINT_BACK_START_TIME = 200,
		// 背景描画を行う周期(ms)
		PAINT_BACK_FREQUENCY = 150,
		// 前景描画を開始するまでの時間(ms)
		PAINT_FORWARD_START_TIME = 200,
		// 前景描画を行う周期(ms)
		PAINT_FORWARD_FREQUENCY = 200;
	
	public static final int
		// １つの時間軸に存在する星の数
		STAR_QUANTITY_ON_STAR_LINE = 180,
		// 背景に存在する時間軸の数
		STAR_LINE_QUANTITY_IN_STAR_SPACE = 40,
		// 星の生存時間(ms)
		STAR_LIFE = STAR_LINE_QUANTITY_IN_STAR_SPACE * ANALYZE_DATA_FREQUENCY,
		// 標準の星の大きさ
		STAR_STANDARD_SIZE = 2,
	
		STAR_SPACE_START_DEGREE = 200,
		STAR_SPACE_END_DEGREE = -180,
	
		STAR_POWER_MIN = 60,
		STAR_POWER_MAX = 120;
	
	public static final double
		STAR_SPACE_DISTANCE_RATE = 1.0,
		STAR_SPACE_VISIBLE_RATE = 0.8,
		STAR_COLOR_R = 0.0,
		STAR_COLOR_G = 0.0,
		STAR_COLOR_B = 1.0;
	
	public static final int
	WINDOW_WIDTH = 955,
	WINDOW_HEIGHT = 600;
	
	// FFT
	public static final double 
		FFT_DB_BASIS = 0.00002;
	
	// autocorrelation
	public static final int
		AUTOCORRELATION_WIDTH = Integer.MAX_VALUE;
	public static final double
		AUTOCORRELATION_MOUNTAIN_MARGIN = 0.000001;
	
	// resources
	public static final String
		// 音楽ファイルのあるディレクトリ(res/からの相対パス /で終わらせる)
		RESOURCE_DIRECTORY_PATH_MUSIC = "songs/music/",
		// 音楽ファイルの拡張子(.をつける)
		RESOURCE_MUSIC_FILE_EXTENSION = ".wav",
		
		// 楽曲情報ファイルのあるディレクトリ(res/からの相対パス /で終わらせる)
		RESOURCE_DIRECTORY_PATH_INFORMATION = "songs/information/",
		// 楽曲情報ファイルの拡張子(.をつける)
		RESOURCE_INFORMATION_FILE_EXTENSION = ".inf",
		
		// 楽譜ファイルのあるディレクトリ(res/からの相対パス /で終わらせる)
		RESOURCE_DIRECTORY_PATH_SCORE = "songs/score/",
		// 楽譜ファイルの拡張子(.をつける)
		RESOURCE_SCORE_FILE_EXTENSION = ".scr",
		
		// 歌詞ファイルのあるディレクトリ(res/からの相対パス /で終わらせる)
		RESOURCE_DIRECTORY_PATH_LYRICS = "songs/lyrics/",
		// 歌詞ファイルの拡張子(.をつける)
		RESOURCE_LYRICS_FILE_EXTENSION = ".lyr";
	
	// information file
	public static final String
		INFORMATION_FILE_SEPARATOR = ":",
		INFORMATION_FILE_INDEX_SONG_NAME = "song name",
		INFORMATION_FILE_INDEX_SUB_NAME = "sub name",
		INFORMATION_FILE_INDEX_VOCAL = "vocal",
		INFORMATION_FILE_INDEX_DESCRIPTION = "description";

	
	// player
	public static final int
		PLAYER_MIXIER_INDEX = 0;
	// recorder
	public static final int
		RECORDER_MIXIER_INDEX = 3;
}
