package sonicala.model.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sonicala.model.recorder.Le4VoiceRecorder;
import sonicala.model.recorder.VoiceRecorder;
import sonicala.model.recorder.VoiceSound;

public class VoiceRecorderTest extends Application{
	VoiceRecorder recorder;
	long time;
	NumberAxis xAxis;
	XYChart.Series<Number, Number> series,series2;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		graph(stage);
		recorder = new Le4VoiceRecorder();
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		time = System.currentTimeMillis();
		service.scheduleAtFixedRate(this::process, 0, 100, TimeUnit.MILLISECONDS);
		recorder.start();
		System.out.println("start");
	}

	private void process() {
		long nowTime = System.currentTimeMillis()-time;
		xAxis.setLowerBound(nowTime/1000.0 - 10);
		xAxis.setUpperBound(nowTime/1000.0);
//		System.out.println(nowTime);
		VoiceSound sound = recorder.getLastVoiceSound();
		if(sound.getPitch().getFrequency()>1000) return;
		if(sound.getLoudness().getPower() < -100) return;
		sound.getSpectrum();
		Platform.runLater(()->{
//			System.out.println((int)(time/1000.0)+" "
//					+sound.getPitch().getFrequency());
			series.getData().add(new XYChart.Data<Number,Number>(
					nowTime/1000.0,
					(int)(sound.getPitch().getFrequency())
					));
			series2.getData().add(new XYChart.Data<Number,Number>(
					nowTime/1000.0,
					(int)(sound.getLoudness().getPower()*10)
					));
			}
		);
					
	}

	private void graph(Stage stage) {
		// チャート作成
        xAxis = new NumberAxis();
        xAxis.setAutoRanging(false);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setMaxHeight(500);
        LineChart<Number,Number> lineChart = 
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
