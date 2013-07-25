# EmojiMenu

[![Build Status](https://travis-ci.org/crowdint/emojimenu.png?branch=master)](https://travis-ci.org/crowdint/emojimenu)

EmojiMenu is an Android Library that allows you to easily add emoji support to your Android application.

The library works by binding two Android Views:

- The trigger View: shows the emoji menu with the list of images.

- The input View: receives the symbol code of the selected image.

# Configuration

1- Include the EmojiMenu directory as an Android Library Project in your application.
2- Create and 'emojis' folder in your application's assets folder
3- Copy the library emojis assets into your application's emojis or put your own icons in the folder (currently only PNGs are supported).

# Usage

Declare and EmojiMenu variable in your Android application and load the Emojis:

    EmojiMenu emojiMenu = new EmojiMenu(yourFragmentActivity);
    emojiMenu.getEmojiStore().loadEmojis();

Bind your Android Views:

    View button = mainActivity.findViewById(yourTriggerView);
    EditText input = (EditText) mainActivity.findViewById(yourInputView);
    emojiMenu.setBinding(button, input);

![emoji menu]('https://github.com/crowdint/emojimenu/emoji-menu.png')
![emoji input]('https://github.com/crowdint/emojimenu/emoji-input.png')
