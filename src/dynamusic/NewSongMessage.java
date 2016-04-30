package dynamusic;

/**
This is a simple JavaBean (also called a MessageBean) that can be used
to carry information about an added song into the Scenario Manager.

It carries three simple properties:

1) songId - The id of the newly created song
2) songGenre - The musical genre of the new song (this may be useful
for a scenario to use as criteria, for example, for which playlists to add the song to)
3) title - The title of the new song
*/

import java.io.Serializable;

public class NewSongMessage implements Serializable{
	
	private String songId;
	private String songGenre;
	private String title;
	
	public NewSongMessage(){}
	
	public NewSongMessage(String songId, String songGenre, String title){
		this.songId = songId;
		this.songGenre = songGenre;
		this.title = title;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getSongGenre() {
		return songGenre;
	}

	public void setSongGenre(String songGenre) {
		this.songGenre = songGenre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
