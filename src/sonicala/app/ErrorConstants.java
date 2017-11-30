package sonicala.app;

public class ErrorConstants {
	public static final String
		INF_SYNTAX = "information file syntax error.",
		INF_01 = "please write one detail on ONE line.",
		INF_02 = "do NOT use incorrect separetor. correct separetor is "+Constants.INFORMATION_FILE_SEPARATOR;
	public static final String
		LYR_SYNTAX = "lyrics file syntax error.",
		LYR_01 = "please set TEMPO and BEAT.",
		LYR_02 = "number parse error!";
	public static final String
		SCO_SYNTAX = "score file syntax error.",
		SCO_01 = "please set TEMPO, BEAT, and END.",
		SCO_02 = "number parse error!",
		SCO_03 = "note name error!";
}
