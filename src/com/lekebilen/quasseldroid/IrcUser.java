package com.lekebilen.quasseldroid;

import java.util.Calendar;
import java.util.List;

public class IrcUser {
	public String name;
	public boolean away;
	public String awayMessage;
	public String ircOperator;
	public String nick;
	public List<String> channels;
	
	
	public String toString(){
		return nick + " away: " + away + " Num chans: "+ channels.size();
	}
}
