/*
 * Copyright 2023 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example;

import com.google.zxing.EncodingParameter;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import ej.microui.display.BufferedImage;
import ej.microui.display.GraphicsContext;
import ej.microui.display.ResourceImage;
import ej.mwt.Widget;
import ej.mwt.style.Style;
import ej.mwt.util.Size;
import ej.widget.util.render.ImagePainter;

/**
 * Class that extends Widget superclass used to draw the QrCode.
 */
public class QrCodeWidget extends Widget {

	private BufferedImage qrCodeImage;

	private static final int IMAGE_SIZE = 150;
	private static final String HELLO_WORLD = "Hello World"; //$NON-NLS-1$

	/**
	 * Instantiates a new QrCodeWidget object with a QrCode containing "Hello world" in raw text.
	 */
	public QrCodeWidget() {

		try {
			this.qrCodeImage = new QRCodeWriter().getAsImage(HELLO_WORLD, IMAGE_SIZE, IMAGE_SIZE);
		} catch (WriterException e) {
			e.printStackTrace();
			this.qrCodeImage = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE);
		}

	}

	@Override
	protected void computeContentOptimalSize(Size size) {

		ResourceImage image = this.qrCodeImage;

		ImagePainter.computeOptimalSize(image, size);

	}

	/**
	 * Generates and updates the QrCode displayed.
	 *
	 * @param qrCodeInfo
	 *            the object containing the necessary informations to generate the qrcode such as color or logo
	 *
	 */
	public void updateQrCode(QrCodeInfo qrCodeInfo) {

		// create a new encoding parameter to provide optional parameters for the qrcode encoding.

		// as we will specify a logo to put in the middle of the QRCode, increase the error correction level to make
		// sure that the content is still readable despite having a logo in the center of the QRCode.
		EncodingParameter encodingParameter = new EncodingParameter.EncodingParameterBuilder()
				.setErrorCorrectionLevel(ErrorCorrectionLevel.Q).setColor(qrCodeInfo.getColor())
				.setLogo(qrCodeInfo.getLogo()).build();

		// close the previous image to free memory
		this.qrCodeImage.close();

		try {
			// create and return a new qrCode using the encodingParameter object as a BufferedImage
			this.qrCodeImage = new QRCodeWriter().getAsImage(qrCodeInfo.getContent(), IMAGE_SIZE, IMAGE_SIZE,
					encodingParameter);
		} catch (WriterException e) {
			e.printStackTrace();
		}

		this.requestRender();

	}

	@Override
	protected void renderContent(GraphicsContext g, int contentWidth, int contentHeight) {

		ResourceImage image = this.qrCodeImage;

		Style style = getStyle();
		g.setColor(style.getColor());
		ImagePainter.drawImageInArea(g, image, 0, 0, contentWidth, contentHeight, style.getHorizontalAlignment(),
				style.getVerticalAlignment());

	}

}
