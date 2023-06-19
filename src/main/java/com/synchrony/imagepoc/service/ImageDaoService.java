package com.synchrony.imagepoc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/**
 * The Class ImageDaoService.
 */
@Service
@Transactional
public class ImageDaoService {

	/**
	 * Upload image.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String uploadImage() throws IOException {

		URL url;
		url = new URL("https://hannykmt.imgur.com/image");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String imageURL ="E:\\udemy\\Capture.png";

		String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imageURL, "UTF-8");

		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "Client-ID " + "8dea265790b27bb");
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		conn.connect();
		StringBuilder stb = new StringBuilder();
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data);
		wr.flush();

		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			stb.append(line).append("\n");
		}
		wr.close();
		rd.close();

		return stb.toString();
	}

}
