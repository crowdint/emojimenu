package com.crowdint.emojimenu;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

public class EmojiMenu {

  private int menuHeight = 400;
  private EditText emojiInput;
  private EmojiStore emojiStore;
  private FragmentActivity activity;
  private PopupWindow popupWindow;
  private View popUpView;
  private View parentLayout;

  public EmojiMenu(FragmentActivity activity) {
    this.activity = activity;
    emojiStore = new EmojiStore(activity);
  }

  public FragmentActivity getActivity() {
    return activity;
  }

  public EditText getEmojiInput() {
    return emojiInput;
  }

  public EmojiStore getEmojiStore() {
    return emojiStore;
  }

  public PopupWindow getPopupWindow() {
    return popupWindow;
  }

  public void copyEmojisFrom(EmojiStore emojiStore) {
    this.emojiStore = emojiStore;
  }

  public void dismiss() {
    popupWindow.dismiss();
  }

  public void setBinding(View emojiButton, View input) {
    parentLayout = input.getRootView();
    emojiInput = (EditText) input;
    configureEmojiPopUp();
    setEmojiInputListener(input);
    setEmojiButtonListener(emojiButton);
  }

  private void configureEmojiPopUp() {
    popUpView = activity.getLayoutInflater().inflate(R.layout.emojis_popup,
        null);
    popupWindow = new PopupWindow(popUpView, LayoutParams.MATCH_PARENT, 0,
        false);
    popUpView.findViewById(R.id.loading_emojis).setVisibility(View.GONE);

    ViewPager pager = (ViewPager) popUpView.findViewById(R.id.emojis_pager);
    pager.setOffscreenPageLimit(3);

    EmojiPagerAdapter adapter = new EmojiPagerAdapter(activity, this);
    pager.setAdapter(adapter);

    View deleteButton = popUpView.findViewById(R.id.delete_emoji);
    setOnDeleteListener(deleteButton);

    View backButton = popUpView.findViewById(R.id.back);
    setOnBackListener(backButton);
  }

  private void setOnBackListener(View backButton) {
    backButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        popupWindow.dismiss();
      }
    });
  }

  private void setOnDeleteListener(View deleteButton) {
    deleteButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0,
            0, KeyEvent.KEYCODE_ENDCALL);
        emojiInput.dispatchKeyEvent(event);
      }
    });
  }

  private void setEmojiInputListener(View emojiInput) {
    emojiInput.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (popupWindow.isShowing()) {
          popupWindow.dismiss();
        }
      }
    });
  }

  private void setEmojiButtonListener(View emojiButton) {
    emojiButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (popupWindow.isShowing()) {
          popupWindow.dismiss();
        } else {
          popupWindow.setHeight(menuHeight);
          popupWindow.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);
          emojiInput.requestFocus();
          showKeyboard(emojiInput);
        }
      }
    });
  }

  private void showKeyboard(View view) {
    InputMethodManager imm = (InputMethodManager) activity
        .getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(view, 0);
  }

}
