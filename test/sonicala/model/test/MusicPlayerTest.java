package sonicala.model.test;

import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioSystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sonicala.model.player.Le4MusicPlayer;
import sonicala.model.player.MusicPlayer;
import sonicala.model.player.MusicSound;
import sonicala.model.song.Music;

/*
 * PCM_SIGNED 44100.0 Hz, 16 bit, mono, 2 bytes/frame, little-endian (test240)
 * PCM_SIGNED 16000.0 Hz, 16 bit, mono, 2 bytes/frame, little-endian (test10)
 * PCM_SIGNED 44100.0 Hz, 16 bit, mono, 2 bytes/frame, little-endian (test60)
 * 
 * xxx PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian (test180)
 * PCM_SIGNED 44100.0 Hz, 16 bit, mono, 2 bytes/frame, little-endian (IGNITE_16bit)
 * PCM_SIGNED 44100.0 Hz, 16 bit, mono, 2 bytes/frame, little-endian (IGNITE_16_not_extention)
 * 
 * xxx PCM_FLOAT 16000.0 Hz, 32 bit, mono, 4 bytes/frame (IGNITE)
 * xxx PCM_FLOAT 44100.0 Hz, 32 bit, stereo, 8 bytes/frame (test300)
 * 
 */

public class MusicPlayerTest extends Application {
	MusicPlayer player;
	long time;
	XYChart.Series<Number, Number> series,series2;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		graph(stage);
		URL url = getClass().getClassLoader().getResource("songs/music/IGNITE.wav");
		System.out.println(url);
		
		String formatInfo = AudioSystem.getAudioFileFormat(url).getFormat().toString();
		System.out.println(formatInfo);
		
		Music music = new Music(url);
		player = new Le4MusicPlayer(music);
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		time = System.currentTimeMillis();
		service.scheduleAtFixedRate(this::process, 0, 30, TimeUnit.MILLISECONDS);
		player.play();
	}

	private void process() {
//		System.out.println((System.currentTimeMillis()-time)/1000);
		MusicSound sound = player.getLastMusicSound();
		if(sound.getPitch().getFrequency()>1000) return;
		if(sound.getLoudness().getPower() < -35) return;
		sound.getSpectrum();
		Platform.runLater(()->{
			System.out.println(((int)(sound.getPosition().getAbsoluteSeconds()*10))/10.0+" "
					+sound.getPitch().getFrequency());
			series.getData().add(new XYChart.Data<Number,Number>(
					(sound.getPosition().getAbsoluteSeconds()),
					(int)(sound.getPitch().getFrequency())
					));
			series2.getData().add(new XYChart.Data<Number,Number>(
					(sound.getPosition().getAbsoluteSeconds()),
					(int)(sound.getLoudness().getPower()*10)
					));
			}
		);
					
	}

	private void graph(Stage stage) {
		// チャート作成
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setMaxHeight(500);
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
        series = new XYChart.Series<>();
        series2 = new XYChart.Series<>();
        lineChart.getData().add(series);
        lineChart.getData().add(series2);
        lineChart.setAnimated(false);
		// 描画
		BorderPane pane = new BorderPane();
		pane.setCenter(lineChart);

		final Scene scene = new Scene(pane, 800, 600);

		// ウィンドウ表示
		stage.setScene(scene);
		stage.setTitle(getClass().getName());
		stage.show();
	}
}
