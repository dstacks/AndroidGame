package com.basteldroid.framework;

import com.basteldroid.framework.Graphics.ImageFormat;

public interface Image {

	public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
