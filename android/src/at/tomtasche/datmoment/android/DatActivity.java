package at.tomtasche.datmoment.android;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class DatActivity extends Activity {

    private static final String SERVER_URL = "http://datmoment-engine.appspot.com/";

    private Button buttonUpload;
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_dat_moment);

	buttonUpload = (Button) findViewById(R.id.buttonUpload);
	buttonUpload.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		doUpload();
	    }
	});

	try {
	    InputStream inputStream = getContentResolver().openInputStream(
		    (Uri) getIntent().getParcelableExtra(Intent.EXTRA_STREAM));
	    bitmap = BitmapFactory.decodeStream(inputStream);

	    ImageView imageThumb = (ImageView) findViewById(R.id.imageThumb);
	    imageThumb.setImageBitmap(bitmap);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void doUpload() {
	buttonUpload.setText("Uploading...");
	buttonUpload.setEnabled(false);

	new Thread() {
	    
	    @SuppressWarnings("unchecked")
	    public void run() {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(SERVER_URL + "dat");

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 75, byteStream);
		byte[] data = byteStream.toByteArray();
		ByteArrayBody byteBody = new ByteArrayBody(data, "dat.jpg");

		MultipartEntity mpEntity = new MultipartEntity();
		mpEntity.addPart("dat", byteBody);

		httppost.setEntity(mpEntity);
		try {
		    HttpResponse response = httpclient.execute(httppost);
		    if (response.getStatusLine().getStatusCode() == 200) {
			final Map<String, Object> container = new Gson().fromJson(
				EntityUtils.toString(response.getEntity()), Map.class);

			DatActivity.this.runOnUiThread(new Runnable() {

			    @SuppressWarnings("deprecation")
			    @Override
			    public void run() {
				final String url = SERVER_URL + "dat?key=" + container.get("key");

				buttonUpload.setText("Done, open!");
				buttonUpload.setEnabled(true);
				buttonUpload.setOnClickListener(new OnClickListener() {

				    @Override
				    public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_TEXT, url);

					startActivity(Intent.createChooser(intent,
						"Quick! Share dat moment"));
				    }
				});

				ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				clipboard.setText(url);

				((TextView) findViewById(R.id.textBtw))
					.setText("Or head over to the app of your choice and paste the link (it's in your clipboard now).");
			    }
			});
		    } else {
			throw new RuntimeException();
		    }

		    httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
		    e.printStackTrace();

		    DatActivity.this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
			    buttonUpload.setText("Failed. :(");
			    buttonUpload.setEnabled(true);
			}
		    });
		}

	    }
	}.start();
    }
}
