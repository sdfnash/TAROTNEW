package com.tarot.sdfnash.tarot.session.viewholder;

import com.tarot.sdfnash.tarot.session.extension.DefaultCustomAttachment;
import com.netease.sdfnash.uikit.session.viewholder.MsgViewHolderText;

/**
 * Created by zhoujianghua on 2015/8/4.
 */
public class MsgViewHolderDefCustom extends MsgViewHolderText {

    @Override
    protected String getDisplayText() {
        DefaultCustomAttachment attachment = (DefaultCustomAttachment) message.getAttachment();

        return "type: " + attachment.getType() + ", data: " + attachment.getContent();
    }
}
