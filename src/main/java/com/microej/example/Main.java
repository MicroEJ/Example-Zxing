/*
 * Copyright 2023 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example;

import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.ResourceImage;
import ej.mwt.Desktop;
import ej.mwt.style.EditableStyle;
import ej.mwt.style.background.NoBackground;
import ej.mwt.style.background.RectangularBackground;
import ej.mwt.style.background.RoundedBackground;
import ej.mwt.style.outline.FlexibleOutline;
import ej.mwt.style.outline.UniformOutline;
import ej.mwt.style.outline.border.FlexibleRectangularBorder;
import ej.mwt.style.outline.border.RoundedBorder;
import ej.mwt.stylesheet.cascading.CascadingStylesheet;
import ej.mwt.stylesheet.selector.ClassSelector;
import ej.mwt.stylesheet.selector.StateSelector;
import ej.mwt.stylesheet.selector.TypeSelector;
import ej.mwt.stylesheet.selector.combinator.AndCombinator;
import ej.mwt.util.Alignment;
import ej.widget.basic.Button;
import ej.widget.basic.Label;
import ej.widget.basic.OnClickListener;
import ej.widget.container.Dock;
import ej.widget.container.LayoutOrientation;
import ej.widget.container.List;

/**
 * Main class containing entry point of the application.
 */
public class Main {

	private static final int MAIN_PAGE = 1001;
	private static final int MESSAGE = 4;
	private static final int TITLE = 2;

	private static final int MESSAGE_TOP_PADDING = 5;
	private static final int CORAL = 0xee502e;
	private static final int POMEGRANATE = 0xcf4520;
	private static final int CONCRETE_BLACK_25 = 0x717d83;
	private static final int TITLE_BOTTOM_BORDER = 2;
	private static final int PADDING_MARGIN = 5;
	private static final int ROUNDED_BORDER_RADIUS = 5;
	private static final int ROUNDED_BORDER_THICKNESS = 1;

	private static final String YOUTUBE_URL = "https://www.youtube.com/channel/UCQBJG8EMUTSL5i-3K4tVnDQ"; //$NON-NLS-1$
	private static final String MICROEJ_URL = "https://developer.microej.com/"; //$NON-NLS-1$
	private static final String TWITTER_URL = "https://twitter.com/microej"; //$NON-NLS-1$

	private static final int MICROEJ_COLOR = 0xEE502E;
	private static final int TWITTER_COLOR = 0x1D9BF0;
	private static final int YOUTUBE_COLOR = 0xFF0000;

	/**
	 * Entry point of the example.
	 *
	 * @param args
	 *            command line arguments.
	 */

	public static void main(String[] args) {

		MicroUI.start();

		final ResourceImage microejImage = ResourceImage.loadImage("/images/mascot.png"); //$NON-NLS-1$
		final ResourceImage twitterImage = ResourceImage.loadImage("/images/twitter.png"); //$NON-NLS-1$
		final ResourceImage youtubeImage = ResourceImage.loadImage("/images/youtube.png"); //$NON-NLS-1$

		final QrCodeWidget textWidget = new QrCodeWidget();

		Dock dock = new Dock();

		Label title = new Label("QR Code example"); //$NON-NLS-1$
		title.addClassSelector(TITLE);
		dock.addChildOnTop(title);

		List buttonList = new List(LayoutOrientation.HORIZONTAL);

		final Button twitterButton = new Button("Twitter"); //$NON-NLS-1$
		final Button microejButton = new Button("MicroEJ"); //$NON-NLS-1$
		final Button youtubeButton = new Button("Youtube"); //$NON-NLS-1$

		twitterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				QrCodeInfo qrCodeInfo = new QrCodeInfo(TWITTER_COLOR, TWITTER_URL, twitterImage);
				textWidget.updateQrCode(qrCodeInfo);
			}
		});
		microejButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				QrCodeInfo qrCodeInfo = new QrCodeInfo(MICROEJ_COLOR, MICROEJ_URL, microejImage);
				textWidget.updateQrCode(qrCodeInfo);
			}
		});
		youtubeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				QrCodeInfo qrCodeInfo = new QrCodeInfo(YOUTUBE_COLOR, YOUTUBE_URL, youtubeImage);
				textWidget.updateQrCode(qrCodeInfo);
			}
		});
		buttonList.addChild(twitterButton);
		buttonList.addChild(microejButton);
		buttonList.addChild(youtubeButton);

		dock.setCenterChild(textWidget);
		dock.addChildOnBottom(buttonList);

		Desktop mainDesktop = new Desktop();

		mainDesktop.setStylesheet(createStylesheet());
		mainDesktop.setWidget(dock);

		Display.getDisplay().requestShow(mainDesktop);

	}

	private static CascadingStylesheet createStylesheet() {
		CascadingStylesheet stylesheet = new CascadingStylesheet();

		EditableStyle style = stylesheet.getDefaultStyle();
		style.setColor(Colors.BLACK);

		style = stylesheet.getSelectorStyle(new ClassSelector(TITLE));
		style.setBackground(NoBackground.NO_BACKGROUND);
		style.setHorizontalAlignment(Alignment.HCENTER);
		style.setBorder(new FlexibleRectangularBorder(CONCRETE_BLACK_25, 0, 0, TITLE_BOTTOM_BORDER, 0));
		style.setMargin(new UniformOutline(PADDING_MARGIN));
		style.setPadding(new UniformOutline(PADDING_MARGIN));

		style = stylesheet.getSelectorStyle(new ClassSelector(MAIN_PAGE));
		style.setBackground(new RectangularBackground(Colors.WHITE));

		style = stylesheet.getSelectorStyle(new TypeSelector(Label.class));
		style.setBackground(NoBackground.NO_BACKGROUND);

		style = stylesheet.getSelectorStyle(new ClassSelector(MESSAGE));
		style.setPadding(new FlexibleOutline(MESSAGE_TOP_PADDING, 0, 0, 0));

		style = stylesheet.getSelectorStyle(new TypeSelector(QrCodeWidget.class));
		style.setHorizontalAlignment(Alignment.HCENTER);
		style.setVerticalAlignment(Alignment.VCENTER);

		style = stylesheet.getSelectorStyle(new TypeSelector(Button.class));
		style.setHorizontalAlignment(Alignment.HCENTER);
		style.setVerticalAlignment(Alignment.VCENTER);
		style.setBorder(new RoundedBorder(POMEGRANATE, ROUNDED_BORDER_RADIUS, ROUNDED_BORDER_THICKNESS));
		style.setBackground(new RoundedBackground(CORAL, ROUNDED_BORDER_RADIUS));
		style.setColor(Colors.WHITE);
		style.setMargin(new UniformOutline(PADDING_MARGIN));
		style.setPadding(new UniformOutline(PADDING_MARGIN));

		style = stylesheet
				.getSelectorStyle(new AndCombinator(new TypeSelector(Button.class), new StateSelector(Button.ACTIVE)));
		style.setBackground(new RoundedBackground(POMEGRANATE, ROUNDED_BORDER_RADIUS));

		return stylesheet;
	}
}
