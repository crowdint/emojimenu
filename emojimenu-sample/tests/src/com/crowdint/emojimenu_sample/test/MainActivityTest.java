package com.crowdint.emojimenu_sample.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crowdint.emojimenu.EmojiMenu;
import com.crowdint.emojimenu_sample.MainActivity;
import com.crowdint.emojimenu_sample.R;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
  
  MainActivity mainActivity;
  EmojiMenu emojiMenu;
  Button button;
  EditText input;

  public MainActivityTest() {
    super(MainActivity.class);
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    mainActivity = getActivity();
    emojiMenu = mainActivity.getEmojiMenu();
    
    emojiMenu.getEmojiStore().loadEmojis();
    button = (Button) mainActivity.findViewById(R.id.button_emoji);
    input = (EditText) mainActivity.findViewById(R.id.input);
    emojiMenu.setBinding(button, input);
  }
  
  public void testShowEmojiMenu() {   
    TouchUtils.clickView(this, button);
    View menu = emojiMenu.getPopupWindow().getContentView();
    sleep(1000); 
    
    assertTrue(menu.isShown());
  }
  
  public void testSelectEmoji() {
    String txt = input.getText().toString();
    TouchUtils.clickView(this, button);
    View menu = emojiMenu.getPopupWindow().getContentView();
    View emoji = menu.findViewById(R.id.emoji_item);
    sleep(1000);
    TouchUtils.clickView(this, emoji);
    
    assertFalse(input.getText().toString().equals(txt));
  }
  
  public void sleep(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
