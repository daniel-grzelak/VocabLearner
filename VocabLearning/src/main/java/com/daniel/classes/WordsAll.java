package com.daniel.classes;

import java.sql.Time;
import java.time.LocalDate;

public class WordsAll {

	private Integer id;
	private String wordPl;
	private String wordEng;
	private String user;
	private int interval;
	private String level;
	private Time date;
	
	public WordsAll(Integer id, String wordPl, String wordEng, String user, int interval) {
		super();
		this.id = id;
		this.wordPl = wordPl;
		this.wordEng = wordEng;
		this.user = user;
		this.interval = interval;
	}
	
	public WordsAll(Integer id, String wordPl, String wordEng, int interval) {
		super();
		this.id = id;
		this.wordPl = wordPl;
		this.wordEng = wordEng;
		this.interval = interval;
	}
	
	public WordsAll(Integer id, String wordPl, String wordEng, int interval, String level) {
		super();
		this.id = id;
		this.wordPl = wordPl;
		this.wordEng = wordEng;
		this.interval = interval;
		this.level = level;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((wordEng == null) ? 0 : wordEng.hashCode());
		result = prime * result + ((wordPl == null) ? 0 : wordPl.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWordPl() {
		return wordPl;
	}

	public void setWordPl(String wordPl) {
		this.wordPl = wordPl;
	}

	public String getWordEng() {
		return wordEng;
	}

	public void setWordEng(String wordEng) {
		this.wordEng = wordEng;
	}



	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	
	
	public int getInterval() {
		return interval;
	}



	public void setInterval(int interval) {
		this.interval = interval;
	}



	public String getLevel() {
		return level;
	}



	public void setLevel(String level) {
		this.level = level;
	}



	public Time getDate() {
		return date;
	}



	public void setDate(Time date) {
		this.date = date;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordsAll other = (WordsAll) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (wordEng == null) {
			if (other.wordEng != null)
				return false;
		} else if (!wordEng.equals(other.wordEng))
			return false;
		if (wordPl == null) {
			if (other.wordPl != null)
				return false;
		} else if (!wordPl.equals(other.wordPl))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WordsAll [id=" + id + ", wordPl=" + wordPl + ", wordEng=" + wordEng + ", user=" + user + ", interval="
				+ interval + ", level=" + level + ", date=" + date + "]";
	}
	
	
	
}
