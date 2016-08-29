package com.tarot.sdfnash.tarot.common.infra;

public interface DefaultTaskCallback {
    public void onFinish(String key, int result, Object attachment);
}