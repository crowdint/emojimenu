package com.crowdint.emojimenu;

import android.content.Intent;
import android.net.Uri;
import android.text.style.ClickableSpan;
import android.view.View;

public class ClickableLink extends ClickableSpan {
  
  String url;
  EmojiStore emojiStore;
  
  public ClickableLink(String url, EmojiStore emojiStore) {
    this.url = url;
    this.emojiStore = emojiStore;
  }

  @Override
  public void onClick(View widget) {
    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    browserIntent.addCategory(Intent.CATEGORY_BROWSABLE);
    emojiStore.getActivity().startActivity(browserIntent);
  }
  
}
