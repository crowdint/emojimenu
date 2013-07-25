package com.crowdint.emojimenu;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;

public class EmojiStore {

  private final static String IMAGE_TYPE = "png";
  private final static String EMOJIS_DIR = "emojis";
  private FragmentActivity activity;
  private Map<String, Bitmap> emojis;
  private List<String> emojiNames;

  public EmojiStore(FragmentActivity activity) {
    this.activity = activity;
    emojis = new HashMap<String, Bitmap>();
    emojiNames = new ArrayList<String>();
  }

  public FragmentActivity getActivity() {
    return activity;
  }

  public Bitmap getBitmap(String name) {
    String imageName = name.concat(".") + IMAGE_TYPE;
    return emojis.get(imageName);
  }

  public Map<String, Bitmap> getEmojis() {
    return emojis;
  }

  public List<String> getEmojiNames() {
    return emojiNames;
  }

  public boolean isLoaded() {
    return !emojis.isEmpty();
  }

  public boolean emojiExists(String text) {
    if (!isLoaded()) {
      loadEmojis();
    }
    return emojiNames.contains(text);
  }

  public void loadEmojis() {
    if (!isLoaded()) {
      try {
        AssetManager manager = activity.getResources().getAssets();
        String[] images = manager.list(EMOJIS_DIR);
        String emojiName;
        for (String imgName : images) {
          emojis.put(imgName, loadImage(imgName));
          emojiName = imgName.substring(0, imgName.lastIndexOf("."));
          emojiNames.add(emojiName);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void loadEmojisAsync() {
    if (!isLoaded()) {
      new LoadingTask().execute(this);
    }
  }

  protected Bitmap loadImage(String imgName) {
    AssetManager mngr = activity.getAssets();
    InputStream in = null;
    try {
      in = mngr.open(EMOJIS_DIR.concat("/") + imgName);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Bitmap bitmap = BitmapFactory.decodeStream(in, null, null);
    return bitmap;
  }

}
