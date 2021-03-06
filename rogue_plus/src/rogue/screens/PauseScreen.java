package rogue.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rogue.RoguePlus;

public class PauseScreen implements Screen {
	private char hrzchr = (char)205;
	private char vrtchr = (char)186;
	private char blcchr = (char)187;
	private char tlcchr = (char)188;
	private char trcchr = (char)200;
	private char brcchr = (char)201;
	/*private char tblchr = (char)185;
	private char tbrchr = (char)204;
	private char tlrchr = (char)202;
	private char blrchr = (char)203;*/
	
	private Screen lastScreen;
	
	private int option;
	private String[] options = new String[]{"Resume"/*,"Settings"*/,"Menu", "Exit"};
	
	public void displayFrame(AsciiPanel terminal) {
		String text[] = new String[23];
		int n=0;
		text[n] = "";
		for (int i=0;i<79;i++) {
			text[n] += ' ';
		}
		n++;
		text[n] = " "+brcchr;
		for (int i=0;i<76;i++) {
			text[n] += hrzchr;
		}
		text[n] += blcchr;
		for (n++;n<22;n++) {
			text[n] = " "+vrtchr;
			for (int i=0;i<76;i++) {
				text[n] += ' ';
			}
			text[n] += vrtchr;
		}
		text[n] = " "+trcchr;
		for (int i=0;i<76;i++) {
			text[n] += hrzchr;
		}
		text[n] += tlcchr;
		for (int i=0;i<text.length;i++) {
			terminal.write(text[i], 0, i, AsciiPanel.yellow);
		}
	}
	
	public void displayOptions(AsciiPanel terminal) {
		for (int i=0;i<options.length;i++) {
			if (option == i) {
				terminal.writeCenter(options[i], i+5, null, AsciiPanel.brightBlack);
			} else {
				terminal.writeCenter(options[i], i+5);
			}
		}
	}
	
	public void displayOutput(AsciiPanel terminal) {
		displayFrame(terminal);
		terminal.writeCenter("Pause Menu", 3);
		displayOptions(terminal);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_UP: option = option == 0 ? options.length-1 : option-1; break;
		case KeyEvent.VK_DOWN: option = option == options.length-1 ? 0 : option+1; break;
		case KeyEvent.VK_SPACE: case KeyEvent.VK_ENTER:
			switch (option) {
			case 0: return lastScreen;
			//case 1: return new SettingsScreen(this);
			case 1: return new StartScreen();
			case 2: RoguePlus.exit();
			} break;
		case KeyEvent.VK_ESCAPE: return lastScreen;
		}
		return this;
	}
	
	public PauseScreen(Screen lastScreen) {
		this.lastScreen = lastScreen;
	}
}
