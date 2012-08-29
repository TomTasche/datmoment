import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Main {

    public static void main(String[] args) throws ClientProtocolException, IOException {
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httppost = new HttpPost("http://localhost:8888/dat");
	File file = new File("/home/user/Downloads/muh.jpg");

	MultipartEntity mpEntity = new MultipartEntity();
	ContentBody cbFile = new FileBody(file, "image/jpeg");
	mpEntity.addPart("dat", cbFile);

	httppost.setEntity(mpEntity);
	HttpResponse response = httpclient.execute(httppost);
	HttpEntity resEntity = response.getEntity();

	System.out.println(response.getStatusLine());
	if (resEntity != null) {
	    System.out.println(EntityUtils.toString(resEntity));
	}
	if (resEntity != null) {
	    resEntity.consumeContent();
	}

	httpclient.getConnectionManager().shutdown();
    }
}
