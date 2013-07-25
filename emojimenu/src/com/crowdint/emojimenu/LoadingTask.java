package com.crowdint.emojimenu;

import android.os.AsyncTask;

public class LoadingTask extends AsyncTask<EmojiStore, Void, Void> {

  @Override
  protected Void doInBackground(EmojiStore... emojiStore) {
    emojiStore[0].loadEmojis();
    return null;
  }

}
