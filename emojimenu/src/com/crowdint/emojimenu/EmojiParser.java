package com.crowdint.emojimenu;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;

public class EmojiParser {

  private EmojiStore emojiStore;
  private SpannableStringBuilder sb;

  public EmojiParser(EmojiStore emojiStore) {
    this.emojiStore = emojiStore;
    this.sb = new SpannableStringBuilder();
  }

  public Spannable parseString(String text) {
    sb.clear();
    String[] words = text.split(" ");
    for (String word : words) {
      if (!isEmoticon(word) && !isUrl(word)) {
        appendWord(word);
      } else if (isEmoticon(word)) {
        appendEmoji(word);
      } else if (isUrl(word)) {
        appendUrl(word);
      }
    }
    return sb;
  }

  private boolean isEmoticon(String text) {
    return text.matches("\\:[\\S\\+]+\\:");
  }

  private boolean isUrl(String text) {
    return text.matches("((ftp|http|https):\\/\\/(\\S+))(\b|$)");
  }

  private void appendWord(String word) {
    sb.append(prefixed(word));
  }
  
  private String prefixed(String word) {
    String prefix = (sb.length() > 0) ? (" ") : "";
    return prefix + word;
  }

  private void appendEmoji(String word) {
    String cleanName = word.substring(1, word.length() - 1);
    if (emojiStore.emojiExists(cleanName)) {
      sb.append(prefixed(""));
      sb.append(insertEmoji(cleanName));
    } else {
      sb.append(prefixed(word));
    }
  }

  private void appendUrl(final String url) {
    sb.append(prefixed(url));
    ClickableSpan clickableSpan = new ClickableLink(url, emojiStore);
    int start = sb.length() - url.length();
    int end = sb.length();
    sb.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  
  private Spanned insertEmoji(final String name) {
    ImageGetter imageGetter = new ImageGetter() {
      public Drawable getDrawable(String source) {
        Bitmap bitmap = emojiStore.getBitmap(name);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 30, 30, true);
        Drawable d = new BitmapDrawable(
            emojiStore.getActivity().getResources(), scaledBitmap);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        return d;
      }
    };

    Spanned cs = Html.fromHtml("<img src ='" + name + "'/>", imageGetter, null);
    return cs;
  }

}
