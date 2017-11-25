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
		ANALYZE_DATA_FREQUENCY = 50;
	
	public static final int
		// 描画に使用するスレッド数
		PAINT_BACK_THREAD_QUANTITY = 1,
		PAINT_FORWARD_THREAD_QUANTITY = 1,
		// 背景描画を開始するまでの時間(ms)
		PAINT_BACK_START_TIME = 200,
		// 背景描画を行う周期(ms)
		PAINT_BACK_FREQUENCY = 50,
		// 前景描画を開始するまでの時間(ms)
		PAINT_FORWARD_START_TIME = 200,
		// 前景描画を行う周期(ms)
		PAINT_FORWARD_FREQUENCY = 120;
	
	public static final int
		// １つの時間軸に存在する星の数
		STAR_QUANTITY_ON_STAR_LINE = 180,
		// 背景に存在する時間軸の数
		STAR_LINE_QUANTITY_IN_STAR_SPACE = 40,
		// 星の生存時間(ms)
		STAR_LIFE = STAR_LINE_QUANTITY_IN_STAR_SPACE * ANALYZE_DATA_FREQUENCY,
	
		STAR_SPACE_START_DEGREE = 200,
		STAR_SPACE_END_DEGREE = -180,
	
		STAR_POWER_MIN = 50,
		STAR_POWER_MAX = 130;
	
	public static final double
	// 標準の星の大きさ
		STAR_STANDARD_SIZE = 1.5,
		STAR_SPACE_DISTANCE_RATE = 1.0,
		STAR_SPACE_VISIBLE_RATE = 0.8,
		STAR_COLOR_R = 0.0,
		STAR_COLOR_POW_R = 0,
		STAR_COLOR_G = 0.0,
		STAR_COLOR_POW_G = 0,		
		STAR_COLOR_B = 0,
		STAR_COLOR_POW_B = 1.0,
		STAR_COLOR_A = 0.0,
		STAR_COLOR_POW_A = 1.0,
		STAR_POWER_FILTER = 3;
	
	public static final double
		PITCH_RING_LOUDNESS_MIN = 50,
		PITCH_RING_LOUDNESS_MAX = 80,
		PITCH_RING_RADIUS_MAX = 50,
		PITCH_RING_WIDTH_MAX = 10,
		PITCH_RING_SHINE_MAX = 5,
		PITCH_RING_AFTERIMAGE_QUANTITY = 10,
		PITCH_RING_AFTERIMAGE_MAX_DEGREE = 30,
		PITCH_RING_AFTERIMAGE_START_OPAQUE = 0.4,
		PITCH_RING_AFTERIMAGE_END_OPAQUE = 0.1,
		PITCH_RING_AFTERIMAGE_END_RADIUS_RATE = 0.6,
		PITCH_RING_POWER_FILTER = 3,
		PITCH_RING_COLOR_R = 1,
		PITCH_RING_COLOR_G = 1,
		PITCH_RING_COLOR_B = 1,
		PITCH_RING_COLOR_A = 0.5;
	
	public static final double
		SCALE_RING_POSITION_RATE = 0.75,
		SCALE_RING_WIDTH = 15,
		SCALE_RING_BRIGHTNESS_WIDTH = 5,
		SCALE_RING_COLOR_R = 0,
		SCALE_RING_COLOR_G = 0.8,
		SCALE_RING_COLOR_B = 0.8,
		SCALE_RING_COLOR_A = 0.3;
	
	public static final int
		SHAFT_QUANTITY = 12,
		SHAFT_START_DEGREE = 180,
		SHAFT_END_DEGREE = -180;
	public static final double
		SHAFT_START_POSITION_RATE = 0.05,
		SHAFT_END_POSITION_RATE = 0.8,
		SHAFT_STANDARD_SIZE = 1,
		SHAFT_SHINE_RATE = 0.5,
		SHAFT_SMOKE_COLOR_R = 0,
		SHAFT_SMOKE_COLOR_G = 0.0,
		SHAFT_SMOKE_COLOR_B = 1.0,
		SHAFT_SMOKE_COLOR_A = 0.3;
	
	public static final int
	WINDOW_WIDTH = 600,
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
		INFORMATION_FILE_INDEX_DESCRIPTION = "description",
		INFORMATION_FILE_TIME_LENGTH = "time length";

	
	// player
	public static final int
		PLAYER_MIXIER_INDEX = 0;
	// recorder
	public static final int
		RECORDER_MIXIER_INDEX = 3;
}
