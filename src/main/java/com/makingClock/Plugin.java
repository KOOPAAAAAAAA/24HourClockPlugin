package com.makingClock;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPanel;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@PluginDescriptor(
		name = "24 Hour Clock"
)
public class Plugin extends net.runelite.client.plugins.Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	private TimeOverlay timeOverlay;

	@Override
	protected void startUp() throws Exception
	{
		log.info("24 Hour Clock started!");
		timeOverlay = new TimeOverlay();
		overlayManager.add(timeOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("24 Hour Clock stopped!");
		overlayManager.remove(timeOverlay);
	}

	private class TimeOverlay extends OverlayPanel
	{
		public TimeOverlay()
		{
			setPosition(OverlayPosition.TOP_LEFT);
			setMovable(true); // Allow movement
		}

		@Override
		public Dimension render(Graphics2D graphics)
		{
			// Get current time
			String time = new SimpleDateFormat("HH:mm").format(new Date());

			// Set overlay dimensions
			int width = 150;
			int height = 50;

			// Draw the background
			graphics.setColor(new Color(0, 0, 0, 150)); // Semi-transparent background
			graphics.fillRect(0, 0, width, height);

			// Draw the time
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Arial", Font.BOLD, 20));
			graphics.drawString(time, 10, 30); // Adjust positioning as needed

			return new Dimension(width, height);
		}
	}
}
