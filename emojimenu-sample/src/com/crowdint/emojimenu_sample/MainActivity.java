package com.crowdint.emojimenu_sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.crowdint.emojimenu.EmojiMenu;

public class MainActivity extends FragmentActivity {
  
  EmojiMenu emojiMenu;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    emojiMenu = new EmojiMenu(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
  
  public EmojiMenu getEmojiMenu() {
    return emojiMenu;
  }

}
