package com.crowdint.emojimenu;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class EmojiGridAdapter extends BaseAdapter {

  private List<String> emojisInPage;
  private FragmentActivity activity;
  private EmojiMenu emojiMenu;

  public EmojiGridAdapter(FragmentActivity activity, List<String> emojisInPage,
      EmojiMenu emojiMenu) {
    this.activity = activity;
    this.emojiMenu = emojiMenu;
    this.emojisInPage = emojisInPage;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) activity
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.emojis_item, null);
    }
    setImageListener(convertView, position);
    return convertView;
  }

  @Override
  public int getCount() {
    return emojisInPage.size();
  }

  @Override
  public String getItem(int position) {
    return emojisInPage.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  private Bitmap getImage(String name) {
    return emojiMenu.getEmojiStore().getBitmap(name);
  }

  private void insertEmoji(String imgName) {
    int cursorPosition = emojiMenu.getEmojiInput().getSelectionStart();
    String emojiString = ":" + imgName + ": ";
    emojiMenu.getEmojiInput().getText().insert(cursorPosition, emojiString);
    emojiMenu.dismiss();
  }

  private void setImageListener(View convertView, int position) {
    final String imgName = emojisInPage.get(position);
    ImageView image = (ImageView) convertView.findViewById(R.id.emoji_item);
    image.setImageBitmap(getImage(imgName));
    image.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        insertEmoji(imgName);
      }
    });
  }
}
