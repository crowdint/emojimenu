package com.crowdint.emojimenu;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class EmojiPagerAdapter extends PagerAdapter {

  private static final int EMOJIS_PER_PAGE = 20;
  private List<String> emojiNames;
  private FragmentActivity activity;
  private EmojiMenu emojiMenu;

  public EmojiPagerAdapter(FragmentActivity activity, EmojiMenu emojiMenu) {
    this.activity = activity;
    this.emojiMenu = emojiMenu;
    this.emojiNames = emojiMenu.getEmojiStore().getEmojiNames();
  }

  @Override
  public int getCount() {
    return (int) Math.ceil((double) emojiNames.size()
        / (double) EMOJIS_PER_PAGE);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    int initialPosition = position * EMOJIS_PER_PAGE;
    List<String> emojisInPage = new ArrayList<String>();

    for (int i = initialPosition; i < initialPosition + EMOJIS_PER_PAGE
        && i < emojiNames.size(); i++) {
      emojisInPage.add(emojiNames.get(i));
    }

    View layout = activity.getLayoutInflater().inflate(R.layout.emojis_grid,
        null);
    GridView grid = (GridView) layout.findViewById(R.id.emojis_grid);
    EmojiGridAdapter adapter = new EmojiGridAdapter(activity, emojisInPage,
        emojiMenu);
    grid.setAdapter(adapter);

    ((ViewPager) container).addView(layout);
    return layout;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    View view = (View) object;
    ((ViewPager) container).removeView(view);
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }
}