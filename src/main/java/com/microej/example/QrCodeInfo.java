/*
 * Copyright 2023 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example;

import ej.microui.display.Image;

/**
 * Class that allows to easily pass parameters to the QrCodeWriter class to encode a QrCode.
 */
public class QrCodeInfo {

	private final int color;
	private final String content;
	private final Image logo;

	/**
	 * Instantiates a new QrCodeInfo object to be used to generate a QrCode.
	 *
	 * @param color
	 *            the color to be used to generate the QrCode
	 * @param content
	 *            the content to be written in the QrCode
	 * @param logo
	 *            the logo to be displayed in the center of the QrCode
	 */
	public QrCodeInfo(int color, String content, Image logo) {
		this.color = color;
		this.content = content;
		this.logo = logo;
	}

	/**
	 * @return the color of the QrCode
	 */
	public int getColor() {
		return this.color;
	}

	/**
	 * @return the content of the QrCode
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * @return the logo of the QrCode
	 */
	public Image getLogo() {
		return this.logo;
	}
}
