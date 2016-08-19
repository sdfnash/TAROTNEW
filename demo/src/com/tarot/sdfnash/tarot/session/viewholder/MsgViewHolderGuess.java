package com.tarot.sdfnash.tarot.session.viewholder;

import com.tarot.sdfnash.tarot.session.extension.GuessAttachment;
import com.netease.sdfnash.uikit.session.viewholder.MsgViewHolderText;

/**
 * Created by zhoujianghua on 2015/8/4.
 */
public class MsgViewHolderGuess extends MsgViewHolderText {

    @Override
    protected String getDisplayText() {
        GuessAttachment attachment = (GuessAttachment) message.getAttachment();

        return attachment.getValue().getDesc() + "!";
    }
}
